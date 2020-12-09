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
							<i class="la la-eye"></i>\
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
	var element = $('#clientForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#clientForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#clientForm').find('.fv-plugins-message-container');
	element.html('');	
}

function clearEditClientForm(){
	$('.error').remove();
	$('#editClientExists').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
	var element = $('#editClientForm').find('.is-invalid');
	element.removeClass('is-invalid');
	element = $('#editClientForm').find('.is-valid');
	element.removeClass('is-valid');
	element = $('#editClientForm').find('.fv-plugins-message-container');
	element.html('');
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
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#client_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForms = function () {	
	var _handleCreateForm = function() {
		var validation;
		const clientForm = document.getElementById('clientForm');
		validation = FormValidation.formValidation(
				clientForm,
				{
					fields: {
						fullName : {
							validators : {
								notEmpty : {
									message : 'please enter client\'s full name'
								}
							}
						},
						mobileNumber : {
							validators : {
								notEmpty : {
									message : 'please enter client\'s mobile number'
								},
								regexp : {
									regexp : /^[0-9]{10,10}$/,
									message : 'please enter a valid mobile number (10 digits).'
								}
							}
						},
						clientLoyaltyPoints:{
							validators: {
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value.'									
								}
							}
						},
						clientPincode: {
							validators: {
								regexp : {
									regexp : /^[0-9]{6,6}$/,
									message : 'please enter a valid pin code (6 digits).'									
								}
							}
						},
						emailId: {
							validators: {
								emailAddress: {
									message: 'The value is not a valid email address'
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
		$('#submitNewClientForm').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {		
					if(REDIRECT_TO != ""){
						document.getElementById("clientForm").action = 'client?redirectTo='+REDIRECT_TO;
						document.getElementById("clientForm").submit();
					}else{
						document.getElementById("clientForm").action = 'client';
						document.getElementById("clientForm").submit();	
					}
				} else {
					$('#basicTab').click();
				}
			});
		});
	}
	var _handleEditForm = function() {
		var validation;
		const clientForm = document.getElementById('editClientForm');
		validation = FormValidation.formValidation(
				clientForm,
				{
					fields: {
						fullName : {
							validators : {
								notEmpty : {
									message : 'please enter client\'s full name'
								}
							}
						},
						mobileNumber : {
							validators : {
								notEmpty : {
									message : 'please enter client\'s mobile number'
								},
								regexp : {
									regexp : /^[0-9]{10,10}$/,
									message : 'please enter a valid mobile number (10 digits).'
								}
							}
						},
						clientPincode: {
							validators: {
								regexp : {
									regexp : /^[0-9]{6,6}$/,
									message : 'please enter a valid pin code (6 digits).'									
								}
							}
						},
						clientLoyaltyPoints:{
							validators: {
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value.'									
								}
							}
						},
						emailId: {
							validators: {
								emailAddress: {
									message: 'The value is not a valid email address'
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
		$('#submitEditClientForm').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {										
					document.getElementById("editClientForm").action = "client/editClient";
					document.getElementById("editClientForm").submit();
				} else {
					$('#editBasicTab').click();
				}
			});
		});
	}
	return {
		init: function() {
			_handleCreateForm();
			_handleEditForm();
		}
	};
}();

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newClientModal').modal();
	}
	setLinkActive();	
	KTDatatablesDataSourceAjaxClient.init();
	fetchClientPlan();	
	handleForms.init();
	$('#loading-spinner').hide();
});
