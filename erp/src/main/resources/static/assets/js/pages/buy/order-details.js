'use strict'
var supplierArray;
var productsArray;
function fetchProducts(id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != productsArray){
		$('select[name="['+ id +'][order_product]"]').append("<option value='Select'>Select Product</option>");
		for( var i = 0; i<productsArray.length; i++){
			var productId = productsArray[i]['productId'];
			var productName = productsArray[i]['productName'];
			var brandName = productsArray[i]['productBrand'];
			$('select[name="['+ id +'][order_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
		}
	}else{
		$.ajax({
			url: HOST_URL + '/inventory/getAllProducts',
			type: 'get',
			dataType: 'json',
			success: function(response){  
				productsArray = response.data;
				$('select[name="['+ id +'][order_product]"]').append("<option value='Select'>Select Product</option>");
				for( var i = 0; i<response.data.length; i++){
					var productId = response.data[i]['productId'];
					var productName = response.data[i]['productName'];
					var brandName = response.data[i]['productBrand'];
					$('select[name="['+ id +'][order_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
				}
			}
		});
	}
}

function fetchSuppliers(id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != supplierArray){
		$('select[name="['+ id +'][product_supplier]"]').append("<option value='Select'>Select Supplier</option>");
		for( var i = 0; i<supplierArray.length; i++){
			var supplierId = supplierArray[i]['supplierId'];         
			var supplierName = supplierArray[i]['supplierName'];
			$('select[name="['+ id +'][product_supplier]"]').append("<option value='"+supplierId+"'>"+supplierName+"</option>");
		}
	}else{
		$.ajax({
			url: HOST_URL + '/buy/getAllSuppliers',
			type: 'get',
			dataType: 'json',
			success: function(response){      
				supplierArray = response.data;
				$('select[name="['+ id +'][product_supplier]"]').append("<option value='Select'>Select Supplier</option>");
				for( var i = 0; i<response.data.length; i++){
					var supplierId = response.data[i]['supplierId'];         
					var supplierName = response.data[i]['supplierName'];
					$('select[name="['+ id +'][product_supplier]"]').append("<option value='"+supplierId+"'>"+supplierName+"</option>");
				}
			}
		});
	}
}

function populateBrandField(element,value){
	for(var i = 0; i<productsArray.length; i++ ){
		var productId = productsArray[i]['productId'];
		if(productId == value){			
			$('input[name="['+ element.substring(1,2) +'][product_brand]"').val(productsArray[i]['productBrand']);
			break;
		}else{
			$('input[name="['+ element.substring(1,2) +'][product_brand]"').val('');
		}
	}
}

function calculateOrderTotal(value,param){
	var totalElements = $("input[name='total_elements']").val();
	var cost_price = 0;
	var total = 0;
	if(totalElements == 0){
		cost_price = $('input[name="['+ param.substring(1,2) +'][product_cost]"').val();
		total = value * cost_price;
	}else{
		var tempTotal = 0;
		var quantity = 0;
		for(var i = 0;i<=totalElements;i++){
			quantity = $('input[name="['+ i +'][product_quantity]"').val();
			cost_price = $('input[name="['+ i +'][product_cost]"').val();
			tempTotal += quantity * cost_price;
		}
		total = tempTotal;
	}
	$('#order_cost').val(total);	
}

function totalElements(id){
	$("input[name='total_elements']").val(id);
}

function decrementTotalCost(id){
	var total = $('#order_cost').val();	
	var costPrice = $('input[name="['+ id +'][product_cost]"').val();
	var quantity = $('input[name="['+ id +'][product_quantity]"').val();
	total = total - (costPrice * quantity);
	$('#order_cost').val(total);
}

function decTotalElements(){
	var currentElements = $("input[name='total_elements']").val();
	$("input[name='total_elements']").val(parseInt(currentElements) - 1);
}

var handleForm = function () {
	var _handleCreateForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('orderForm'),
				{
					fields: {
						orderDate: {
							validators: {
								notEmpty: {
									message: 'Please enter order date'
								}
							}
						},
						"[0][order_product]" : {
							validators : {
								callback : {
									message : 'Please select one product',
									callback : function(input) {
										// Get the selected options
										const options = $(
										'select[name="[0][order_product]"]')
										.val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][product_supplier]" : {
							validators : {
								callback : {
									message : 'Please select one supplier',
									callback : function(input) {
										// Get the selected options
										const options = $(
										'select[name="[0][product_supplier]"]')
										.val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][product_cost]" : {
							validators : {
								callback : {
									message : 'please enter product cost price (0 or positive allowed)',
									callback : function(input) {
										// Get the selected options
										const options = $('input[name="[0][product_cost]"]').val();
										return (options > 0);
									}
								}
							}
						},
						"[0][product_quantity]" : {
							validators : {
								callback : {
									message : 'Please enter product quantity (can\'t be 0 or negative)',
									callback : function(input) {
										// Get the selected options
										const options = $('input[name="[0][product_quantity]"]').val();
										return (options > 0);
									}
								}
							}
						}
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						submitButton: new FormValidation.plugins.SubmitButton(),	                   
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		);
		$('#createOrderBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				var invalid = false;
				if (status == 'Valid') {
					invalid = checkIfAllEntriesAreValid();
					if (invalid) {
						swal.fire(
								{
									text : "Sorry, looks like there are some errors detected, please make sure all products,supplier\'s,cost price and quantity are selected/entered and try again.",
									icon : "error",
									buttonsStyling : false,
									confirmButtonText : "Ok, got it!",
									customClass : {
										confirmButton : "btn font-weight-bold btn-light-primary"
									}
								})
								.then(
										function() {
											KTUtil.scrollTop();
										});
					} else {
						document.getElementById("orderForm").action = "buy/createOrder";
						document.getElementById("orderForm").submit();
					}
				}else{
					KTUtil.scrollTop();
				}
			});
		});
	}
	return {
		init: function() {
			_handleCreateForm();			
		}
	};
}();

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#newOrder_nav').addClass('menu-item-active');
}

function checkIfAllEntriesAreValid(){
	var invalid = false;
	$("select[name*='product_supplier']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("select[name*='order_product']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("input[name*='product_cost']").each(function(){
		if($(this).val() < 0){
			invalid = true;
		}
	});
	$("input[name*='product_quantity']").each(function(){
		if($(this).val() <= 0){
			invalid = true;
		}
	});
	return invalid;
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	fetchProducts(0);
	fetchSuppliers(0);
	handleForm.init();
	setLinkActive();
});