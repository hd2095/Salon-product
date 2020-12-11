"use strict";

var serviceCategoryArray;
var staffArray;
var totalCost = 0;
var totalDuration;
function fetchServicesEdit(id,serviceId){
	if(undefined == serviceCategoryArray){
		$.ajax({
			url: HOST_URL + '/services/getAllServices',
			type: 'get',
			dataType: 'json',
			success: function(response){      
				serviceCategoryArray = response.data;
				$('select[name="['+ id +'][edit_appointment_service]"]').append("<option value='Select'>Select Service</option>");
				for(var key in response.data){
					if(response.data.hasOwnProperty(key)){
						$('select[name="['+ id +'][edit_appointment_service]"]').append("<optgroup label='"+key+"'></option>");
						var array = response.data[key];
						for(var i = 0; i<array.length; i++){
							var service_id = array[i]['serviceId'];
							var service_name = array[i]['serviceName'];
							var service_cost = array[i]['serviceCost'];
							service_name = service_name + ' ( &#8377 ' + service_cost + ' )';
							$('select[name="['+ id +'][edit_appointment_service]"]').append("<option value='"+service_id+"'>"+service_name+"</option>");
							if(service_id == serviceId){
								totalCost = totalCost + service_cost;
								$('#edit_appointment_cost').val(totalCost);	
								if(undefined != totalDuration){
									totalDuration = addDuration(totalDuration,array[i]['serviceDuration']);
								}else{
									totalDuration = array[i]['serviceDuration'];
								}
								$('#edit_total_appointment_duration').val(totalDuration);
								$('input[name="['+ id +'][edit_appointment_duration_hidden]"').val(array[i]['serviceDuration']);
								$('input[name="['+ id +'][edit_appointment_service_cost]"').val(service_cost);
								$('select[name="['+ id +'][edit_appointment_service]"]').val(serviceId);
								$('input[name="['+ id +'][edit_appointment_duration]"').val(array[i]['serviceDuration']);
							}							
						}					
					}
				}
			}
		});
	}else{
		$('select[name="['+ id +'][edit_appointment_service]"]').append("<option value='Select'>Select Service</option>");
		for(var key in serviceCategoryArray){
			if(serviceCategoryArray.hasOwnProperty(key)){
				$('select[name="['+ id +'][edit_appointment_service]"]').append("<optgroup label='"+key+"'></option>");
				var array = serviceCategoryArray[key];
				for(var i = 0; i<array.length; i++){
					var service_id = array[i]['serviceId'];
					var service_name = array[i]['serviceName'];
					var service_cost = array[i]['serviceCost'];
					service_name = service_name + ' ( &#8377 ' + service_cost + ' )';					
					$('select[name="['+ id +'][edit_appointment_service]"]').append("<option value='"+service_id+"'>"+service_name+"</option>");
					if(service_id == serviceId){
						$('input[name="['+ id +'][edit_appointment_service_cost]"').val(service_cost);
						$('input[name="['+ id +'][edit_appointment_duration_hidden]"').val(array[i]['serviceDuration']);
						$('select[name="['+ id +'][edit_appointment_service]"]').val(serviceId);
						$('input[name="['+ id +'][edit_appointment_duration]"').val(array[i]['serviceDuration']);
					}							
				}					
			}
		}
	}
}

function fetchStaffEdit(id,staffId){
	if(undefined == staffArray){
		$.ajax({
			url: HOST_URL + '/staff/getAllStaff',
			type: 'get',
			dataType: 'json',
			success: function(response){
				staffArray = response.data;
				$('select[name="['+ id +'][edit_appointment_staff]"').append("<option value='Select'>Select Staff</option>");
				for( var i = 0; i<response.data.length; i++){
					var staff_id = response.data[i]['staffId'];
					var staff_name = response.data[i]['fullName'];                
					$('select[name="['+ id +'][edit_appointment_staff]"').append("<option value='"+staff_id+"'>"+staff_name+"</option>");
					if(staff_id == staffId){
						$('select[name="['+ id +'][edit_appointment_staff]"]').val(staffId);
					}
				}
			}
		});
	}else{
		$('select[name="['+ id +'][edit_appointment_staff]"').append("<option value='Select'>Select Staff</option>");
		for( var i = 0; i<staffArray.length; i++){
			var staff_id = staffArray[i]['staffId'];
			var staff_name = staffArray[i]['fullName'];                
			$('select[name="['+ id +'][edit_appointment_staff]"').append("<option value='"+staff_id+"'>"+staff_name+"</option>");
		}
	}
}

function populateClient(id){	
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var clientId = response.data[i]['clientId'];
				var clientName = response.data[i]['fullName'];				
				$("#edit_appointment_client").append("<option value='"+clientId+"'>"+clientName+"</option>");
			}
			$("#edit_appointment_client").val(id).trigger('change');
		}
	});
}

function fetchAppointmentDetails(id){
	var fetchedDetails = true;
	localStorage.setItem("fetchedDetails", fetchedDetails); 
	$.ajax({
		url: HOST_URL + '/appointment/getAppointmentDetails/'+id,
		type: 'get',
		dataType: 'json',
		beforeSend: function(){
			KTApp.blockPage({
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
		},
		success: function(response){      
			if(response.data.length > 0){
				for( var i = 0; i<response.data.length; i++){
					if(i > 0){
						$('#addServiceBtn').click();
						totalElementsEdit(response.data.length - 1);
					}
					$('input[name="['+ i +'][edit_appointment_record_id]"').val(response.data[i]['recordId']);
					var time = response.data[i]['appointmentStartTime'];
					var serviceId = response.data[i]['service']['serviceId'];
					fetchServicesEdit(i,serviceId);
					var staffId = response.data[i]['staff']['staffId'];
					fetchStaffEdit(i,staffId);
					$('input[name="['+ i +'][edit_appointment_start_time]"').timepicker({
						defaultTime:time
					});
				}
			}else{
				fetchServicesEdit(0);
				fetchStaffEdit(0);
				fetchTimePickerEdit(0);
			}
		},
		complete: function(){
			KTApp.unblockPage();
			localStorage.removeItem("fetchedDetails");
		}
	});	
}

function removeService(recordId){
	$.ajax({
		url: HOST_URL + '/appointment/deleteAppointmentService/'+recordId,
		type: 'get',
		dataType: 'json',
		success: function(response){      
			if(undefined != response.redirect){
				if(response.redirect == true){
					window.location.assign(HOST_URL +'/appointment');
				}	
			}			
			else if('successful' != response.status){
				alert('Something went wrong in deleting product kindly contact support team for assistance');
			}
		},

	});
}

function showClientOverview(id){
	$.ajax({
		url: HOST_URL + '/client/clientDetails/'+id,
		type: 'get',
		dataType: 'json',
		beforeSend: function(){
			KTApp.blockPage({
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
		},			  
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				if(undefined != response.data[i]['clientLoyaltyPoints']){
					$('#edit_loyaltyPoints').text(response.data[i]['clientLoyaltyPoints']);
				}
				if(undefined != response.data[i]['clientVisits']){
					$('#edit_totalVisits').text(response.data[i]['clientVisits']);
				}
				if(undefined != response.data[i]['clientLastVisitedDate']){
					$('#edit_lastVisitedDate').text(response.data[i]['clientLastVisitedDate']);
				}
				if(undefined != response.data[i]['clientPlan']){
					$('#edit_clientPlan').text(response.data[i]['clientPlan']['planName']);
				}							
			}
		},
		complete: function(){
			$('#edit_clientDetails').show();
			KTApp.unblockPage();
		}
	});
}

//Convert a time in hh:mm format to minutes
function timeToMins(time) {
	var b = time.split(':');
	return b[0]*60 + +b[1];
}

//Convert minutes to a time in format hh:mm
//Returned value is in range 00  to 24 hrs
function timeFromMins(mins) {
	function z(n){return (n<10? '0':'') + n;}
	var h = (mins/60 |0) % 24;
	var m = mins % 60;
	return z(h) + ':' + z(m);
}

//Add two times in hh:mm format
function addDuration(t0,t1){
	return timeFromMins(timeToMins(t0) + timeToMins(t1));
}

//Add two times in hh:mm AM/PM format
function addTimes(t0, t1) {
	var time;
	if(t0.indexOf(' PM') > 0){
		time = moment(t0,'hh:mm p').add(t1.split(':')[0],'h').add(t1.split(':')[1],'m').format('hh:mm p');
		if(t0.split(':')[0] == 11 && time.split(':')[0] != 11){
			time = time.replace('p','a');
		}
	}else if(t0.indexOf(' AM') > 0){
		time = moment(t0,'hh:mm a').add(t1.split(':')[0],'h').add(t1.split(':')[1],'m').format('hh:mm a');
		if(t0.split(':')[0] == 11 && time.split(':')[0] != 11){
			time = time.replace('a','p');
		}
	}
	return time;
}

//Subtract two times in hh:mm format
function subTimes(t0, t1) {
	if(timeToMins(t0) > timeToMins(t1)){
		return timeFromMins(timeToMins(t0) - timeToMins(t1));	
	}else{
		return timeFromMins(timeToMins(t1) - timeToMins(t0));
	}	
}

//Subtract two times in hh:mm a/p format
function subtractTimes(t0,t1){
	var time;
	if(t0.indexOf(' PM') > 0){
		time = moment(t0,'hh:mm p').subtract(t1.split(':')[0],'h').subtract(t1.split(':')[1],'m').format('hh:mm p');
		if(t0.split(':')[0] == 11 && time.split(':')[0] != 11){
			time = time.replace('p','a');
		}
	}else if(t0.indexOf(' AM') > 0){
		time = moment(t0,'hh:mm a').subtract(t1.split(':')[0],'h').subtract(t1.split(':')[1],'m').format('hh:mm a');
		if(t0.split(':')[0] == 11 && time.split(':')[0] != 11){
			time = time.replace('a','p');
		}
	}
	return time;
}

//Subtract two times in hh:mm format
function subTimes(t0, t1) {
	if(timeToMins(t0) > timeToMins(t1)){
		return timeFromMins(timeToMins(t0) - timeToMins(t1));	
	}else{
		return timeFromMins(timeToMins(t1) - timeToMins(t0));
	}	
}

function getServiceDurationDifference(t0, t1){
	t0 = timeFromMins(timeToMins(t0));
	t1 = timeFromMins(timeToMins(t1));
	if(t1 > t0){
		addTime = true;
		return subTimes(t1,t0);
	}else{
		addTime = false;
		return subTimes(t0,t1);
	}
}

function fetchTimePickerEdit(id){
	if(undefined == id){
		id = 0;
	}
	if(id == 0){
		$('input[name="['+ id +'][edit_appointment_start_time]"').timepicker({});
	}else{
		var previousId = id - 1;
		var previousServiceTime = $('input[name="['+ previousId +'][edit_appointment_start_time]"').val();
		var previousServiceDuration = $('input[name="['+ previousId +'][edit_appointment_duration]"').val();
		var time = addTimes(previousServiceTime,previousServiceDuration);
		$('input[name="['+ id +'][edit_appointment_start_time]"').timepicker({
			defaultTime:time
		});	
	}
}

function changeService(param){
	var id = param.substring(1,2);
	var service_duration;
	var service_cost = 0;
	var values = $('select[name="'+ param +'"').val();
	if(values.length > 0){
		for(var key in serviceCategoryArray){
			if(serviceCategoryArray.hasOwnProperty(key)){
				var array = serviceCategoryArray[key];
				for(var i = 0; i<array.length; i++){
					var serviceId  = array[i]['serviceId'];
						if(serviceId == values){
							service_cost = service_cost + array[i]['serviceCost'];
							$('input[name="['+ id +'][edit_appointment_service_cost]"]').val(service_cost);
							if(undefined != service_duration){
								service_duration = addDuration(service_duration,array[i]['serviceDuration'])
							}else{
								service_duration = array[i]['serviceDuration'];
							}						
							$('input[name="['+ id +'][edit_appointment_duration]"').val(array[i]['serviceDuration']);
							$('input[name="['+ id +'][edit_appointment_duration_hidden]"').val(array[i]['serviceDuration']);							
						}
				}
			}
		}
		calculateTotalCostEdit();
		calculateTotalDurationEdit();
	}else{
		$('#edit_appointment_cost').val('');
		$('#edit_total_appointment_duration').val('');
	}
}

function calculateTotalCostEdit(){
	var totalAppointmentCost = 0;
	$("input[name*='edit_appointment_service_cost']").each(function(){
		console.log($(this).val());
		totalAppointmentCost = totalAppointmentCost + parseInt($(this).val());
	});
	$('#edit_appointment_cost').val(totalAppointmentCost);	
}

function calculateTotalDurationEdit(){
	var totalAppointmentDuration;
	$("input[name*='edit_appointment_duration_hidden']").each(function(){
		if(undefined != totalAppointmentDuration){
			totalAppointmentDuration = addDuration(totalAppointmentDuration,$(this).val());
		}else{
			totalAppointmentDuration = $(this).val();
		}
		console.log(totalAppointmentDuration);
	});
	$('#edit_total_appointment_duration').val(totalAppointmentDuration);
}

function updateRelevantElements(id,timeDifference){
	$("input[name$='[edit_appointment_start_time]']").each(function(){
		if($(this).attr("name").indexOf(id) == -1 && $(this).attr("name").indexOf(0) == -1){
			if(addTime){
				$(this).timepicker('setTime',addTimes($(this).val(),timeDifference));
			}else{
				$(this).timepicker('setTime',subtractTimes($(this).val(),timeDifference));
			}
		}
	});
}

function subTotalCostEdit(id){
	var finalTotal = 0;
	if(id > 0){
		decrementId = id - 1;
	}
	var currentTotal = $('#edit_appointment_cost').val();
	var values = $('select[name="['+ id +'][edit_appointment_service]"').val();
	if(values.length > 0){
		for(var key in serviceCategoryArray){
			if(serviceCategoryArray.hasOwnProperty(key)){
				var array = serviceCategoryArray[key];
				for(var i = 0; i<array.length; i++){
					var serviceId  = array[i]['serviceId'];
					for(var j = 0;j<values.length;j++){
						if(serviceId == values[j]){
							finalTotal = currentTotal - array[i]['serviceCost'];
							$('#edit_appointment_cost').val(finalTotal);	
						}
					}
				}
			}
		}
	}		
}

function subTotalDurationEdit(id){
	if(id > 0){
		decrementId = id - 1;
	}
	var totalDuration;
	var currentDuration = $('#edit_total_appointment_duration').val();
	var values = $('select[name="['+ id +'][edit_appointment_service]"').val();
	if(values.length > 0){
		for(var key in serviceCategoryArray){
			if(serviceCategoryArray.hasOwnProperty(key)){
				var array = serviceCategoryArray[key];
				for(var i = 0; i<array.length; i++){
					var serviceId  = array[i]['serviceId'];
					for(var j = 0;j<values.length;j++){
						if(serviceId == values[j]){
							totalDuration = subTimes(currentDuration,array[i]['serviceDuration']);
							$('#edit_total_appointment_duration').val(totalDuration);
						}
					}
				}
			}
		}
	}
}

function totalElementsEdit(id){
	$("input[name='edit_total_elements']").val(id);
}

function decTotalElementsEdit(){
	var currentElements = $("input[name='edit_total_elements']").val();
	$("input[name='edit_total_elements']").val(parseInt(currentElements) - 1);
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#appointment_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForms = function () {
	var _handleEditForm = function() {
		var validation;
		const appointmentForm = document.getElementById('editAppointmentForm');
		const clientField = jQuery(appointmentForm.querySelector('[name="client"]'));
		validation = FormValidation.formValidation(
				appointmentForm,
				{
					fields: {
						client : {
							validators : {
								callback : {
									message : 'Please select a client',
									callback : function(input) {
										const options = clientField.val();
										return (options != "");
									}
								}
							}
						},
						appointmentDate : {
							validators : {
								notEmpty : {
									message : 'Please enter appointment date'
								},
							}
						},
						"[0][edit_appointment_service]" : {
							validators : {
								callback : {
									message : 'Please select a service',
									callback : function(input) {										
										const options = $('select[name="[0][edit_appointment_service]"]').val();
										return (options != "Select");
									}
								}
							}
						},
						"[0][edit_appointment_staff]" : {
							validators : {
								callback : {
									message : 'Please select a staff',
									callback : function(input) {
										const options = $('select[name="[0][edit_appointment_staff]"]').val();
										return (options != "Select");
									}
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
		clientField.on('select2:select', function() {
			validation.revalidateField('client');
		});
		$('[name="appointmentDate"]').datepicker({
			format : 'mm/dd/yyyy'
		}).on('changeDate', function(e) {
			validation.revalidateField('appointmentDate');
		});
		$('#editAppointmentBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				var invalid = false;
				if (status == 'Valid') {
					invalid = checkIfAllEntriesAreValid();
					if (invalid) {
						swal.fire(
								{
									text : "Sorry, looks like there are some errors detected, please make sure all services and staff are selected and try again.",
									icon : "error",
									buttonsStyling : false,
									confirmButtonText : "Ok, got it!",
									customClass : {
										confirmButton : "btn font-weight-bold btn-light-primary"
									}
								})
								.then(
										function() {
											KTUtil.scrollTop();
										});
					} else {
						document.getElementById("editAppointmentForm").action = 'appointment/editAppointment/'+$('#edit_appointmentId').val();
						document.getElementById("editAppointmentForm").submit();
					}
				} else {
					KTUtil
					.scrollTop();
				}
			});
		});
	}
	return {
		init: function() {
			_handleEditForm();
		}
	};
}();

function checkIfAllEntriesAreValid(){
	var invalid = false;
	$("select[name*='edit_appointment_service']").each(function(){
		console.log($(this).val());
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	$("select[name*='edit_appointment_staff']").each(function(){
		if($(this).val() == 'Select'){
			invalid = true;
		}
	});
	return invalid;
}

jQuery(document).ready(function() {
	setLinkActive();
	$('#edit_appointment_client').on('select2:select', function (e) {		
		showClientOverview(e.params.data.id);
	});
	handleForms.init();
});