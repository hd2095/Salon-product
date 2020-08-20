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
							<a href="javascript:fetchClientDetails(\'' +full.clientId+'\');" class="btn btn-sm btn-clean btn-icon" title="View Client Details">\
							<i class="la la-cog"></i>\
							</a>\
							<a href="javascript:editClient(\'' +full.clientId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit Client">\
							<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteClient(\'' +full.clientId+'\',\''+full.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Client">\
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

function clearNewClientForm(){
	$('.error').remove();
	$("span[id$='_span']").show();
	$('#clientExists').hide();
	$('#validation_error').remove();
	$('#fullName').val('');
	$('#mobileNumber').val('');
	$('#clientPincode').val('');
	$('#emailId').val('');
	$('#client_address').val('');
	$('#client_birthday').val('');
	$('#client_start_date').val('');
	$('#client_end_date').val('');
	$('#isMember').prop('checked',false);
	$('.client-duration-details').hide();
}

function clearEditClientForm(){
	$('.error').remove();
	$('#editClientExists').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	$('#clientExists').hide();
	var valid = true;
	var full_name = $('#fullName').val();
	var client_mobileNumber = $('#mobileNumber').val();
	var pincode = $('#clientPincode').val();
	var emailId = $('#emailId').val();
	var client_address = $('#client_address').val();
	if (full_name.length < 1) {
		$('#fullName').after('<span id="fullName_error" class="error">please enter client Full Name</span>');
		$('#fullName_span').hide();
		valid = false;
	}else{
		$('#fullName_span').show();
		$('#fullName_error').hide();
	}
	if(client_mobileNumber < 1){
		$('#mobileNumber_span').after('<span id="mobileNumber_error" class="error">please enter client mobile number</span>');
		$('#mobileNumber_span').hide();
		valid = false;
	}else if(client_mobileNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var mobileNumber = regEx.test(client_mobileNumber);
		if (!mobileNumber) {
			valid = false;
			$('#mobileNumber_span').after('<span id="mobileNumber_error" class="error">Enter a valid mobile number</span>');
			$('#mobileNumber_span').hide();
		}else{
			$('#mobileNumber_error').hide();
			$('#mobileNumber_span').show();
		}
	}else{
		valid = false;
		$('#mobileNumber_span').after('<span id="mobileNumber_error" class="error">Enter a valid mobile number (10 digits)</span>');
		$('#mobileNumber_span').hide();
	}
	if(pincode < 1){
		valid = false;
		$('#clientPincode_span').after('<span id="clientPincode_error" class="error">please enter client pin code</span>');
		$('#clientPincode_span').hide();
	}else if(pincode.length == 6){
		if(isNaN(pincode)){
			valid = false;
			$('#clientPincode_span').after('<span id="clientPincode_error" class="error">Invalid client pin code enter 6 digits</span>');
			$('#clientPincode_span').hide();
		}else{
			$('#clientPincode_span').show();	
		}
	}else{
		valid = false;
		$('#clientPincode_span').after('<span id="clientPincode_error" class="error">Invalid client pin code enter 6 digits</span>');
		$('#clientPincode_span').hide();
	}
	if(emailId.length > 1){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(emailId);
		if (!validEmail) {
			valid = false;
			$('#emailId_span').hide();
			$('#emailId_span').after('<span id="email_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(emailId).toLowerCase());
			$('#emailId_span').show();
			$('#email_error').hide();
		}
	}
	if(client_address < 1){
		valid = false;
		$('#client_address_span').after('<span id="client_address_error" class="error">please enter client address</span>');
		$('#client_address_span').hide();
	}else{
		$('#client_address_span').show();
		$('#client_address_error').hide();
	}
	if(valid){
		//showLoadingDiv();
		document.clientForm.action = "";
		document.getElementById("clientForm").submit();
	}
}


function submitEditForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$('#editClientExists').hide();
	var valid = true;
	var full_name = $('#edit_fullName').val();
	var client_mobileNumber = $('#edit_mobileNumber').val();
	var pincode = $('#edit_clientPincode').val();
	var emailId = $('#edit_emailId').val();
	var client_address = $('#edit_address').val();
	if (full_name.length < 1) {
		$('#edit_fullName').after('<span id="edit_fullName_error" class="error">please enter client Full Name</span>');
		$('#edit_fullName_span').hide();
		valid = false;
	}else{
		$('#edit_fullName_span').show();
		$('#edit_fullName_error').hide();
	}
	if(client_mobileNumber < 1){
		$('#edit_mobileNumber_span').after('<span id="edit_mobileNumber_error" class="error">please enter client mobile number</span>');
		$('#edit_mobileNumber_span').hide();
		valid = false;
	}else if(client_mobileNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var mobileNumber = regEx.test(client_mobileNumber);
		if (!mobileNumber) {
			valid = false;
			$('#edit_mobileNumber_span').after('<span id="edit_mobileNumber_error" class="error">Enter a valid mobile number</span>');
			$('#edit_mobileNumber_span').hide();
		}else{
			$('#edit_mobileNumber_error').hide();
			$('#edit_mobileNumber_span').show();
		}
	}else{
		valid = false;
		$('#edit_mobileNumber_span').after('<span id="edit_mobileNumber_error" class="error">Enter a valid mobile number (10 digits)</span>');
		$('#edit_mobileNumber_span').hide();
	}
	if(pincode < 1){
		valid = false;
		$('#edit_clientPincode_span').after('<span id="edit_clientPincode_error" class="error">please enter client pin code</span>');
		$('#edit_clientPincode_span').hide();
	}else if(pincode.length == 6){
		if(isNaN(pincode)){
			valid = false;
			$('#edit_clientPincode_span').after('<span id="edit_clientPincode_error" class="error">Invalid client pin code enter 6 digits</span>');
			$('#edit_clientPincode_span').hide();
		}else{
			$('#edit_clientPincode_span').show();	
		}
	}else{
		valid = false;
		$('#edit_clientPincode_span').after('<span id="edit_clientPincode_error" class="error">Invalid client pin code enter 6 digits</span>');
		$('#edit_clientPincode_span').hide();
	}
	if(emailId < 1){
		valid = false;
		$('#edit_emailId_span').after('<span id="edit_email_error" class="error">please enter client email id</span>');
		$('#edit_emailId_span').hide();
	}else if(emailId.length > 1){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(emailId);
		if (!validEmail) {
			valid = false;
			$('#edit_emailId_span').hide();
			$('#edit_emailId_span').after('<span id="edit_email_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(emailId).toLowerCase());
			$('#edit_emailId_span').show();
			$('#edit_email_error').hide();
		}
	}
	if(client_address < 1){
		valid = false;
		$('#edit_address_span').after('<span id="edit_client_address_error" class="error">please enter client address</span>');
		$('#edit_address_span').hide();
	}else{
		$('#edit_address_span').show();
		$('#edit_client_address_error').hide();
	}
	if(valid){
		document.editClientForm.action = "client/editClient/"+$('#edit_clientId').val();
		document.getElementById("editClientForm").submit();
	}
}

function editClient(id){
	clearEditClientForm();
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
						$('#editLoyaltyPoints').val(v.clientLoyaltyPoints);			
						if(undefined != v.clientPlan){
							$("#edit_client_plan_dropdown").val(v.clientPlan.planId).trigger('change');
						}
						$("#editClientCreatedDate").datepicker('setDate',formattedDate(v.clientCreatedDate));
						$("#edit_client_start_date").datepicker('setDate',formattedDate(v.client_start_date));
						$('#edit_client_end_date').datepicker('setDate',formattedDate(v.client_end_date));
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

function fetchClientDetails(id){
	$.ajax({
		url: HOST_URL + '/client/clientDetails/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){
						$('#totalVisits').val(v.clientVisits);						  			
						$('#clientDetailsRevenueGenerated').val(v.revenue_generated);					
						$("#clientDetailsLastVisited").datepicker('setDate',formattedDate(v.clientLastVisitedDate));
						$("#clientDetailsCreatedDate").datepicker('setDate',formattedDate(v.clientCreatedDate));
					});
				}
			});
			$('#clientDetailsModal').modal();
		}
	});
}

function fetchClientPlan(){
	$.ajax({
		url: HOST_URL + '/getAllClientPlan',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.length; i++){
				var planName = response[i]['planName'];
				var planId = response[i]['planId'];
				if(i == 0){
					$("#edit_client_plan_dropdown").append("<option value=' '>Select a Plan</option>");
					$("#edit_client_plan_dropdown").append("<option value='"+planId+"'>"+planName+"</option>");					
					$("#client_plan_dropdown").append("<option value='" + i + "'>Select a Plan</option>");
					$("#client_plan_dropdown").append("<option value='"+planId+"'>"+planName+"</option>");
				}else{		
					$("#edit_client_plan_dropdown").append("<option value='"+planId+"'>"+planName+"</option>");
					$("#client_plan_dropdown").append("<option value='"+planId+"'>"+planName+"</option>");
				}
			}
		}
	});
}

function formattedDate(date){
	if(undefined != date){
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
			month = "03";
			break;
		case "Apr":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "Jun":
			month = "06";
			break;
		case "Jul":
			month = "07";
			break;
		case "Aug":
			month = "08";
			break;
		case "Sep":
			month = "09";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		}
		var day = tokens[1];
		var year = tokens[2];
		date = month + "/" + day + "/" +year;
	}
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
	fetchClientPlan();
});
