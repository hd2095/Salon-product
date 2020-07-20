"use strict";

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var appointment_date = $('#appointmentDate').val();
	if (appointment_date.length < 1) {
		$('#appointmentDate_span').after('<span id="appointmentDate_error" class="error">please enter appointment date</span>');
		$('#appointmentDate_span').hide();
		valid = false;
	}else{
		$('#appointmentDate_span').show();
		$('#appointmentDate_error').hide();
	}
	if(valid){
		document.appointmentForm.action = 'appointment/add/';
		document.getElementById("appointmentForm").submit();
	}
}

function populateClient(){	
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
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

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#appointment_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

$(document).ready(function() {
	if($('#validation_error').length){
		$('#appointmentDate_span').hide();	
	}
	setLinkActive();
	populateClient();
});