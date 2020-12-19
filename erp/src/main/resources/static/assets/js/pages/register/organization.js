"use strict"

function fetchPlan(){
	$.ajax({
		url: HOST_URL + '/getAllPlans',
		type: 'get',
		dataType: 'json',
		success: function(response){     
            for( var i = 0; i<response.length; i++){
            	var plan_name = response[i]['plan_name'];                
                $("#plan").append("<option value='"+plan_name+"'>"+plan_name+"</option>");
               }
		}
	});
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#register_nav').addClass('menu-item-open menu-item-here');
	$('#register_organization_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	fetchPlan();
	setLinkActive();
});