'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#product_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/inventory/getAllProducts',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'productName'},
				{data: 'productBrand'},
				{data: 'productBarcode'},								
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {							
						return '\
							<a href="javascript:editProduct(\'' +full.productId+'\');" class="btn btn-xs btn-custom" title="Edit details">\
								<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteProduct(\'' +full.productId+'\',\''+full.productName+'\');" class="btn btn-xs btn-custom" title="Delete">\
							<i class="lnr lnr-trash"></i>\
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
	document.getElementById("productForm").submit();
}

function submitEditForm(){	
	document.editProductForm.action = "inventory/products/editProduct/"+$('#edit_productId').val();
	document.getElementById("editProductForm").submit();
}

function editProduct(id){
	$.ajax({
		url: HOST_URL + '/inventory/products/editProduct/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				  if(key == 'data'){					  
					  $.each(value, function(k,v){
						  	$('#edit_productId').val(v.productId);
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
			return fetch(`${HOST_URL}/inventory/products/deleteProduct/${id}`)
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
						location.replace('inventory/products');
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
	$('#products_nav').addClass('active');
	$('#inventory_nav').addClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newProductModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
