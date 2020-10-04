'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#order_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/buy/getAllOrders',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'orderId'},
				{data: 'orderDate',
					render: function(orderDate){
						return orderDate.substring(0,orderDate.lastIndexOf(','));
					}				
				},
				{data: 'orderTotal',
					render : function(orderTotal){
						return '<p> &#8377; ' + orderTotal + '</p>'; 
					}	},
					{data: 'orderDeliveryStatus'},
					{data: 'orderReceivedDate',
						render: function(orderReceivedDate){
							if(undefined != orderReceivedDate){
								return orderReceivedDate.substring(0,orderReceivedDate.lastIndexOf(','));
							}
						},
						defaultContent:''
					},
					{data: 'actions', responsivePriority: -1},
					],
					columnDefs: [
						{
							targets: -1,
							title: 'Actions',
							orderable: false,					
							render: function(data, type, full, meta) {
								if(full.orderDeliveryStatus != 'Received'){
									return '\
									<a href="buy/editOrder/'+full.orderId+'" class="btn btn-sm btn-clean btn-icon" title="Edit Order">\
									<i class="la la-edit"></i>\
									</a>\
									<a href="javascript:deleteOrder(\'' +full.orderId+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Order">\
									<i class="la la-trash"></i>\
									</a>\
									';
								}else{
									return '\
									<a href="buy/viewOrderDetails/'+full.orderId+'" class="btn btn-sm btn-clean btn-icon" title="View Order Details">\
									<i class="la la-cog"></i>\
									</a>\
									<a href="javascript:deleteOrder(\'' +full.orderId+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Order">\
									<i class="la la-trash"></i>\
									</a>\
									';
								}
							},
						},
						{
							width: '75px',
							targets: -3,
							render: function(data, type, full, meta) {
								var status = {							
									Cancelled: {'title': 'Cancelled', 'class': ' label-light-danger'},
									Received: {'title': 'Received', 'class': ' label-light-success'},									
									Booked: {'title': 'Booked', 'class': ' label-light-info'},
									InTransit:{'title': 'In Transit', 'class': ' label-light-warning'}
								};
								if (typeof status[data] === 'undefined') {
									return data;
								}
								return '<span class="label label-lg font-weight-bold' + status[data].class + ' label-inline">' + status[data].title + '</span>';
							},
						}
						]
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

function clearNewOrderForm(){
	$('.error').remove();
	$("span[id$='_span']").show();
	$('#validation_error').remove();
	$('#order_date').val('');
	$('#costPrice').val('');
	$('#quantity').val(0);
}

function clearEditOrderForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var order_date = $('#order_date').val();
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
	}
	if(valid){
		document.getElementById("orderForm").submit();
	}
}

function submitEditForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var order_date = $('#edit_order_date').val();
	var costPrice = $('#edit_cost_price').val();
	var quantity = $('#edit_order_quantity').val();
	if (order_date.length < 1) {
		$('#edit_order_date_span').after('<span id="edit_order_date_error" class="error">please enter order date</span>');
		$('#edit_order_date_span').hide();
		valid = false;
	}else{
		$('#edit_order_date_span').show();
		$('#edit_order_date_error').hide();
	}
	if (costPrice.length < 1) {
		$('#edit_cost_price').after('<span id="edit_cost_price_error" class="error">please enter order cost price</span>');
		$('#edit_cost_price_span').hide();
		valid = false;
	}else{
		if(isNaN(costPrice)){
			$('#edit_cost_price').after('<span id="edit_cost_price_error" class="error">Cost price only allows numeric data</span>');
			$('#edit_cost_price_span').hide();
			valid = false;
		}else{
			if(Math.sign(costPrice) == -1){
				$('#edit_cost_price').after('<span id="edit_cost_price_error" class="error">Cost price cannot be negative</span>');
				$('#edit_cost_price_span').hide();
				valid = false;
			}else{
				$('#costPrice_span').show();
				$('#edit_cost_price_error').hide();
			}
		}
	}
	if (quantity.length < 1) {
		$('#edit_order_quantity').after('<span id="edit_order_quantity_error" class="error">please enter order quantity</span>');
		$('#edit_order_quantity_span').hide();
		valid = false;
	}else{
		if(isNaN(quantity)){
			$('#edit_order_quantity').after('<span id="edit_order_quantity_error" class="error">Quantity only allows numeric data</span>');
			$('#edit_order_quantity_span').hide();
			valid = false;
		}else{
			if(Math.sign(quantity) == -1){
				$('#edit_order_quantity').after('<span id="edit_order_quantity_error" class="error">Quantity cannot be negative</span>');
				$('#edit_order_quantity_span').hide();
				valid = false;
			}else{
				$('#edit_order_quantity_span').show();
				$('#edit_order_quantity_error').hide();
			}
		}
	}
	if(valid){
		document.editOrderForm.action = 'buy/order/editOrder/'+$('#edit_OrderId').val();
		document.getElementById("editOrderForm").submit();
	}
}

function editOrder(id){
	clearEditOrderForm();
	$.ajax({
		url: HOST_URL + '/buy/order/editOrder/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){
						$('#edit_OrderId').val(v.orderId);
						$('#edit_cost_price').val(v.costPrice);
						$('#edit_order_quantity').val(v.quantity);
						$('#edit_order_product_dropdown').val(v.product.productName);
						$('#edit_order_brand').val(v.product.productBrand);	
						$('#edit_supplier_select').val(v.supplier.supplierName);
						$('#edit_order_receive_date').datepicker('setDate',formattedDate(v.orderReceivedDate));
						fetchProducts(v.product.productName);
						fetchSuppliers(v.supplier.supplierName);
						$('#edit_order_date').datepicker('setDate',formattedDate(v.orderDate));
					});
				}
			});
			$('#editOrderModal').modal();
		}
	});
}

function formattedDate(date){
	if(undefined != date){
		var tokens = date.split(" ");
		var month = "";
		switch(tokens[0]){
		case "Jan":
			month = "01";
			break;
		case "Feb":
			month = "02";
			break;
		case "Mar":
			month = "03";
			break;
		case "Apr":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "Jun":
			month = "06";
			break;
		case "Jul":
			month = "07";
			break;
		case "Aug":
			month = "08";
			break;
		case "Sep":
			month = "09";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		}
		var day = tokens[1];
		var year = tokens[2];
		date = month + "/" + day + "/" +year;
	}
	return date;
}


function deleteOrder(id){
	Swal.fire({
		title: "Are you sure you want to delete order no " + id + "!",
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
			return fetch(`${HOST_URL}/buy/order/deleteOrder/${id}`)
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
				title: " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('buy/newOrder');
				}
			});
		}
	});	
}

function populateBrandField(value){
	var brandName = value.split(',');
	if(brandName[1].includes("\"")){
		value = brandName[1].replace("\"","'");
	}else{
		value = brandName[1];
	}
	$('#order_brand').val(value);
}

function populateEditBrandField(value){
	var brandName = value.split(',');
	if(brandName[1].includes("\"")){
		value = brandName[1].replace("\"","'");
	}else{
		value = brandName[1];
	}
	$('#edit_order_brand').val(value);
}

function fetchProducts(product){
	$.ajax({
		url: HOST_URL + '/inventory/getAllProducts',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var productId = response.data[i]['productId'];
				var productBrand = response.data[i]['productBrand'];
				if(i == 0){
					$('#order_brand').val(productBrand);
				}
				if(productBrand.includes("'")){
					var tokens = productBrand.split("\'");             	
					productBrand = tokens[0] + "\"" + tokens[1];
				}
				var productName = response.data[i]['productName'];
				if(undefined != product){
					if(product == productName){
						$("#edit_order_product_dropdown").append("<option value='"+productId+","+productBrand+"' selected>"+productName+"</option>");
					}else{
						$("#edit_order_product_dropdown").append("<option value='"+productId+","+productBrand+"'>"+productName+"</option>");
					}	
				}else{
					$("#order_product_dropdown").append("<option value='"+productId+","+productBrand+"'>"+productName+"</option>");
				}                
			}
		}
	});
}

function fetchSuppliers(supplier){
	$.ajax({
		url: HOST_URL + '/buy/getAllSuppliers',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var supplierId = response.data[i]['supplierId'];         
				var supplierName = response.data[i]['supplierName'];
				if(undefined != supplier){
					if(supplier == supplierName){
						$("#edit_supplier_select").append("<option value='"+supplierId+"' selected>"+supplierName+"</option>");
					}else{
						$("#edit_supplier_select").append("<option value='"+supplierId+"'>"+supplierName+"</option>");
					}	
				}else{
					if(i == 0){
						$("#supplier_select").append("<option value=Select> Select a supplier </option>");
						$("#supplier_select").append("<option value='"+supplierId+"'>"+supplierName+"</option>");
					}else{
						$("#supplier_select").append("<option value='"+supplierId+"'>"+supplierName+"</option>");
					}	
				}                
			}
		}
	});
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#newOrder_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newOrderModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
