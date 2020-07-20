"use strict";
function submitForm(){
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var appointment_date = $('#edit_appointment_date').val();
	if (appointment_date.length < 1) {
		$('#edit_appointmentDate_span').after('<span id="edit_appointmentDate_error" class="error">please enter appointment date</span>');
		$('#edit_appointmentDate_span').hide();
		valid = false;
	}else{
		$('#edit_appointmentDate_span').show();
		$('#edit_appointmentDate_error').hide();
	}
	if(valid){
		document.editAppointmentForm.action = 'appointment/editAppointment/'+$('#edit_appointmentId').val();
		document.getElementById("editAppointmentForm").submit();
	}
}
function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#appointment_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function () {
	setLinkActive();
	if($('#validation_error').length){
		$('#edit_appointmentDate_span').hide();	
	}
});
