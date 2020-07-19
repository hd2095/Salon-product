'use strict';

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#profile-creation_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

function fetchUserProfile(){
	$.ajax({
		url: HOST_URL + '/getUserDetails',
		success:function(response){	

		}
	});
}

function submitForm(){
	document.getElementById("profileForm").submit();
}

function showPasswordField(){
	if($('.passwordField:visible').length == 0)
	{
		$('.passwordField').show();
	}else{
		$('.passwordField').hide();
	}
}

jQuery(document).ready(function() {
	setLinkActive();
	fetchUserProfile();
});