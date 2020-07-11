"use strict";

// Class definition
var KTProjectsAdd = function () {
	// Base elements
	var _wizardEl;
	var _wizard;
	var _formEl;

	// Private functions
	var initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number
			clickableSteps: true  // allow step clicking
		});

		// Validation before going to next page
		_wizard.on('beforeNext', function () {
			// Don't go to the next step yet
			//_wizard.stop();

			// Validate form
		});

		// Change Event
		_wizard.on('change', function () {
			KTUtil.scrollTop();
		});
	}


	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('edit-staff-wizard');
			_formEl = KTUtil.getById('editstaffForm');

			initWizard();
		}
	};
}();


function submitForm(){
	document.editStaffForm.action = 'staff/editStaff/'+$('#edit_staffId').val();
	document.getElementById("editStaffForm").submit();
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}

jQuery(document).ready(function () {
	setLinkActive();
	KTProjectsAdd.init();
});
