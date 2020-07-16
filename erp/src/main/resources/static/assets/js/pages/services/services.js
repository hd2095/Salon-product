'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#service_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/services/getAllServices',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'serviceName'},
				{data: 'category.categoryName'},
				{
					data: 'serviceCost',
					render: function(serviceCost){
						return '<p> &#8377; ' + serviceCost + '</p>';
					}	
				},
				{data: 'serviceDuration'},
				{data: 'serviceDescription'},					
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {	
						return '\
							<a href="javascript:editOrDeleteService(\'' +full.serviceId+'\');" class="btn btn-xs btn-custom" title="Edit or Delete Service">\
								<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:editOrDeleteCategory(\'' +full.category.categoryId+'\');" class="btn btn-xs btn-custom" title="Edit or Delete Category">\
								<i class="lnr lnr-pencil"></i>\
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


function submitServiceForm(){	
	document.serviceForm.action = "services/create";
	document.getElementById("serviceForm").submit();
}

function submitEditServiceForm(){	
	document.editServiceForm.action = "services/editService/"+$('#edit_serviceId').val();
	document.getElementById("editServiceForm").submit();
}

function submitCategory(){
	document.categoryForm.action = "category";
	document.getElementById("categoryForm").submit();
}

function submitEditCategory(){	
	document.editCategoryForm.action = "category/editCategory/"+$('#edit_categoryId').val();
	document.getElementById("editCategoryForm").submit();
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
	$.ajax({
		url:HOST_URL +'/services/editService/'+serviceId,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				  if(key == 'data'){					  
					  $.each(value, function(k,v){
						  	$('#edit_serviceCost').val(v.serviceCost);
						  	$('#edit_serviceId').val(v.serviceId);
						  	$('#edit_service_categoryId').val(v.category.categoryId);
						  	$('#edit_service_duration').val(v.serviceDuration);
						  	$('#editServiceDescription').val(v.serviceDescription);
						  	$('#edit_serviceName').val(v.serviceName);
						  	$('#edit_service_category').append("<option value='"+v.category.categoryId+"' selected>"+v.category.categoryName+"</option>");
					  });
				  }
				});
			$('#editServiceModal').modal();
		}
	});
}

function editOrDeleteCategory(categoryId){
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
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#services_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newServiceModal').modal();
	}
	fetchCategory();
	KTDatatablesDataSourceAjaxClient.init();
	setLinkActive();
});
