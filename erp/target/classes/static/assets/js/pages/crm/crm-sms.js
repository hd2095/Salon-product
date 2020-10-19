'use strict'

function fetchClients(){
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			for( var i = 0; i<response.data.length; i++){
				var clientId = response.data[i]['clientId'];
				var clientName = response.data[i]['fullName'];
				$("#crm_sms_clients").append("<option value='"+clientId+"'>"+clientName+"</option>");				
			}
			$('#crm_sms_clients').selectpicker('refresh');
		}
	});	
}

function populateMsgArea(id){
	var text = '';
	if(id == 'template-one'){		
		text = $('#template-one-para').text();
		$('#message-to-send').val(text.trim());
	}else if(id == 'template-two'){
		text = $('#template-two-para').text()
		$('#message-to-send').val(text.trim());
	}else{
		text = $('#template-three-para').text()
		$('#message-to-send').val(text.trim());
	}
}

function filterClient(param){
	$.ajax({
		url: HOST_URL + '/client/filterClients/'+param,
		type: 'get',
		dataType: 'json',
		beforeSend: function() {
			KTApp.blockPage({
				message: 'Fetching clients..',
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
		},
		success: function(response){  
			$("#crm_sms_clients").find('option').remove().end();
			for( var i = 0; i<response.data.length; i++){
				var clientId = response.data[i]['clientId'];
				var clientName = response.data[i]['fullName'];
				$("#crm_sms_clients").append("<option value='"+clientId+"'>"+clientName+"</option>");				
			}
			$("#crm_sms_clients").selectpicker('refresh');
		},
		complete: function() {
			KTApp.unblockPage();
		}
	});
}

function sendMessage(){
	var valid = true;
	var messageContent = $('#message-to-send').val();
	var selectedClient = $('select[name=crm_sms_clients]').val();
	if(selectedClient == ''){
		valid = false;
		alert('Please select atleast one client to send message');
	}else{
		if(messageContent.length > 150){
			alert('Only 150 characters allowed per message kindly trim your message!!');
			valid = false;
		}else if(messageContent.length == 0){
			alert('Cannot send empty message to client(s) !!');
			valid = false;
		}
		if(valid){
			document.clientMessageForm.action = 'crm/sms';
			document.getElementById("clientMessageForm").submit();
			KTApp.blockPage({
				message: 'Sending message(s)..',
				overlayColor: 'red',
				opacity: 0.1,
				state: 'primary' // a bootstrap color
			});
		}	
	}
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#sms_nav').addClass('menu-item-active');
	$('#crm_nav').addClass('menu-item-open menu-item-here');
}

jQuery(document).ready(function() {
	
	setLinkActive();
	fetchClients();
});