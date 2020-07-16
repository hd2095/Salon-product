'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#client_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/client/getAllClients',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'fullName'},
				{data: 'mobileNumber'},
				{data: 'emailId'},
				{data: 'gender'},
				{
					data: 'revenue_generated',
					render: function(revenue_generated){
						return '<p> &#8377; ' + revenue_generated + '</p>';
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
						return '\
							<a href="javascript:editClient(\'' +full.clientId+'\');" class="btn btn-xs btn-custom" title="Edit Client">\
								<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteClient(\'' +full.clientId+'\',\''+full.fullName+'\');" class="btn btn-xs btn-custom" title="Delete Client">\
								<i class="lnr lnr-trash"></i>\
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

function submitForm(){	
	document.clientForm.action = "";
	document.getElementById("clientForm").submit();
}

function submitEditForm(){	
	document.editClientForm.action = "client/editClient/"+$('#edit_clientId').val();
	document.getElementById("editClientForm").submit();
}

function editClient(id){
	$.ajax({
		url: HOST_URL + '/client/editClient/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				  if(key == 'data'){					  
					  $.each(value, function(k,v){
						  	$('#edit_clientId').val(v.clientId);
						  	$('#edit_fullName').val(v.fullName);		
						  	$('#edit_emailId').val(v.emailId);
						  	$('#edit_mobileNumber').val(v.mobileNumber);						  	
						  	if(v.gender == 'Female'){
						  		$('#edit_male').attr('checked',false);
						  		$('#edit_female').attr('checked',true);
						  	}else{
						  		$('#edit_female').attr('checked',false);
						  		$('#edit_male').attr('checked',true);
						  	}		
						  	if(v.isMember == 1){
						  		$('#edit_isMember').attr('checked',true);	
						  		$("#edit_client_start_date").datepicker('setDate',formattedDate(v.client_start_date));
						  		$('#edit_client_end_date').datepicker('setDate',formattedDate(v.client_end_date));
						  		isEditMemberValue();
						  	}else{
						  		$('.edit_client-duration-details').hide();
						  		$('#edit_isMember').attr('checked',false);	
						  	}
						  	$('#edit_client_birthday').datepicker('setDate',formattedDate(v.birthday));
						  	$('#edit_clientPincode').val(v.clientPincode);
						  	$('#edit_address').val(v.client_address);
						  	
					  });
				  }
				});
			$('#editClientModal').modal();
		}
	});
}

function formattedDate(date){
	var tokens = date.split(" ");
	var month = "";
	switch(tokens[0]){
	case "Jan":
		month = "01";
		break;
	case "Feb":
		month = "02";
		break;
	case "Mar":
		month = "02";
		break;
	case "Apr":
		month = "02";
		break;
	case "May":
		month = "02";
		break;
	case "Jun":
		month = "02";
		break;
	case "Jul":
		month = "02";
		break;
	case "Aug":
		month = "02";
		break;
	case "Sep":
		month = "02";
		break;
	case "Oct":
		month = "02";
		break;
	case "Nov":
		month = "02";
		break;
	case "Dec":
		month = "02";
		break;
	}
	var day = tokens[1];
	var year = tokens[2];
	date = month + "/" + day + "/" +year;
	return date;
}

function deleteClient(id,clientName){
	Swal.fire({
		title: "Are you sure you want to delete " + clientName+ "!",
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
			return fetch(`${HOST_URL}/client/deleteClient/${id}`)
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
					title: clientName + " deleted successfully!",
					confirmButtonText: "OK"
				}).then(function(result){
					if(result.value){
						location.replace('client');
					}
				});
			}
		});	
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#client_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newClientModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
