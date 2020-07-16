"use strict";
function submitForm(){	
	document.editAppointmentForm.action = 'appointment/editAppointment/'+$('#edit_appointmentId').val();
	document.getElementById("editAppointmentForm").submit();
}
function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
	$('#appointment_nav').addClass('menu-item-active');
}

jQuery(document).ready(function () {
	setLinkActive();
});
