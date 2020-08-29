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
		document.getElementById("editOrderForm").submit();
	}
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

jQuery(document).ready(function() {

});