"use strict";
//Class definition
var editStaff = function () {
	// Base elements
	var _wizardEl;
	var _wizard;
	var _formEl;
	var _validations = [];
	var fv;
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
			_wizard.stop();
			var validator = _validations[wizard.getStep() - 1];
			validator.validate().then(function (status) {
				if (status == 'Valid') {
					_wizard.goNext();
					KTUtil.scrollTop();
				} else {
					Swal.fire({
						text: "Sorry, looks like there are some errors detected, please try again.",
						icon: "error",
						buttonsStyling: false,
						confirmButtonText: "Ok, got it!",
						customClass: {
							confirmButton: "btn font-weight-bold btn-light"
						}
					}).then(function () {
						KTUtil.scrollTop();
					});
				}
			});
			
			if(wizard.currentStep == 2){
				populateReviewTable();
			}
		});

		// Change Event
		_wizard.on('change', function () {
			KTUtil.scrollTop();
		});
			
	}

	// Form Validation
	var initValidation = function () {
		// Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
		// Step 1
		_validations.push(FormValidation.formValidation(
				_formEl,
				{
					fields: {
						fullName: {
							validators: {
								notEmpty: {
									message: 'please enter staff name'
								}
							}
						},
						mobileNumber: {
							validators : {
								notEmpty : {
									message : 'please enter staff\'s mobile number'
								},
								regexp : {
									regexp : /^[0-9]{1,10}$/,
									message : 'please enter a valid mobile number (10 digits).'									
								}
							}
						},
						staffPincode: {
							validators: {
								regexp : {
									regexp : /^[0-9]{6,6}$/,
									message : 'please enter a valid pin code (6 digits).'									
								}
							}
						},
						emailId: {
							validators: {
								emailAddress: {
									message: 'The value is not a valid email address'
								}
							}
						},
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		));
		
		_validations.push(fv = FormValidation.formValidation(
				_formEl,
				{
					fields: {
						// Step 2
						staff_start_date : {
							validators : {
								notEmpty : {
									message : 'Please enter staff start date'
								},
							}
						},
						staffDesignation: {
							validators: {
								notEmpty: {
									message: 'Please enter staff designation'
								}
							}
						},
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		));

		$('[name="staff_start_date"]').datepicker({
			format : 'mm/dd/yyyy'
		}).on('changeDate', function(e) {
			fv.revalidateField('staff_start_date');
		});

		$('#editStaffBtn').on('click', function (e) {
			e.preventDefault();
			document.getElementById("editstaffForm").action = "staff/editStaff/"+$('#edit_staffId').val();
			document.getElementById("editstaffForm").submit();
		});
	}

	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('edit-staff-wizard');
			_formEl = KTUtil.getById('editstaffForm');
			initWizard();
			initValidation();
		}
	};
}();

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
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
	$('#loading-spinner').hide();
	setLinkActive();
	editStaff.init();
});
