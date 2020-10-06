'use strict'
var supplierArray;
var productsArray;
function fetchEditProducts(id,product_Id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != productsArray){
		$('select[name="['+ id +'][edit_order_product]"]').append("<option value='Select'>Select Product</option>");
		for( var i = 0; i<productsArray.length; i++){
			var productId = productsArray[i]['productId'];
			var productName = productsArray[i]['productName'];	
			var brandName = productsArray[i]['productBrand'];
			$('select[name="['+ id +'][edit_order_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
			if(productId == product_Id){
				$('select[name="['+ id +'][edit_order_product]"]').val(product_Id);
			}
		}
	}else{
		$.ajax({
			url: HOST_URL + '/inventory/getAllProducts',
			type: 'get',
			dataType: 'json',
			success: function(response){  
				productsArray = response.data;
				$('select[name="['+ id +'][edit_order_product]"]').append("<option value='Select'>Select Product</option>");
				for( var i = 0; i<response.data.length; i++){
					var productId = response.data[i]['productId'];
					var productName = response.data[i]['productName'];		
					var brandName = response.data[i]['productBrand'];
					$('select[name="['+ id +'][edit_order_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
					if(productId == product_Id){
						$('select[name="['+ id +'][edit_order_product]"]').val(product_Id);
					}
				}
			}
		});
	}
}

function fetchEditSuppliers(id,supplier_id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != supplierArray){
		$('select[name="['+ id +'][edit_product_supplier]"]').append("<option value='Select'>Select Supplier</option>");
		for( var i = 0; i<supplierArray.length; i++){
			var supplierId = supplierArray[i]['supplierId'];         
			var supplierName = supplierArray[i]['supplierName'];		
			$('select[name="['+ id +'][edit_product_supplier]"]').append("<option value='"+supplierId+"'>"+supplierName+"</option>");
			if(supplierId == supplier_id){
				$('select[name="['+ id +'][edit_product_supplier]"]').val(supplier_id);
			}
		}
	}else{
		$.ajax({
			url: HOST_URL + '/buy/getAllSuppliers',
			type: 'get',
			dataType: 'json',
			success: function(response){      
				supplierArray = response.data;
				$('select[name="['+ id +'][edit_product_supplier]"]').append("<option value='Select'>Select Supplier</option>");
				for( var i = 0; i<response.data.length; i++){
					var supplierId = response.data[i]['supplierId'];         
					var supplierName = response.data[i]['supplierName'];
					$('select[name="['+ id +'][edit_product_supplier]"]').append("<option value='"+supplierId+"'>"+supplierName+"</option>");
					if(supplierId == supplier_id){
						$('select[name="['+ id +'][edit_product_supplier]"]').val(supplier_id);
					}				
				}
			}
		});
	}
}

function populateBrandFieldForEdit(element,value){
	for(var i = 0; i<productsArray.length; i++ ){
		var productId = productsArray[i]['productId'];
		if(productId == value){			
			$('input[name="['+ element.substring(1,2) +'][edit_product_brand]"').val(productsArray[i]['productBrand']);
			break;
		}else{
			$('input[name="['+ element.substring(1,2) +'][edit_product_brand]"').val('');
		}
	}
}

function decrementTotalCostForEdit(id){
	var total = $('#edit_order_cost').val();	
	var costPrice = $('input[name="['+ id +'][edit_product_cost]"').val();
	var quantity = $('input[name="['+ id +'][edit_product_quantity]"').val();
	total = total - (costPrice * quantity);
	$('#edit_order_cost').val(total);
}

function calculateOrderTotalForEdit(value,param){
	var totalElements = $("input[name='edit_total_elements']").val();
	var cost_price = 0;
	var total = 0;
	if(totalElements == 0){
		cost_price = $('input[name="['+ param.substring(1,2) +'][edit_product_cost]"').val();
		total = value * cost_price;
	}else{
		var tempTotal = 0;
		var quantity = 0;
		for(var i = 0;i<=totalElements;i++){
			quantity = $('input[name="['+ i +'][edit_product_quantity]"').val();
			cost_price = $('input[name="['+ i +'][edit_product_cost]"').val();
			tempTotal += quantity * cost_price;
		}
		total = tempTotal;
	}
	$('#edit_order_cost').val(total);	
}

function totalElementsEdit(id){
	$("input[name='edit_total_elements']").val(id);
}

function fetchOrderDetails(id){
	var fetchedOrderDetails = true;
	localStorage.setItem("fetchedOrderDetails", fetchedOrderDetails); 
	$.ajax({
		url: HOST_URL + '/buy/getOrderDetails/'+id,
		type: 'get',
		dataType: 'json',
		beforeSend: function(){
			KTApp.blockPage({
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
		},
		success: function(response){    
			if(response.data.length > 0){
				for( var i = 0; i<response.data.length; i++){
					if(i > 0){
						$('#addProductBtn').click();
						totalElementsEdit(response.data.length - 1);
					}
					$('input[name="['+ i +'][edit_order_record_id]"').val(response.data[i]['recordId']);
					var productId = response.data[i]['product']['productId'];
					var productBrand = response.data[i]['productBrand'];
					var productQuantity = response.data[i]['productQuantity'];
					var productCost = response.data[i]['productCostPrice'];
					var supplierId = response.data[i]['supplier']['supplierId'];
					$('input[name="['+ i +'][edit_product_brand]"').val(productBrand);
					$('input[name="['+ i +'][edit_product_quantity]"').val(productQuantity);
					$('input[name="['+ i +'][edit_product_cost]"').val(productCost);	
					$('input[name="['+ i +'][elementCount]"').val(i);
					var productDeliveryStatus = response.data[i]['productDeliveryStatus'];
					if(productDeliveryStatus == 'Received'){
						var productName = response.data[i]['product']['productName'];
						var supplierName = response.data[i]['supplier']['supplierName'];
						$('select[name="['+ i +'][edit_order_product]"]').attr('readonly',true);
						$('select[name="['+ i +'][edit_order_product]"]').append("<option value='"+productId+"'>"+productName+"</option>");
						$('select[name="['+ i +'][edit_product_supplier]"]').attr('readonly',true);
						$('select[name="['+ i +'][edit_product_supplier]"]').append("<option value='"+supplierId+"'>"+supplierName+"</option>");
						$('input[name="['+ i +'][edit_product_received]"]').prop('checked',true);	
						$('input[name="['+ i +'][edit_product_received]"').prop('disabled',true);
						$('input[name="['+ i +'][edit_product_cost]"').prop('readonly',true);
						$('input[name="['+ i +'][edit_product_quantity]"').prop('readonly',true);
					}else{
						fetchEditProducts(i,productId);						
						fetchEditSuppliers(i,supplierId);
					}
				}
			}else{
				fetchEditProducts(0);
				fetchEditSuppliers(0);
			}
		},
		complete: function(){
			KTApp.unblockPage();
			localStorage.removeItem("fetchedOrderDetails");
		}
	});	
}

function removeProductFromOrder(recordId){
	$.ajax({
		url: HOST_URL + '/buy/deleteProductFromOrder/'+recordId,
		type: 'get',
		dataType: 'json',
		success: function(response){      
			if('successful' != response.status){
				alert('Something went wrong in deleting product kindly contact support team for assistance');
			}
		},

	});
}

function decTotalElementsEdit(){
	var currentElements = $("input[name='edit_total_elements']").val();
	$("input[name='edit_total_elements']").val(parseInt(currentElements) - 1);
}

function receiveProduct(name){
	var id = name.substring(1,2);
	if(confirm('Editing this product won\'t be allowed once you receive')) {	
		var recordId = $('input[name="['+ id +'][edit_order_record_id]"').val();
		var totalElements = $("input[name='edit_total_elements']").val();
		var counter = 0;
		var isThisFinalProduct = false;
		$('input[name$="[edit_product_received]"]').each(function(){
			if($(this)[0].checked){
				counter++;
			}
		});
		if((counter - 1) == totalElements){
			isThisFinalProduct = true;
		}		
		if(totalElements == "0"){
			isThisFinalProduct = true;
		}
		$.ajax({
			url: HOST_URL + '/buy/recieveProductFromOrder/'+recordId+'?isFinalProduct='+isThisFinalProduct,
			type: 'get',
			dataType: 'json',
			beforeSend: function(){
				KTApp.blockPage({
					overlayColor: 'red',
					opacity: 0.1,
					state: 'primary' // a bootstrap color
				});
			},
			success: function(response){      
				console.log(response);
				if('successful' != response.status){
					alert('Something went wrong in receiving product kindly contact support team for assistance');
				}
				if(isThisFinalProduct){
					console.log("should redirect no");
					location.href = '/buy/newOrder';
				}				
			},
			complete: function(){
				KTApp.unblockPage();			
			}
		});
	}else{
		$('input[name="['+ id +'][edit_product_received]"]').prop('checked',false);		
	}
}

function hideProducReceivedSwitch(id){
	$('div[name="['+ id +'][edit_product_received_div]"').hide();
}

var handleForm = function () {
	var _handleEditForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('editOrderForm'),
				{
					fields: {
						orderDate: {
							validators: {
								notEmpty: {
									message: 'Please enter order date'
								}
							}
						},
						"[0][edit_order_product]" : {
							validators : {
								callback : {
									message : 'Please select one product',
									callback : function(input) {
										// Get the selected options
										const options = $(
										'select[name="[0][edit_order_product]"]')
										.val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][edit_product_supplier]" : {
							validators : {
								callback : {
									message : 'Please select one supplier',
									callback : function(input) {
										// Get the selected options
										const options = $(
										'select[name="[0][edit_product_supplier]"]')
										.val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][edit_product_cost]" : {
							validators : {
								callback : {
									message : 'please enter product cost price (0 or positive allowed)',
									callback : function(input) {
										// Get the selected options
										const options = $('input[name="[0][edit_product_cost]"]').val();
										return (options > 0);
									}
								}
							}
						},
						"[0][edit_product_quantity]" : {
							validators : {
								callback : {
									message : 'Please enter product quantity (can\'t be 0 or negative)',
									callback : function(input) {
										// Get the selected options
										const options = $('input[name="[0][edit_product_quantity]"]').val();
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
		$('#editOrderBtn').on('click', function (e) {
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
						document.getElementById("editOrderForm").submit();
					}
				}else{
					KTUtil.scrollTop();
				}
			});
		});
	}
	return {
		init: function() {
			_handleEditForm();			
		}
	};
}();

function checkIfAllEntriesAreValid(){
	var invalid = false;
	$("select[name*='edit_product_supplier']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("select[name*='edit_order_product']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("input[name*='edit_product_cost']").each(function(){
		if($(this).val() < 0){
			invalid = true;
		}
	});
	$("input[name*='edit_product_quantity']").each(function(){
		if($(this).val() < 0){
			invalid = true;
		}
	});
	return invalid;
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#newOrder_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	handleForm.init();
	setLinkActive();
});