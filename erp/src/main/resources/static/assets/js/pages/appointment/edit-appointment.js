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
		if(wizard.currentStep == 2){
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
			_formEl = KTUtil.getById('editAppointmentForm');

			initWizard();
		}
	};
}();


function submitForm(){	
	document.editAppointmentForm.action = 'appointment/editAppointment/'+$('#edit_appointmentId').val();
	document.getElementById("editAppointmentForm").submit();
}

function populateReviewTable(){ 
	$('#edit_selected_client').html($('#edit_appointment_client option:selected').text());	
	$('#edit_selected_appointment_staff').html($('#edit_appointment_staff option:selected').text());
	$('#edit_selected_appointment_duration').html($('#edit_appointment_duration').val());
	$('#edit_selected_appointment_cost').html($('#edit_appointment_cost').val());
	$('#edit_selected_appointment_notes').html($('#edit_appointment_notes').val());
	$('#edit_selected_appointment_service').html($('#edit_appointment_service option:selected').text());
	$('#edit_selected_appointment_date').html($('#edit_appointment_date')[0].value);
	$('#edit_selected_appointment_time').html($('#edit_appointment_start_time').timepicker('getTime')[0].value);
}

jQuery(document).ready(function () {
	KTProjectsAdd.init();
});