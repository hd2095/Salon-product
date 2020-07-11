'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#service_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: '/services/getAllServices',
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
				{data: 'serviceCost'},
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
							<a href="javascript:editClient(\'' +full.clientId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit details">\
								<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteService(\'' +full.serviceId+'\',\''+full.serviceName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
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


function submitServiceForm(){	
	document.serviceForm.action = "services/create";
	document.getElementById("serviceForm").submit();
}

function editServiceForm(){	
	document.editClientForm.action = "client/editClient/"+$('#edit_clientId').val();
	document.getElementById("editClientForm").submit();
}

function submitCategory(){
	document.categoryForm.action = "category";
	document.getElementById("categoryForm").submit();
}

function fetchCategory(){
	$.ajax({
		url: 'category/getAllCategories',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			console.log(response);
            for( var i = 0; i<response.length; i++){
                var category_name = response[i]['categoryName'];
                var category_id = response[i]['categoryId'];
                $("#category_dropdown").append("<option value='"+category_id+"'>"+category_name+"</option>");
               }
		}
	});
}

function deleteService(id,serviceName){
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
			return fetch(`/services/deleteService/${id}`)
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

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#services_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newClientModal').modal();
	}
	fetchCategory();
	KTDatatablesDataSourceAjaxClient.init();
});
