'use strict';

var stockResponseData;
var productsArray;
function fetchProducts(id){
	if(undefined == id){
		id = 0;
	}
	if(undefined != productsArray){
		$('select[name="['+ id +'][sale_product]"]').append("<option value='Select'>Select Product</option>");
		for( var i = 0; i<productsArray.length; i++){
			var productId = productsArray[i]['productId'];
			var productName = productsArray[i]['productName'];
			var brandName = productsArray[i]['productBrand'];
			$('select[name="['+ id +'][sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
		}
	}else{
		$.ajax({
			url: HOST_URL + '/inventory/getAllProducts',
			type: 'get',
			dataType: 'json',
			success: function(response){  
				productsArray = response.data;
				$('select[name="['+ id +'][sale_product]"]').append("<option value='Select'>Select Product</option>");
				for( var i = 0; i<response.data.length; i++){
					var productId = response.data[i]['productId'];
					var productName = response.data[i]['productName'];
					var brandName = response.data[i]['productBrand'];
					$('select[name="['+ id +'][sale_product]"]').append("<option value='"+productId+"'>"+productName+" ( " + brandName +" )"+ "</option>");
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
	document.editSalesForm.action = "sell/sales/editSale/"+$('#edit_saleId').val();
	document.getElementById("editSalesForm").submit();
}

function editProduct(id){
	clearEditSalesForm();
	$.ajax({
		url: HOST_URL + '/sell/sales/editSale/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){
						$('#edit_saleId').val(v.saleId);
						$('#edit_productName').val(v.productName);
						$('#edit_productBrand').val(v.productBrand);
						if(v.productBarcode == 'No Barcode Provided'){
							$('#edit_productBarcode').val('');
						}else{
							$('#edit_productBarcode').val(v.productBarcode);
						}						  							  
					});
				}
			});
			$('#editProductModal').modal();
		}
	});
}

function deleteProduct(id,productName){
	Swal.fire({
		title: "Are you sure you want to delete " + productName+ "!",
		icon: "warning",		  
		confirmButtonText: "Yes, delete it!",
		showCancelButton: true,
		cancelButtonText: "No, Cancel!",
		customClass: {
			confirmButton: "btn btn-danger",
			cancelButton: "btn btn-default"
		},
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return fetch(`${HOST_URL}/sell/sales/deleteSale/${id}`)
			.then(response => {
				if(!response.ok){
					throw new Error(response.statusText);	
				}
				return response.json();
			})
			.catch(error => {
				Swal.showValidationMessage(
						`Request failed: ${error}`
				)
			})  
		}
	}).then(function(result){
		if(result.value){
			Swal.fire({
				title: productName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('sell/sales');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#subPages').addClass('in');
	$('#sales_nav').addClass('active');
	$('#inventory_nav').addClass('active');
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

function fetchClients(){	
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var clientId = response.data[i]['clientId'];
				var clientName = response.data[i]['fullName'];
				$("#sale_client").append("<option value='"+clientId+"'>"+clientName+"</option>");
			}
		}
	});

}

function populateStockInHand(id,name){
	if('Select' != id){
		$.ajax({
			url: HOST_URL + 'inventory/stock/getStockByProductId/'+id,
			type: 'get',
			dataType: 'json',
			success: function(response){      
				$('input[name="['+ name.substring(1,2) +'][sale_product_stock]"]').val(response.stockQuantity);
			}
		});
	}else{
		$('input[name="['+ name.substring(1,2) +'][sale_product_stock]"]').val('');
	}
}

function calculateSaleTotal(value,param){
	var totalElements = $("input[name='sale_total_elements']").val();
	var cost_price = 0;
	var total = 0;
	if(totalElements == 0){
		cost_price = $('input[name="['+ param.substring(1,2) +'][product_selling_price]"').val();
		total = value * cost_price;
	}else{
		var tempTotal = 0;
		var quantity = 0;
		for(var i = 0;i<=totalElements;i++){
			quantity = $('input[name="['+ i +'][product_quantity]"').val();
			cost_price = $('input[name="['+ i +'][product_selling_price]"').val();
			tempTotal += quantity * cost_price;
		}
		total = tempTotal;
	}
	$('#sale_cost').val(total);	
}

function totalElements(id){
	$("input[name='sale_total_elements']").val(id);
}

function decTotalElements(){
	var currentElements = $("input[name='sale_total_elements']").val();
	$("input[name='sale_total_elements']").val(parseInt(currentElements) - 1);
}

function decrementTotalSale(id){
	var total = $('#sale_cost').val();	
	var sellPrice = $('input[name="['+ id +'][product_selling_price]"').val();
	var quantity = $('input[name="['+ id +'][product_quantity]"').val();
	total = total - (sellPrice * quantity);
	$('#sale_cost').val(total);
}

jQuery(document).ready(function() {
	//fetchStocks();
	fetchClients();
	fetchProducts(0);
});
