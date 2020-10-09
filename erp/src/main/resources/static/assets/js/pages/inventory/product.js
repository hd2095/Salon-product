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
							<a href="javascript:editProduct(\'' +full.productId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit details">\
							<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteProduct(\'' +full.productId+'\',\''+full.productName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
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

function clearNewProductForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#productAlreadyExists').hide();
	$("span[id$='_span']").show();
	$('#productName').val('');
	$('#productBrand').val('');
	$('#productBarcode').val('');	
	var element = $('#productForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#productForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#productForm').find('.fv-plugins-message-container');
	element.html('');
}

function clearEditProductForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
	var element = $('#editProductForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#editProductForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#editProductForm').find('.fv-plugins-message-container');
	element.html('');
}


function editProduct(id){
	clearEditProductForm();
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
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').addClass('menu-item-open menu-item-here');
	$('#products_nav').addClass('menu-item-active');
}

var handleForms = function () {
	var _handleCreateForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('productForm'),
				{
					fields: {
						productName: {
							validators: {
								notEmpty: {
									message: 'Please enter product name'
								}
							}
						},
						productBrand: {
							validators: {
								notEmpty: {
									message: 'Please enter brand name'
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
		$('#createProductBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("productForm").action = "inventory/products";
					document.getElementById("productForm").submit();
				}
			});
		});
	}
	var _handleEditForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('editProductForm'),
				{
					fields: {
						productName: {
							validators: {
								notEmpty: {
									message: 'Please enter product name'
								}
							}
						},
						productBrand: {
							validators: {
								notEmpty: {
									message: 'Please enter brand name'
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
		$('#editProductBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("editProductForm").action = "inventory/products/editProduct/"+$('#edit_productId').val();
					document.getElementById("editProductForm").submit();
				}
			});
		});
	}
	return {
		init: function() {
			_handleCreateForm();
			_handleEditForm();
		}
	};
}();

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newProductModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
	handleForms.init();
});
