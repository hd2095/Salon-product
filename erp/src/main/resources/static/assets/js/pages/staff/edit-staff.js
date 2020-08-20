"use strict";
//Class definition
var editStaff = function () {
	// Base elements
	var _wizardEl;
	var _wizard;
	var _formEl;

	// Private functions
	var initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number
			clickableSteps: true  // allow step clicking
		});

		// Validation before going to next page
		_wizard.on('beforeNext', function () {
			// Don't go to the next step yet
			//_wizard.stop();

			// Validate form
		});

		// Change Event
		_wizard.on('change', function () {
			KTUtil.scrollTop();
		});
	}


	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('edit-staff-wizard');
			_formEl = KTUtil.getById('editstaffForm');

			initWizard();
		}
	};
}();

function submitForm(){
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var fullName = $('#edit_staff_fullName').val();
	var staffAddress = $('#edit_staff_address').val();
	var pincode = $('#edit_staff_pincode').val();
	var mobileNumber = $('#edit_staff_mobileNumber').val();
	var emailId = $('#edit_staff_emailId').val();
	var startDate = $('#edit_staff_start_date').val();
	if(staffAddress < 1){
		valid = false;
		$('#edit_staff_address_span').after('<span id="edit_staff_address_error" class="error">please enter staff address</span>');
		$('#edit_staff_address_span').hide();
	}else{
		$('#edit_staff_address_span').show();
		$('#edit_staff_address_error').hide();
	}
	if (startDate.length < 1) {
		$('#edit_staff_start_date_span').after('<span id="edit_staff_start_date_error" class="error">please enter staff start date</span>');
		$('#edit_staff_start_date_span').hide();
		valid = false;
	}else{
		$('#edit_staff_start_date_span').show();
		$('#edit_staff_start_date_error').hide();
	}
	if (fullName.length < 1) {
		$('#edit_staff_fullName_span').after('<span id="edit_staff_fullName_error" class="error">please enter staff full name</span>');
		$('#edit_staff_fullName_span').hide();
		valid = false;
	}else{
		$('#edit_staff_fullName_span').show();
		$('#edit_staff_fullName_error').hide();
	}
	if(mobileNumber < 1){
		$('#edit_staff_mobileNumber_span').after('<span id="edit_staff_mobileNumber_error" class="error">please enter staff mobile number</span>');
		$('#edit_staff_mobileNumber_span').hide();
		valid = false;
	}else if(mobileNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var number = regEx.test(mobileNumber);
		if (!number) {
			valid = false;
			$('#edit_staff_mobileNumber_span').after('<span id="edit_staff_mobileNumber_error" class="error">Enter a valid mobile number</span>');
			$('#edit_staff_mobileNumber_span').hide();
		}else{
			$('#edit_staff_mobileNumber_error').hide();
			$('#edit_staff_mobileNumber_span').show();
		}
	}else{
		valid = false;
		$('#edit_staff_mobileNumber_span').after('<span id="edit_staff_mobileNumber_error" class="error">Enter a valid mobile number (10 digits)</span>');
		$('#edit_staff_mobileNumber_span').hide();
	}
	if(emailId.length > 1){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(emailId);
		if (!validEmail) {
			valid = false;
			$('#edit_staff_emailId_span').hide();
			$('#edit_staff_emailId_span').after('<span id="edit_staff_emailId_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(emailId).toLowerCase());
			$('#edit_staff_emailId_span').show();
			$('#edit_staff_emailId_error').hide();
		}
	}
	if(pincode < 1){
		valid = false;
		$('#edit_staff_pincode_span').after('<span id="edit_staff_pincode_error" class="error">please enter staff pin code</span>');
		$('#edit_staff_pincode_span').hide();
	}else if(pincode.length == 6){
		if(isNaN(pincode)){
			valid = false;
			$('#edit_staff_pincode_span').after('<span id="edit_staff_pincode_error" class="error">Invalid staff pin code enter 6 digits</span>');
			$('#edit_staff_pincode_span').hide();
		}else{
			$('#edit_staff_pincode_span').show();	
		}
	}else if(pincode.length > 0){
		valid = false;
		$('#edit_staff_pincode_span').after('<span id="edit_staff_pincode_error" class="error">Invalid staff pin code enter 6 digits</span>');
		$('#edit_staff_pincode_span').hide();
	}
	if(valid){
		document.editStaffForm.action = 'staff/editStaff/'+$('#edit_staffId').val();
		document.getElementById("editStaffForm").submit();
	}
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#staff_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

function populateReviewTable(){ 
	$('#edit_selected_full_name').html($('#edit_staff_fullName').val());
	$('#edit_selected_staff_number').html($('#edit_staff_mobileNumber').val());
	$('#edit_selected_staff_email').html($('#edit_staff_emailId').val());
	$('#edit_selected_staff_address').html($('#edit_staff_address').val());
	$('#edit_selected_staff_pincode').html($('#edit_staff_pincode').val());
	$('#edit_selected_staff_gender').html($("input[name='gender']:checked")[0].value);	
	$('#edit_selected_staff_birthday').html($('#edit_staff_birthday')[0].value);
	$('#edit_selected_staff_designation').html($('#edit_staff_designation').val());
	$('#edit_selected_staff_start_date').html($('#edit_staff_start_date')[0].value);
	$('#edit_selected_staff_end_date').html($('#edit_staff_end_date')[0].value); 
	$('#edit_selected_staff_inTime').html($('#edit_staff_in_time').timepicker('getTime')[0].value);
	$('#edit_selected_staff_outTime').html($('#edit_staff_out_time').timepicker('getTime')[0].value);
	var workdays = new Array();
	$('input[name="workdays"]:checked').each(function() {
		workdays.push(this.value+' ');
	});
	$('#edit_selected_staff_workdays').html(workdays);
}


jQuery(document).ready(function () {
	setLinkActive();
	editStaff.init();
});
