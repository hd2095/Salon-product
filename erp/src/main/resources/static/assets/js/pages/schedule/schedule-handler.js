"use strict";

function submitForm(){	
	//$('.error').remove();
	//$('#validation_error').remove();
	var valid = true;
	/*	var appointment_date = $('#appointmentDate').val();
	if (appointment_date.length < 1) {
		$('#appointmentDate_span').after('<span id="appointmentDate_error" class="error">please enter appointment date</span>');
		$('#appointmentDate_span').hide();
		valid = false;
	}else{
		$('#appointmentDate_span').show();
		$('#appointmentDate_error').hide();
	}*/
	if(valid){
		document.getElementById("scheduleForm").action = 'schedule/add';
		document.getElementById("scheduleForm").submit();
	}
}