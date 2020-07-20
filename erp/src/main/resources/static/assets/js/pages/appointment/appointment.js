'use strict';
var KTDatatablesDataSourceAjaxClient = function() {
	var initTable1 = function() {
		var table = $('#appointment_dataTable');
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/appointment/getAllAppointments',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'appointmentDate',
					render: function(appointmentDate){
						return appointmentDate.split("12:")[0];
					}
				},
				{data: 'appointmentStartTime'},
				{data: 'service.serviceName'},
				{data: 'staff.fullName'},
				{data: 'client.fullName'},
				{data: 'service.serviceCost',
					render : function(service){
						return '<p> &#8377; ' + service + '</p>'; 
					}	
				},
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
							<a href="appointment/editAppointment/'+full.appointmentId+'"  class="btn btn-xs btn-custom" title="Edit Appointment">\
							<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-xs btn-custom" title="Delete Appointment">\
							<i class="lnr lnr-trash"></i>\
							</a>\
							';
						},
					},
					],
		});
	};
	return {		
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
			return fetch(`${HOST_URL}/appointment/deleteAppointment/${id}`)
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
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#appointment_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function() {
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
})