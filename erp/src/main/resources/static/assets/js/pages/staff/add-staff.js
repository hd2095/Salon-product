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
			_wizardEl = KTUtil.getById('new-staff-wizard');
			_formEl = KTUtil.getById('staffForm');

			initWizard();
		}
	};
}();

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}

function submitForm(){	
	document.getElementById("staffForm").submit();
}

function populateReviewTable(){ 
	//$('#selected_client').html($('#appointment_client option:selected').text());	
	//$('#selected_appointment_staff').html($('#appointment_staff option:selected').text());
	$('#selected_full_name').html($('#fullName').val());
	$('#selected_staff_number').html($('#mobileNumber').val());
	$('#selected_staff_address').html($('#staffAddress').val());
	$('#selected_staff_pincode').html($('#staffPincode').val());
	$('#selected_staff_gender').html($("input[name='gender']:checked")[0].value);
	$('#selected_staff_email').html($('#staffEmail').val());
	//$('#selected_staff_gender').html($('#appointment_service option:selected').text());
	$('#selected_staff_birthday').html($('#staff_birthday')[0].value);
	//$('#selected_appointment_time').html($('#kt_timepicker_1').timepicker('getTime')[0].value);
}


jQuery(document).ready(function () {
	setLinkActive();
	KTProjectsAdd.init();
});
