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
		_wizard.on('beforeNext', function (wizard) {
			// Don't go to the next step yet
			//_wizard.stop();

			// Validate form
			if(wizard.currentStep == 2){
				populateReviewTable();
			}
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

function populateReviewTable(){ 
	$('#edit_selected_full_name').html($('#edit_staff_fullName').val());
	$('#edit_selected_staff_number').html($('#edit_staff_mobileNumber').val());
	$('#edit_selected_staff_email').html($('#edit_staff_emailId').val());
	$('#edit_selected_staff_address').html($('#edit_staff_address').val());
	$('#edit_selected_staff_pincode').html($('#edit_staff_pincode').val());
	$('#edit_selected_staff_gender').html($("input[name='gender']:checked")[0].value);	
	$('#edit_selected_staff_birthday').html($('#edit_staff_birthday')[0].value);
	$('#edit_selected_staff_designation').html($('#edit_staff_designation').val());
	$('#edit_selected_staff_start_date').html($('#edit_staff_start_date')[0].value);
	$('#edit_selected_staff_end_date').html($('#edit_staff_end_date')[0].value); 
	$('#edit_selected_staff_inTime').html($('#edit_staff_in_time').timepicker('getTime')[0].value);
	$('#edit_selected_staff_outTime').html($('#edit_staff_out_time').timepicker('getTime')[0].value);
	var workdays = new Array();
	$('input[name="workdays"]:checked').each(function() {
		workdays.push(this.value+' ');
	});
	$('#edit_selected_staff_workdays').html(workdays);
}


jQuery(document).ready(function () {
	setLinkActive();
	KTProjectsAdd.init();
});
