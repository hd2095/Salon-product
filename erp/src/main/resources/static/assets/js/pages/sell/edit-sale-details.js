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

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	/* var sales_sellingPrice = $('#sales_sellingPrice').val();
	var sales_quantity = $('#sales_quantity').val();
	var saleDate = $('#saleDate').val();
	if (saleDate.length < 1) {
		$('#saleDate_span').after('<span id="saleDate_error" class="error">please enter sale date</span>');
		$('#saleDate_span').hide();
		valid = false;
	}else{
		$('#saleDate_span').show();
		$('#saleDate_error').hide();
	}
	if (sales_sellingPrice.length < 1) {
		$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">please enter sale selling price</span>');
		$('#sales_sellingPrice_span').hide();
		valid = false;
	}else{
		if(isNaN(sales_sellingPrice)){
			$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">Selling price only allows numeric data</span>');
			$('#sales_sellingPrice_span').hide();
			valid = false;
		}else{
			if(Math.sign(sales_sellingPrice) == -1){
				$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">Selling price cannot be negative</span>');
				$('#sales_sellingPrice_span').hide();
				valid = false;
			}else{
				$('#sales_sellingPrice_span').show();
				$('#sales_sellingPrice_error').hide();
			}
		}
	}
	if (sales_quantity.length < 1) {
		$('#sales_quantity').after('<span id="sales_quantity_error" class="error">please enter sale quantity</span>');
		$('#sales_quantity_span').hide();
		valid = false;
	}else{
		if(isNaN(sales_quantity)){
			$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity only allows numeric data</span>');
			$('#sales_quantity_span').hide();
			valid = false;
		}else{
			if(Math.sign(sales_quantity) == -1){
				$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity cannot be negative</span>');
				$('#sales_quantity_span').hide();
				valid = false;
			}else if(sales_quantity == 0){
				$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity cannot be 0</span>');
				$('#sales_quantity_span').hide();
				valid = false;
			}else{
				$('#sales_quantity_span').show();
				$('#sales_quantity_error').hide();
			}
		}
	} */
	if(valid){
		document.salesForm.action = "sell/createSale";
		document.getElementById("salesForm").submit();
	}
}

function submitEditForm(){	
	document.editSalesForm.action = "sell/editSale/"+$('#edit_saleId').val();
	document.getElementById("editSalesForm").submit();
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
			console.log(response);
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

jQuery(document).ready(function() {
	setLinkActive();
	//fetchStocks();
	//fetchClients();
	//fetchProducts(0);
});
