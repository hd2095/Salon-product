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
}

function clearEditProductForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function submitForm(){
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var product_name = $('#productName').val();
	if (product_name.length < 1) {
		$('#productName').after('<span id="productName_error" class="error">please enter product name</span>');
		$('#productName_span').hide();
		valid = false;
	}else{
		$('#productName_span').show();
		$('#productName_error').hide();
	}
	if(valid){
		document.getElementById("productForm").submit();
	}
}

function submitEditForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var product_name = $('#edit_productName').val();
	var product_brand = $('#edit_productBrand').val();
	if (product_name.length < 1) {
		$('#edit_productName').after('<span id="edit_productName_error" class="error">please enter product name</span>');
		$('#edit_productName_span').hide();
		valid = false;
	}else{
		$('#edit_productName_span').show();
		$('#edit_productName_error').hide();
	}
	if (product_brand.length < 1) {
		$('#edit_productBrand').after('<span id="edit_productBrand_error" class="error">please enter product brand</span>');
		$('#edit_productBrand_span').hide();
		valid = false;
	}else{
		$('#edit_productBrand_span').show();
		$('#edit_productBrand_error').hide();
	}
	if(valid){
		document.editProductForm.action = "inventory/products/editProduct/"+$('#edit_productId').val();
		document.getElementById("editProductForm").submit();
	}
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
					document.getElementById("productForm").action = "products";
					document.getElementById("productForm").submit();
				}
			});
		});
	}
	var _handleServiceForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('serviceForm'),
				{
					fields: {
						serviceName: {
							validators: {
								notEmpty: {
									message: 'Please enter service name'
								}
							}
						},
						serviceDuration: {
							validators: {
								notEmpty: {
									message: 'Please enter service duration'
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
		$('[name="serviceDuration"]').timepicker().on('changeTime.timepicker', function(e) {
			validation.revalidateField('serviceDuration');
		});
		$('#createServiceBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("serviceForm").action = "services/create";
					document.getElementById("serviceForm").submit();
				}
			});
		});
	}
	return {
		init: function() {
			_handleCreateForm();
			_handleServiceForm();
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
