'use strict';

function clearNewCategoryForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#categoryAlreadyExists').hide();
	$("span[id$='_span']").show();
	$('#categoryDescription').val('');
	$('#categoryName').val('');	
}

function clearEditCategoryForm(){
	$('#editCategoryAlreadyExists').hide();
	$('#validation_error').remove();
	$('.error').remove();
	$("span[id$='_span']").show();
}

function clearNewServiceForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#serviceAlreadyExists').hide();
	$("span[id$='_span']").show();
	$('#serviceName').val('');
	$('#service_duration').val('');	
	$('#serviceCost').val(0.0);	
}

function clearEditServiceForm(){
	$('.error').remove();
	$('#editServiceAlreadyExists').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function submitServiceForm(){
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var serviceName = $('#serviceName').val();
	var serviceDuration = $('#service_duration').val();
	var serviceCost = $('#serviceCost').val();
	if(serviceName.length < 1){
		$('#serviceName').after('<span id="serviceName_error" class="error">please enter service name</span>');
		$('#serviceName_span').hide();
		valid = false;	
	}else{
		$('#serviceName_span').show();
		$('#serviceName_error').hide();
	}
	if(serviceDuration.length < 1){
		$('#service_duration_span').after('<span id="service_duration_error" class="error">please enter service duration</span>');
		$('#service_duration_span').hide();
		valid = false;	
	}else{
		$('#service_duration_span').show();
		$('#service_duration_error').hide();
	}
	if(isNaN(serviceCost)){
		$('#serviceCost').after('<span id="serviceCost_error" class="error">Only numeric data allowed</span>');
		$('#serviceCost_span').hide();
		valid = false;	
	}else{
		if(Math.sign(serviceCost) == -1){
			$('#serviceCost').after('<span id="serviceCost_error" class="error">Service Cost cant be negative</span>');
			$('#serviceCost_span').hide();
			valid = false;	
		}else{
			$('#serviceCost_error').hide();
			$('#serviceCost_span').show();	
		}
	}
	if(valid){
		document.serviceForm.action = "services/create";
		document.getElementById("serviceForm").submit();
	}
}

function submitEditServiceForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var serviceName = $('#edit_serviceName').val();
	var serviceDuration = $('#edit_service_duration').val();
	var serviceCost = $('#edit_serviceCost').val();
	if(serviceName.length < 1){
		$('#edit_serviceName').after('<span id="edit_serviceName_error" class="error">please enter service name</span>');
		$('#edit_serviceName_span').hide();
		valid = false;	
	}else{
		$('#edit_serviceName_span').show();
		$('#edit_serviceName_error').hide();
	}
	if(serviceDuration.length < 1){
		$('#edit_service_duration_span').after('<span id="edit_service_duration_error" class="error">please enter service duration</span>');
		$('#edit_service_duration_span').hide();
		valid = false;	
	}else{
		$('#edit_service_duration_span').show();
		$('#edit_service_duration_error').hide();
	}
	if(isNaN(serviceCost)){
		$('#edit_serviceCost').after('<span id="edit_serviceCost_error" class="error">Only numeric data allowed</span>');
		$('#serviceCost_span').hide();
		valid = false;	
	}else{
		if(Math.sign(serviceCost) == -1){
			$('#edit_serviceCost').after('<span id="edit_serviceCost_error" class="error">Service Cost cant be negative</span>');
			$('#serviceCost_span').hide();
			valid = false;	
		}else{
			$('#edit_serviceCost').hide();
			$('#serviceCost_span').show();	
		}
	}
	if(valid){
		document.editServiceForm.action = "services/editService/"+$('#edit_serviceId').val();
		document.getElementById("editServiceForm").submit();
	}
}

function submitCategory(){
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var categoryName = $('#categoryName').val();
	if (categoryName.length < 1) {
		$('#categoryName').after('<span id="categoryName_error" class="error">please enter category name</span>');
		$('#categoryName_span').hide();
		valid = false;
	}else{
		$('#categoryName_span').show();
		$('#categoryName_error').hide();
	}
	if(valid){
		document.categoryForm.action = "category";
		document.getElementById("categoryForm").submit();
	}
}

function submitEditCategory(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var categoryName = $('#editCategoryName').val();
	if (categoryName.length < 1) {
		$('#editCategoryName').after('<span id="editCategoryName_error" class="error">please enter category name</span>');
		$('#editcategoryName_span').hide();
		valid = false;
	}else{
		$('#editcategoryName_span').show();
		$('#editCategoryName_error').hide();
	}
	if(valid){
		document.editCategoryForm.action = "category/editCategory/"+$('#edit_categoryId').val();
		document.getElementById("editCategoryForm").submit();
	}
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
			_handleCategoryForm();
			_handleServiceForm();
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
});
