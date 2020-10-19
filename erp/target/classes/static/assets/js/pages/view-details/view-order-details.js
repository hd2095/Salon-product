'use strict'

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#newOrder_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	setLinkActive();
});