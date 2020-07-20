"use strict";

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var fullName = $('#fullName').val();
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
	if(pincode.length == 6){
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
	if(valid){
		document.getElementById("staffForm").submit();
	}
}

jQuery(document).ready(function () {
	setLinkActive();
});
