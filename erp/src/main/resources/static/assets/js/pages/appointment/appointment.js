'use strict';
var KTDatatablesDataSourceAjaxClient = function() {
	var initTable = function() {
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
				{data: 'appointmentDate',
					render: function(appointmentDate){
						return appointmentDate.substring(0,appointmentDate.lastIndexOf(','));
					}				
				},
				{data: 'client.fullName'},
				{data: 'appointmentExpectedTotal'},	
				{data: 'appointmentStartTime'},	
				{	
					data: 'appointmentStatus',
					render: function(data,type,full,meta){
						var status = {							
								Cancelled: {'title': 'Cancelled', 'class': ' label-light-danger'},
								Completed: {'title': 'Completed', 'class': ' label-light-success'},									
								Booked: {'title': 'Booked', 'class': ' label-light-info'},
								NoShow: {'title': 'No Show', 'class': ' label-light-warn'},
						};
						if (typeof status[data] === 'undefined') {
							return data;
						}
						if(status[data].title != 'Completed' && status[data].title != 'No Show' && status[data].title != 'Cancelled'){
							return '\
							<div class="dropdown dropdown-inline">\
							<a href="javascript:;" class="btn btn-sm btn-clean" data-toggle="dropdown">\
							<span class="label label-lg font-weight-bold' + status[data].class + ' label-inline">' + status[data].title + '</span>\
							</a>\
							<div class="dropdown-menu dropdown-menu-right">\
							<ul class="nav nav-hoverable flex-column">\
							<li class="nav-item"><a class="nav-link" href="javascript:completeAppointment(\''+full.appointmentId+'\');"><span class="nav-text font-weight-bold">Complete Appointment</span></a></li>\
							</ul>\
							</div>\
							</div>\
							';
						}else{
							return '<span class="label label-lg font-weight-bold' + status[data].class + ' label-inline">' + status[data].title + '</span>';
						}
					}
				},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {	
							if(full.appointmentInvoiceGenerated){
								return '\
								<a href="appointment/viewAppointmentDetails/' +full.appointmentId+'" class="btn btn-sm btn-clean btn-icon" title="View Appointment Details">\
								<i class="la la-eye"></i>\
								</a>\
								<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Appointment">\
								<i class="la la-trash"></i>\
								</a>\
								';
							}else if(full.appointmentStatus == 'Completed'){
								return '\
								<a href="appointment/viewAppointmentDetails/' +full.appointmentId+'" class="btn btn-sm btn-clean btn-icon" title="View Appointment Details">\
								<i class="la la-eye"></i>\
								</a>\
								<a href="appointment/generateAppointmentInvoice/'+full.appointmentId+'"  class="btn btn-sm btn-clean btn-icon" title="Generate Appointment Invoice">\
								<i class="la la-file-invoice"></i>\
								</a>\
								<a href="javascript:deleteAppointment(\'' +full.appointmentId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Appointment">\
								<i class="la la-trash"></i>\
								</a>\
								';
							}
							else{
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
			initTable();
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

function completeAppointment(id){
	Swal.fire({
		title: "Are you sure you want to complete this appointment?",
		icon: "warning",		  
		confirmButtonText: "Yes, Complete it!",
		showCancelButton: true,
		cancelButtonText: "No, Cancel!",
		customClass: {
			confirmButton: "btn btn-danger",
			cancelButton: "btn btn-default"
		},
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return fetch(`${HOST_URL}/appointment/updateAppointment/${id}`)
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
				title:"Appointment updated successfully!",
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

function setLocalStorage(){
	$.ajax({
		url: HOST_URL + '/setLocalStorage',
		success:function(response){	
			if(response != ''){
				localStorage.setItem('user',response);	
			}
		}
	});
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
	setLocalStorage();
})