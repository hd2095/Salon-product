'use strict';

function clearNewCategoryForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#categoryAlreadyExists').hide();
	$("span[id$='_span']").show();
	$('#categoryDescription').val('');
	$('#categoryName').val('');	
	var element = $('#categoryForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#categoryForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#categoryForm').find('.fv-plugins-message-container');
	element.html('');
}

function clearEditCategoryForm(){
	$('#editCategoryAlreadyExists').hide();
	$('#validation_error').remove();
	$('.error').remove();
	$("span[id$='_span']").show();
	var element = $('#editCategoryForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#editCategoryForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#editCategoryForm').find('.fv-plugins-message-container');
	element.html('');
}

function clearNewServiceForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#serviceAlreadyExists').hide();
	$("span[id$='_span']").show();
	$('#serviceName').val('');
	$('#service_duration').val('');	
	$('#serviceCost').val(0.0);	
	var element = $('#serviceForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#serviceForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#serviceForm').find('.fv-plugins-message-container');
	element.html('');
}

function clearEditServiceForm(){
	$('.error').remove();
	$('#editServiceAlreadyExists').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
	var element = $('#editServiceForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#editServiceForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#editServiceForm').find('.fv-plugins-message-container');
	element.html('');
}

function fetchCategory(){
	$.ajax({
		url: HOST_URL + '/category/getAllCategories',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.length; i++){
				var category_name = response[i]['categoryName'];
				var category_id = response[i]['categoryId'];
				$("#category_dropdown").append("<option value='"+category_id+"'>"+category_name+"</option>");
			}
		}
	});
}

function editOrDeleteService(serviceId){
	clearEditServiceForm();
	$.ajax({
		url:HOST_URL +'/services/editService/'+serviceId,
		type: 'get',
		dataType: 'json',
		success:function(response){
			var array = [];
			array.push(response.data);
			for(var i = 0;i<array.length;i++){
				$('#edit_serviceCost').val(array[i]['serviceCost']);
				$('#edit_serviceId').val(array[i]['serviceId']);
				$('#edit_service_categoryId').val(array[i]['category']['categoryId']);
				$('#edit_service_duration').val(array[i]['serviceDuration']);
				$('#editServiceDescription').val(array[i]['serviceDescription']);
				$('#edit_serviceName').val(array[i]['serviceName']);
				$('#edit_service_category').append("<option value='"+array[i]['category']['categoryId']+"' selected>"+array[i]['category']['categoryName']+"</option>");

			}
			$('#editServiceModal').modal();
		}
	});
}

function editOrDeleteCategory(categoryId){
	clearEditCategoryForm();
	$.ajax({
		url: HOST_URL + '/category/editCategory/'+categoryId,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){			
						$('#edit_categoryId').val(v.categoryId);
						$('#editCategoryDescription').val(v.categoryDescription);
						$('#editCategoryName').val(v.categoryName);
					});
				}
			});
			$('#editCategoryModal').modal();
		}
	});

}

function deleteService(){
	var id = $('#edit_serviceId').val();
	var serviceName = $('#edit_serviceName').val();
	Swal.fire({
		title: "Are you sure you want to delete " + serviceName + "!",
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
			return fetch(`${HOST_URL}/services/deleteService/${id}`)
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
				title: serviceName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.reload();
				}
			});
		}
	});	
}

function deleteCategory(){
	var id = $('#edit_categoryId').val();
	var categoryName = $('#editCategoryName').val();
	Swal.fire({
		title: "Are you sure you want to delete " + categoryName + " !",
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
			return fetch(`${HOST_URL}/category/deleteCategory/${id}`)
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
				title: categoryName +" deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.reload();
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#services_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForms = function () {
	var _handleCategoryForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('categoryForm'),
				{
					fields: {
						categoryName: {
							validators: {
								notEmpty: {
									message: 'Please enter category name'
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
		$('#createCategoryBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("categoryForm").action = "category";
					document.getElementById("categoryForm").submit();
				}
			});
		});
	}
	var _handleEditCategoryForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('editCategoryForm'),
				{
					fields: {
						categoryName: {
							validators: {
								notEmpty: {
									message: 'Please enter category name'
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
		$('#editCategoryBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("editCategoryForm").action = "category/editCategory";
					document.getElementById("editCategoryForm").submit();
				}
			});
		});
	}
	var _handleServiceForm = function() {
		var validation;
		const category_dropdown = jQuery(KTUtil.getById('serviceForm').querySelector('[name="category"]'));
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
						},
						serviceCost: {
							validators: {
								regexp: {
									regexp : /^[0-9]*$/,
									message: 'Please enter positive numeric value'
								}
							}
						},
						category : {
							validators : {
								callback : {
									message : 'Please select/create category',
									callback : function(input) {
										// Get the selected options
										const options = category_dropdown.select2('data');
										return (options != null && options != '');
									}
								}
							}
						},
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
	var _handleEditServiceForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('editServiceForm'),
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
						},
						serviceCost: {
							validators: {
								regexp: {
									regexp : /^[0-9]*$/,
									message: 'Please enter positive numeric value'
								}
							}
						},
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
		$('#editServiceBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("editServiceForm").action = "services/editService/"+$('#edit_serviceId').val();
					document.getElementById("editServiceForm").submit();
				}
			});
		});
	}
	return {
		init: function() {
			_handleCategoryForm();
			_handleEditCategoryForm();
			_handleServiceForm();
			_handleEditServiceForm();
		}
	};
}();

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newServiceModal').modal();
	}
	fetchCategory();
	setLinkActive();
	handleForms.init();
	$('#newServiceBtn').click(function (){
		$('#service_duration').timepicker('setTime', '0:00');
	});
});
