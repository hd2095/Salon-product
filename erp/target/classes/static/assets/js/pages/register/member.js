"use strict"

function fetchOrganization(){
	$.ajax({
		url: HOST_URL + 'register/getAllMasters',
		type: 'get',
		dataType: 'json',
		success: function(response){     
            for( var i = 0; i<response.length; i++){
            	var organization_id = response[i]['masterId'];
            	var organization_name = response[i]['organizationName'];                
                $("#organization").append("<option value='"+organization_id+"'>"+organization_name+"</option>");
               }
		}
	});
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#register_nav').addClass('menu-item-open menu-item-here');
	$('#register_member_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	fetchOrganization();
	setLinkActive();
});