'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#supplier_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/buy/getAllSuppliers',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'supplierName'},
				{data: 'supplierNumber'},
				{data: 'supplierEmail'},				
				{data: 'supplierGstnNo'},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {							
							return '\
							<a href="javascript:editSupplier(\'' +full.supplierId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit Supplier">\
							<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteSupplier(\'' +full.supplierId+'\',\''+full.supplierName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Supplier">\
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

function clearNewSupplierForm(){
	$('.error').remove();
	$('#supplierExists').hide();
	$("span[id$='_span']").show();
	$('#validation_error').remove();
	$('#supplierFullName').val('');
	$('#supplierEmail').val('');
	$('#supplierNumber').val('');
	$('#supplier_address').val('');
	$('#supplierPincode').val('');
	$('#supplierGstnNo').val('');
}

function clearEditSupplierForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function editSupplier(id){
	$.ajax({
		url: HOST_URL + '/buy/supplier/editSupplier/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){
						$('#edit_supplierId').val(v.supplierId);
						$('#edit_supplierName').val(v.supplierName);
						$('#edit_supplier_address').val(v.supplier_address);
						$('#edit_supplierNumber').val(v.supplierNumber);
						$('#edit_supplierPincode').val(v.supplierPincode);
						$('#edit_supplierEmail').val(v.supplierEmail);						  
						if(v.supplierGstnNo == 'No GSTN No Provided'){
							$('#edit_supplierGstnNo').val('');
						}else{
							$('#edit_supplierGstnNo').val(v.supplierGstnNo);
						}						  							  
					});
				}
			});
			$('#editSupplierModal').modal();
		}
	});
}

function deleteSupplier(id,supplierName){
	Swal.fire({
		title: "Are you sure you want to delete " + supplierName+ "!",
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
			return fetch(`${HOST_URL}/buy/supplier/deleteSupplier/${id}`)
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
				title: supplierName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('buy/addSupplier');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#supplier_nav').addClass('menu-item-active');
}

var handleForms = function () {
	var _handleCreateForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('supplierForm'),
				{
					fields: {
						supplierName: {
							validators: {
								notEmpty: {
									message: 'Please enter supplier full name'
								}
							}
						},
						supplierNumber: {
							validators: {
								notEmpty: {
									message: 'Please enter supplier number'
								},
								regexp : {
									regexp : /^[0-9]{10,10}$/,
									message : 'please enter a valid mobile number (10 digits).'									
								}
							}						
						},
						supplierPincode: {
							validators: {
								regexp : {
									regexp : /^[0-9]{6,6}$/,
									message : 'please enter a valid pin code (6 digits).'									
								}
							}
						},
						supplierEmail: {
							validators: {
								emailAddress: {
									message: 'The value is not a valid email address'
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
		$('#createSupplierBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("supplierForm").action = "addSupplier";
					document.getElementById("supplierForm").submit();
				}
			});
		});
	}
	var _handleEditForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('editSupplierForm'),
				{
					fields: {
						supplierName: {
							validators: {
								notEmpty: {
									message: 'Please enter supplier full name'
								}
							}
						},
						supplierNumber: {
							validators: {
								notEmpty: {
									message: 'Please enter supplier number'
								},
								regexp : {
									regexp : /^[0-9]{10,10}$/,
									message : 'please enter a valid mobile number (10 digits).'									
								}
							}						
						},
						supplierPincode: {
							validators: {
								regexp : {
									regexp : /^[0-9]{6,6}$/,
									message : 'please enter a valid pin code (6 digits).'									
								}
							}
						},
						supplierEmail: {
							validators: {
								emailAddress: {
									message: 'The value is not a valid email address'
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
		$('#editSupplierBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("editSupplierForm").action = "buy/supplier/editSupplier/"+$('#edit_supplierId').val();
					document.getElementById("editSupplierForm").submit();
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
		$('#newSupplierModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
	handleForms.init();
});
