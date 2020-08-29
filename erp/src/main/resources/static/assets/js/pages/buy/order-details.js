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

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	/* var order_date = $('#order_date').val();
	var costPrice = $('#costPrice').val();
	var quantity = $('#quantity').val();
	if (order_date.length < 1) {
		$('#order_date_span').after('<span id="order_date_error" class="error">please enter order date</span>');
		$('#order_date_span').hide();
		valid = false;
	}else{
		$('#order_date_span').show();
		$('#order_date_error').hide();
	}
	if (costPrice.length < 1) {
		$('#costPrice').after('<span id="costPrice_error" class="error">please enter order cost price</span>');
		$('#costPrice_span').hide();
		valid = false;
	}else{
		if(isNaN(costPrice)){
			$('#costPrice').after('<span id="costPrice_error" class="error">Cost price only allows numeric data</span>');
			$('#costPrice_span').hide();
			valid = false;
		}else{
			if(Math.sign(costPrice) == -1){
				$('#costPrice').after('<span id="costPrice_error" class="error">Cost price cannot be negative</span>');
				$('#costPrice_span').hide();
				valid = false;
			}else{
				$('#costPrice_span').show();
				$('#costPrice_error').hide();
			}
		}
	}
	if (quantity.length < 1) {
		$('#quantity').after('<span id="quantity_error" class="error">please enter order quantity</span>');
		$('#quantity_span').hide();
		valid = false;
	}else{
		if(isNaN(quantity)){
			$('#quantity').after('<span id="quantity_error" class="error">Quantity only allows numeric data</span>');
			$('#quantity_span').hide();
			valid = false;
		}else{
			if(Math.sign(quantity) == -1){
				$('#quantity').after('<span id="quantity_error" class="error">Quantity cannot be negative</span>');
				$('#quantity_span').hide();
				valid = false;
			}else{
				$('#quantity_span').show();
				$('#quantity_error').hide();
			}
		}
	} */
	if(valid){
		document.getElementById("orderForm").submit();
	}
}

jQuery(document).ready(function() {
	/*	$(function(){
		$('input[name$="[product_quantity]"').keyup(function(e){
			alert('called');
			var cost_price = $('input[name="['+ e.target.name.substring(1,2) +'][product_cost]"').val();
			var total = e.target.value * cost_price;
			$('#order_cost').val(total);
		});
	}); */
	fetchProducts(0);
	fetchSuppliers(0);
});