"use strict";

function submitForm(){	
	document.appointmentForm.action = 'appointment/add/';
	document.getElementById("appointmentForm").submit();
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
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
	$('#appointment_nav').addClass('menu-item-active');
}

$(document).ready(function() {
	if($('#validation_error').length){
		alert('error');
		$('.span-info').hide();	
	}
	populateClient();
});