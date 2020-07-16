"use strict";

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}

function submitForm(){	
	document.getElementById("staffForm").submit();
}

jQuery(document).ready(function () {
	setLinkActive();
});
