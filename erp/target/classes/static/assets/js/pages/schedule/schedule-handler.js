"use strict";

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#schedule_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForms = function () {
	var _handleCreateForm = function() {
		var validation;
		const scheduleForm = document.getElementById('scheduleForm');
		validation = FormValidation.formValidation(
				scheduleForm,
				{
					fields: {
						scheduleWith : {
							validators : {
								notEmpty : {
									message : 'Please enter meeting with'
								}
							}
						},
						scheduleTopic : {
							validators : {
								notEmpty : {
									message : 'Please enter meeting topic'
								}
							}
						},
						scheduleDate : {
							validators : {
								notEmpty : {
									message : 'Please enter schedule date'
								},
							}
						},
						scheduleTo: {
							validators: {
								notEmpty: {
									message: 'Please enter service duration'
								}
							}
						},
						scheduleFrom: {
							validators: {
								notEmpty: {
									message: 'Please enter service duration'
								}
							}
						}
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						submitButton: new FormValidation.plugins.SubmitButton(),	                   
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		);
		$('[name="scheduleDate"]').datepicker({
			format : 'mm/dd/yyyy'
		}).on('changeDate', function(e) {
			validation.revalidateField('scheduleDate');
		});
		$('[name="scheduleFrom"]').timepicker().on('changeTime.timepicker', function(e) {
			validation.revalidateField('scheduleFrom');
		});
		$('[name="scheduleTo"]').timepicker().on('changeTime.timepicker', function(e) {
			validation.revalidateField('scheduleTo');
		});
		$('#createScheduleBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {										
					document.getElementById("scheduleForm").action = "schedule/add";
					document.getElementById("scheduleForm").submit();
				} else {
					KTUtil.scrollTop();
				}
			});
		});
	}
	
	return {
		init: function() {
			_handleCreateForm();
		}
	};
}();

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	setLinkActive();
	handleForms.init();
});