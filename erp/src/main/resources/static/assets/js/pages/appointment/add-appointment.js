"use strict";

// Class definition
var KTProjectsAdd = function () {
	// Base elements
	var _wizardEl;
	var _formEl;
	var _wizard;
	var _avatar;
	var _validations = [];

	// Private functions
	var initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number
			clickableSteps: true  // allow step clicking
		});

		// Validation before going to next page
		_wizard.on('beforeNext', function (wizard) {
			// Don't go to the next step yet
			//_wizard.stop();
			// Validate form
			if(wizard.currentStep == 1){
				populateClient();
			}else if(wizard.currentStep == 2){
				populateReviewTable();
			}
		});

		// Change Event
		_wizard.on('change', function (wizard) {
			KTUtil.scrollTop();
		});
	}


	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('new-appointment-wizard');
			_formEl = KTUtil.getById('appointmentForm');

			initWizard();
		}
	};
}();


function submitForm(){	
	document.getElementById("appointmentForm").submit();
}

function populateReviewTable(){ 
	$('#selected_client').html($('#appointment_client option:selected').text());	
	$('#selected_appointment_staff').html($('#appointment_staff option:selected').text());
	$('#selected_appointment_duration').html($('#appointment_duration').val());
	$('#selected_appointment_cost').html($('#appointment_cost').val());
	$('#selected_appointment_notes').html($('#appointment_notes').val());
	$('#selected_appointment_service').html($('#appointment_service option:selected').text());
	$('#selected_appointment_date').html($('#kt_datepicker_4')[0].value);
	$('#selected_appointment_time').html($('#kt_timepicker_1').timepicker('getTime')[0].value);
}

function populateClient(){	
	$.ajax({
		url: 'client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
            for( var i = 0; i<response.data.length; i++){
            	var clientId = response.data[i]['clientId'];
            	var clientName = response.data[i]['fullName'];
            	 $("#appointment_client").append("<option value='"+clientId+"'>"+clientName+"</option>");
               }
		}
	});
	
}

jQuery(document).ready(function () {
	KTProjectsAdd.init();
});
