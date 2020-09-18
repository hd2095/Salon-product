'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#appointment_dataTable');

		// begin first table
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
				{data: 'appointmentDate'},
				{data: 'client.fullName'},
				{data: 'appointmentExpectedTotal'},	
				{data: 'appointmentStartTime'},	
				{data: 'appointmentStatus'},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {	
							if(full.appointmentStatus == 'Completed'){
								return '\
								<a href="appointment/generateAppointmentInvoice/'+full.appointmentId+'"  class="btn btn-sm btn-clean btn-icon" title="Generate Appointment Invoice">\
								<i class="la la-cog"></i>\
								</a>\
								<a href="appointment/editAppointment/'+full.appointmentId+'"  class="btn btn-sm btn-clean btn-icon" title="Edit Appointment">\
								<i class="la la-edit"></i>\
								</a>\
								<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Appointment">\
								<i class="la la-trash"></i>\
								</a>\
								';
							}else{
								return '\
								<a href="appointment/editAppointment/'+full.appointmentId+'"  class="btn btn-sm btn-clean btn-icon" title="Edit Appointment">\
								<i class="la la-edit"></i>\
								</a>\
								<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Appointment">\
								<i class="la la-trash"></i>\
								</a>\
								';	
							}							
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

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#appointment_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

jQuery(document).ready(function() {
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
})