'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#sales_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: '/inventory/getAllSales',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'stock.stockId'},
				{data: 'product.productName'},
				{data: 'costPrice'},
				{data: 'sellingPrice'},
				{data: 'quantity'},				
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {							
						return '\
							<a href="javascript:editSale(\'' +full.saleId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit details">\
								<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteSale(\'' +full.saleId+'\',\''+full.productName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
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
	document.salesForm.action = "inventory/sales";
	document.getElementById("salesForm").submit();
}

function submitEditForm(){	
	document.editSalesForm.action = "inventory/sales/editSale/"+$('#edit_saleId').val();
	document.getElementById("editSalesForm").submit();
}

function editProduct(id){
	$.ajax({
		url:'inventory/sales/editSale/'+id,
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
			return fetch(`inventory/sales/deleteSale/${id}`)
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
						location.replace('inventory/sales');
					}
				});
			}
		});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').addClass('menu-item-open');
	$('#sales_nav').addClass('menu-item-active');
}


jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newProductModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
