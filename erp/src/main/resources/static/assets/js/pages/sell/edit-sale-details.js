'use strict';

var stockResponseData;
var productsArray;
function fetchProductsForSaleEdit(id,product_id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != productsArray){
		$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='Select'>Select Product</option>");
		for( var i = 0; i<productsArray.length; i++){
			var productId = productsArray[i]['productId'];
			var productName = productsArray[i]['productName'];
			var brandName = productsArray[i]['productBrand'];
			if(productId == product_id){
				$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
				$('select[name="['+ id +'][edit_sale_product]"').val(product_id);	
			}else{
				$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");	
			}			
		}
	}else{
		$.ajax({
			url: HOST_URL + '/inventory/getAllProducts',
			type: 'get',
			dataType: 'json',
			success: function(response){  
				productsArray = response.data;
				$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='Select'>Select Product</option>");
				for( var i = 0; i<response.data.length; i++){
					var productId = response.data[i]['productId'];
					var productName = response.data[i]['productName'];
					var brandName = response.data[i]['productBrand'];
					if(productId == product_id){
						$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
						$('select[name="['+ id +'][edit_sale_product]"').val(product_id);						
					}else{
						$('select[name="['+ id +'][edit_sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");	
					}
				}
			}
		});
	}
}

function populateCostAndProductName(value){
	for( var i = 0; i<stockResponseData.length; i++){
		var stockId = stockResponseData[i]['stockId'];     
		var productName = stockResponseData[i]['product']['productName'];
		var productId = stockResponseData[i]['product']['productId'];
		var costPrice = stockResponseData[i]['order']['costPrice'];
		if(value == stockId){
			$("#sales_product_name").val(productName);	
			$("#salesProductId").val(productId);
			$("#salesCostPrice").val(costPrice);
			$("#sales_CostPrice").val(costPrice);
		}      	 
	}
}

function clearNewSalesForm(){
	$('.error').remove();
	$('#outOfStock').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
	$('#sales_sellingPrice').val(0);
	$('#sales_quantity').val(0);	
	$('#saleDate').val('');
}

function clearEditSalesForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#sell_nav').addClass('menu-item-open menu-item-here');
	$('#sales_nav').addClass('menu-item-active');
}

function fetchStocks(){
	$.ajax({
		url: HOST_URL + '/sell/stock/getAllStock',
		type: 'get',
		dataType: 'json',
		success: function(response){
			stockResponseData = response.data;
			var productName;
			var productId;
			var costPrice;
			for( var i = 0; i<response.data.length; i++){
				var stockId = response.data[i]['stockId'];     
				productName = response.data[i]['product']['productName'];
				productId = response.data[i]['product']['productId'];
				costPrice = response.data[i]['order']['costPrice'];
				if(i == 0){
					$("#sales_product_name").val(productName);	
					$("#salesProductId").val(productId);
					$("#salesCostPrice").val(costPrice);
					$("#sales_CostPrice").val(costPrice);					
				}
				$("#sales_stock_id").append("<option value='"+stockId+"'>"+stockId+"</option>");
			}
		}
	});
}

function fetchClients(id){	
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var clientId = response.data[i]['clientId'];
				var clientName = response.data[i]['fullName'];
				$("#edit_sale_client").append("<option value='"+clientId+"'>"+clientName+"</option>");
				if(clientId == id){
					$("#edit_sale_client").val(id);
				}
			}
		}
	});
}

function fetchSaleDetails(id){
	var fetchedDetails = true;
	localStorage.setItem("fetchedSaleDetails", fetchedDetails);
	$.ajax({
		url: HOST_URL + '/sell/getSalesDetails/'+id,
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
			for( var i = 0; i<response.data.length; i++){
				if(i > 0){
					$('#addProductBtnSale').click();		
					totalElementsSaleEdit(response.data.length - 1);
				}
				$('input[name="['+ i +'][edit_sale_record_id]"').val(response.data[i]['recordId']);
				var productId = response.data[i]['product']['productId'];
				fetchProductsForSaleEdit(i,productId);
				$('input[name="['+ i +'][edit_product_selling_price]"').val(response.data[i]['sellingPrice']);
				$('input[name="['+ i +'][edit_product_quantity]"').val(response.data[i]['quantity']);				
			}
		},
		complete: function(){
			KTApp.unblockPage();
			localStorage.removeItem("fetchedSaleDetails");
		}
	});
}

function totalElementsSaleEdit(id){
	$("input[name='edit_sale_total_elements']").val(id);
}

function decTotalElementsSaleEdit(){
	var currentElements = $("input[name='edit_sale_total_elements']").val();
	$("input[name='edit_sale_total_elements']").val(parseInt(currentElements) - 1);
}

function decrementTotalSaleForEdit(id){
	var total = $('#edit_sale_cost').val();	
	var sellPrice = $('input[name="['+ id +'][edit_product_selling_price]"').val();
	var quantity = $('input[name="['+ id +'][edit_product_quantity]"').val();
	total = total - (sellPrice * quantity);
	$('#edit_sale_cost').val(total);
}

function removeProductFromSale(recordId){
	$.ajax({
		url: HOST_URL + '/sell/deleteProductFromSale/'+recordId,
		type: 'get',
		dataType: 'json',
		success: function(response){      
			if(undefined != response.redirect){
				if(response.redirect == true){
					window.location.assign(HOST_URL +'/sell/sales');
				}	
			}			
			else if('successful' != response.status){
				alert('Something went wrong in deleting product kindly contact support team for assistance');
			}
		},

	});
}


function calculateSaleTotalForEdit(value,param){
	var totalElements = $("input[name='edit_sale_total_elements']").val();
	var cost_price = 0;
	var total = 0;
	if(totalElements == 0){
		cost_price = $('input[name="['+ param.substring(1,2) +'][edit_product_selling_price]"').val();
		total = value * cost_price;
	}else{
		var tempTotal = 0;
		var quantity = 0;
		for(var i = 0;i<=totalElements;i++){
			quantity = $('input[name="['+ i +'][edit_product_quantity]"').val();
			cost_price = $('input[name="['+ i +'][edit_product_selling_price]"').val();
			tempTotal += quantity * cost_price;
		}
		total = tempTotal;
	}
	$('#edit_sale_cost').val(total);	
}

function checkIfAllEntriesAreValid(){
	var invalid = false;
	$("select[name*='edit_sale_product']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("input[name*='edit_product_selling_price']").each(function(){
		if($(this).val() < 0){
			invalid = true;
		}
	});
	$("input[name*='edit_product_quantity']").each(function(){
		if($(this).val() <= 0){
			invalid = true;
		}
	});
	return invalid;
}

var handleForm = function () {
	var _handleEditForm = function() {
		var validation;
		const saleForm = document.getElementById('editSalesForm');
		validation = FormValidation.formValidation(
				saleForm,
				{
					fields: {
						saleDate : {
							validators : {
								notEmpty : {
									message : 'Please enter sale date'
								},
							}
						},
						"[0][edit_sale_product]" : {
							validators : {
								callback : {
									message : 'Please select a product',
									callback : function(input) {										
										const options = $('select[name="[0][edit_sale_product]"]').val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][edit_product_selling_price]" : {
							validators : {
								callback : {
									message : 'please enter product selling price (0 or positive allowed)',
									callback : function(input) {
										// Get the selected options
										const options = $('input[name="[0][edit_product_selling_price]"]').val();
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
		$('[name="saleDate"]').datepicker({
			format : 'mm/dd/yyyy'
		}).on('changeDate', function(e) {
			validation.revalidateField('saleDate');
		});
		$('#editSaleBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				var invalid = false;
				if (status == 'Valid') {
					invalid = checkIfAllEntriesAreValid();
					if (invalid) {
						swal.fire(
								{
									text : "Sorry, looks like there are some errors detected, please make sure all products,selling price and quantity are selected/entered and try again.",
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
						document.getElementById("editSalesForm").action = "sell/editSale/"+$('#edit_saleId').val();
						document.getElementById("editSalesForm").submit();
					}
				} else {
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

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	setLinkActive();
	handleForm.init();
});
