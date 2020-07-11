'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#order_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: '/inventory/getAllOrders',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'product.productName'},
				{data: 'quantity'},
				{data: 'orderStatus'},								
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {							
						return '\
							<a href="javascript:editOrder(\'' +full.orderId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit details">\
								<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteOrder(\'' +full.orderId+'\',\''+full.product.productName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
								<i class="la la-trash"></i>\
							</a>\
						';
					},
				},
			],
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

function submitForm(){	
	document.getElementById("orderForm").submit();
}

function submitEditForm(){	
	console.log($('#edit_OrderId').val());
	document.editOrderForm.action = 'inventory/order/editOrder/'+$('#edit_OrderId').val();
	document.getElementById("editOrderForm").submit();
}

function editOrder(id){
	$.ajax({
		url:'inventory/order/editOrder/'+id,
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
		month = "02";
		break;
	case "Apr":
		month = "02";
		break;
	case "May":
		month = "02";
		break;
	case "Jun":
		month = "02";
		break;
	case "Jul":
		month = "02";
		break;
	case "Aug":
		month = "02";
		break;
	case "Sep":
		month = "02";
		break;
	case "Oct":
		month = "02";
		break;
	case "Nov":
		month = "02";
		break;
	case "Dec":
		month = "02";
		break;
	}
	var day = tokens[1];
	var year = tokens[2];
	date = month + "/" + day + "/" +year;
	return date;
}


function deleteOrder(id,productName){
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
			return fetch(`inventory/order/deleteOrder/${id}`)
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
						location.replace('inventory/newOrder');
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
		url: 'inventory/getAllProducts',
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
		url: 'inventory/getAllSuppliers',
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
                	$("#supplier_select").append("<option value='"+supplierId+"'>"+supplierName+"</option>");	
                }                
               }
		}
	});
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').addClass('menu-item-open');
	$('#newOrder_nav').addClass('menu-item-active');
}


jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newOrderModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
	fetchProducts();
	fetchSuppliers();
});
