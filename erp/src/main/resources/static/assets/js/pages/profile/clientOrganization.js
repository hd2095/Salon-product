'use strict'

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#profile-creation_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForm = function () {
	var _handleSubmitForm = function() {
		var validation;
		validation = FormValidation.formValidation(
				KTUtil.getById('clientOrganizationForm'),
				{
					fields: {
						gstn_no: {
							validators: {
								regexp: {
									regexp : /^[0-9]*$/,
									message: 'Please enter 0 or positive numeric value'
								}
							}
						},
						gstn_percent: {
							validators: {
								notEmpty : {
									message : 'please enter numeric value (0 is allowed).'	
								},
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value (0 is allowed).'									
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
		$('#updateOrganizationDetails').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {
					document.getElementById("clientOrganizationForm").submit();
				}
			});
		});
	}
	return {
		init: function() {
			_handleSubmitForm();
		}
	};
}();

jQuery(document).ready(function() {
	setLinkActive();
	$('#loading-spinner').hide();
	handleForm.init();
});