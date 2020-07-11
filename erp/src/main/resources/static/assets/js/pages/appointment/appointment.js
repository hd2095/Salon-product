'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#appointment_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: '/appointment/getAllAppointments',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'appointmentDate'},
				{data: 'appointmentStartTime'},
				{data: 'service.serviceName'},
				{data: 'staff.fullName'},
				{data: 'client.fullName'},
				{data: 'service.serviceCost'},
				{data: 'appointmentStatus'},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {			
							return '\
							<a href="appointment/editAppointment/'+full.appointmentId+'" class="btn btn-sm btn-clean btn-icon" title="Edit Staff">\
							<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
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


function deleteAppointment(id,clientName){
	Swal.fire({
		title: "Are you sure you want to delete appointment for " + clientName+ "!",
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
			return fetch(`/appointment/deleteAppointment/${id}`)
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
				title:"Appointment for "+ clientName +  " deleted successfully!",
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
	$('#inventory_nav').removeClass('menu-item-open');
	$('#appointment_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
