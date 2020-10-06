"use strict";

//Class definition
var addStaff = function () {
	// Base elements
	var _wizardEl;
	var _formEl;
	var _wizard;
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
			// Validate form
			if(wizard.currentStep == 2){
				populateReviewTable();
			}
		});

		// Change Event
		_wizard.on('change', function (wizard) {
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
		
		$('#submitNewStaffForm').on('click', function (e) {
			e.preventDefault();
			document.getElementById("staffForm").submit();
		});
	}

	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('new-staff-wizard');
			_formEl = KTUtil.getById('staffForm');
			initValidation();
			initWizard();
		}
	};
}();

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#staff_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

function populateReviewTable(){ 
	$('#selected_staff_designation').html($('#staffDesignation').val());
	$('#selected_full_name').html($('#fullName').val());
	$('#selected_staff_number').html($('#mobileNumber').val());
	$('#selected_staff_address').html($('#staffAddress').val());
	$('#selected_staff_pincode').html($('#staffPincode').val());
	$('#selected_staff_gender').html($("input[name='gender']:checked")[0].value);
	$('#selected_staff_email').html($('#staffEmail').val());
	$('#selected_staff_birthday').html($('#staff_birthday')[0].value);
	$('#selected_staff_start_date').html($('#staff_start_date').val());
	$('#selected_staff_end_date').html($('#staff_end_date').val());
	$('#selected_staff_inTime').html($('#staff_in_time').val());
	$('#selected_staff_outTime').html($('#staff_out_time').val());
	var workdays = new Array();
	$('input[name="workdays"]:checked').each(function() {
		workdays.push(this.value+' ');
	});
	$('#selected_staff_workdays').html(workdays);
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

jQuery(document).ready(function () {
	$('#loading-spinner').hide();
	setLinkActive();
	addStaff.init();
});
