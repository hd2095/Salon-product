'use strict';
var allowSubmit = false;

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#profile-creation_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}


function submitForm(){
	var valid = true;
	$('.error').remove();
	$('#validation_error').remove();
	var first_name = $('#first_name').val();
	var last_name = $('#last_name').val();
	var mobileNumber = $('#mobileNumber').val();
	var emailId = $('#emailId').val();
	if (first_name.length < 1) {
		$('#first_name').after('<span id="first_name_error" class="error">please enter your first name</span>');
		$('#first_name_span').hide();
		valid = false;
	}else{
		$('#first_name_span').show();
		$('#first_name_error').hide();
	}
	if (last_name.length < 1) {
		$('#last_name').after('<span id="last_name_error" class="error">please enter your last name</span>');
		$('#last_name_span').hide();
		valid = false;
	}else{
		$('#last_name_span').show();
		$('#last_name_error').hide();
	}
	if(mobileNumber < 1){
		$('#mobileNumber_span').after('<span id="mobileNumber_error" class="error">please enter your mobile number</span>');
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
			$('#emailId_span').hide();
			$('#emailId_span').after('<span id="email_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(emailId).toLowerCase());
			$('#emailId_span').show();
			$('#email_error').hide();
		}
	}else{
		valid = false;
		$('#emailId_span').hide();
		$('#emailId_span').after('<span id="email_error" class="error">please enter your email id</span>');
	}
	if(valid){
		if($('.passwordField:visible').length != 0){
			if(allowSubmit){
				//showLoadingDiv();
				KTApp.blockPage({
					overlayColor: 'red',
					opacity: 0.1,
					state: 'primary' // a bootstrap color
				});
				setTimeout(function() {
					$('#profileForm').submit();
				},2000);                   
				//document.getElementById("profileForm").submit();
			}
		}else{
			//showLoadingDiv();
			KTApp.blockPage({
				message: 'updating profile..',
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
			setTimeout(function() {
				$('#profileForm').submit();
			},2000);     
			//document.getElementById("profileForm").submit();
		}
	}
}

function showPasswordField(){
	if($('.passwordField:visible').length == 0)
	{
		$('.passwordField').show();
		$('#confirmPassword_span').text('Please Confirm password');
		$("#confirmPassword_span").css("color","");
		$('#password').val('');
		$('#confirmPassword').val('');
	}else{
		$('.passwordField').hide();
	}
}

jQuery(document).ready(function() {
	setLinkActive();
	$(function(){
		$('#confirmPassword').keyup(function(e){
			if($('#password').val() == $(this).val()){
				//if both are same remove the error and allow to submit
				$('#confirmPassword_span').text('Please Confirm password');
				$("#confirmPassword_span").css("color","");
				allowSubmit = true;
			}else{
				//if not matching show error and not allow to submit
				$("#confirmPassword_span").css("color","red");
				$('#confirmPassword_span').text('Entered password\'s don\'t match');				
				allowSubmit = false;
			}
		});
	});
});