'use strict';
var appointmentDataTable = function() {

	var initializeTable = function() {

		var datatable = $('#appointment_dataTable').KTDatatable({
			data: {
				type: 'remote',
				source: {
					read: {
						url: HOST_URL + '/appointment/getAllAppointments',
					},
				},
				pageSize: 10,
				serverPaging: true,
				serverFiltering: true,
				serverSorting: true,
			},
			// layout definition
			layout: {
				scroll: false,
				footer: false,
			},

			// column sorting
			sortable: true,

			pagination: true,

			detail: {
				title: 'Load sub table',
				content: subTableInit,
			},
			

			search: {
				input: $('#search_query'),
				key: 'generalSearch'
			},

			columns: [
				{
					field: 'appointmentId',
					title: '',
					sortable: false,
					width: 30,
					textAlign: 'center',
				},
				{field: 'appointmentDate',
					title: 'Appointment Date',
				},
				{field: 'appointmentExpectedTotal',
					title: 'Appointment Total',
				},
				{field: 'appointmentStartTime',
					title: 'Appointment Time',
				},
				{field: 'client.fullName',
					title: 'Client',
				},
				{
					field: 'Actions',
					width: 125,
					title: 'Actions',
					sortable: false,
					overflow: 'visible',
					autoHide: false,
					template: function(data) {
						return '\
						<a href="appointment/editAppointment/'+data.appointmentId+'"  class="btn btn-sm btn-clean btn-icon" title="Edit Appointment">\
						<i class="la la-edit"></i>\
						</a>\
						<a href="javascript:deleteAppointment(\'' +data.appointmentId+'\',\''+data.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Appointment">\
						<i class="la la-trash"></i>\
						</a>\
						';
					},
				}
				]	
		});

		function subTableInit(e) {
			$('<div/>').attr('id', 'child_data_ajax_' + e.data.appointmentId).appendTo(e.detailCell).KTDatatable({
				data: {
					type: 'remote',
					source: {
						read: {
							url: HOST_URL + '/appointment/getAppointmentDetails/'+e.data.appointmentId,
						},
					},
				},

				pagination: false,

				columns: [
					{field: 'staff.fullName',
						title: 'Staff Name',
					},
					{field: 'service.serviceName',
						title: 'Service Taken',
					},
					{field: 'serviceCost',
						title: 'Service Cost',
					},
					]
			});

		}
	}
	return {
		// Public functions
		init: function() {
			// init dmeo
			initializeTable();
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

/*function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#appointment_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}
*/
jQuery(document).ready(function() {
	//setLinkActive();
	appointmentDataTable.init();
})