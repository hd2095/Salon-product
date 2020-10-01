"use strict";

//Class definition
var addStaff = function () {
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
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#staff_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
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

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	$('#staffAlreadyExists').hide();
	var valid = true;
	var fullName = $('#fullName').val();
	var staffAddress = $('#staffAddress').val();	
	var pincode = $('#staffPincode').val();
	var mobileNumber = $('#mobileNumber').val();
	var emailId = $('#staffEmail').val();
	var startDate = $('#staff_start_date').val();
	if (startDate.length < 1) {
		$('#staff_start_date_span').after('<span id="staff_start_date_error" class="error">please enter staff start date</span>');
		$('#staff_start_date_span').hide();
		valid = false;
	}else{
		$('#staff_start_date_span').show();
		$('#staff_start_date_error').hide();
	}
	if (fullName.length < 1) {
		$('#fullName_span').after('<span id="fullName_error" class="error">please enter staff full name</span>');
		$('#fullName_span').hide();
		valid = false;
	}else{
		$('#fullName_span').show();
		$('#fullName_error').hide();
	}
	if(staffAddress < 1){
		valid = false;
		$('#staffAddress_span').after('<span id="staffAddress_error" class="error">please enter staff address</span>');
		$('#staffAddress_span').hide();
	}else{
		$('#staffAddress_span').show();
		$('#staffAddress_error').hide();
	}
	if(pincode < 1){
		valid = false;
		$('#staffPincode_span').after('<span id="staffPincode_error" class="error">please enter staff pin code</span>');
		$('#staffPincode_span').hide();
	}else if(pincode.length == 6){
		if(isNaN(pincode)){
			valid = false;
			$('#staffPincode_span').after('<span id="staffPincode_error" class="error">Invalid staff pin code enter 6 digits</span>');
			$('#staffPincode_span').hide();
		}else{
			$('#staffPincode_span').show();	
		}
	}else if(pincode.length > 0){
		valid = false;
		$('#staffPincode_span').after('<span id="staffPincode_error" class="error">Invalid staff pin code enter 6 digits</span>');
		$('#staffPincode_span').hide();
	}
	if(mobileNumber < 1){
		$('#mobileNumber_span').after('<span id="mobileNumber_error" class="error">please enter staff mobile number</span>');
		$('#mobileNumber_span').hide();
		valid = false;
	}else if(mobileNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var number = regEx.test(mobileNumber);
		if (!number) {
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
	if(emailId.length > 1){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(emailId);
		if (!validEmail) {
			valid = false;
			$('#staffEmail_span').hide();
			$('#staffEmail_span').after('<span id="staffEmail_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(emailId).toLowerCase());
			$('#staffEmail_span').show();
			$('#staffEmail_error').hide();
		}
	}
	if(valid){
		document.getElementById("staffForm").submit();
	}
}

jQuery(document).ready(function () {
	$('#loading-spinner').hide();
	setLinkActive();
	addStaff.init();
});
