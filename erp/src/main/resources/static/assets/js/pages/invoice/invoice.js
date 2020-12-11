'use strict'

function submitFinalInvoice(){
	window.open("sell/saveSaleInvoice/saleId/"+$('#saleId').val()+"/invoiceId/"+$('#invoiceId').val(), "_blank");
}

function submitAppointmentFinalInvoice(){
	window.open("appointment/saveAppointmentInvoice/appointmentId/"+$('#appointmentId').val()+"/invoiceId/"+$('#invoiceId').val());
}

var handleForm = function () {	
	var _handleSubmitForm = function() {
		var validation;
		const invoiceDetailsForm = document.getElementById('invoiceDetailsForm');
		validation = FormValidation.formValidation(
				invoiceDetailsForm,
				{
					fields: {
						cgst : {
							validators: {
								notEmpty : {
									message : 'please enter numeric value (0 is allowed).'	
								},
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value (0 is allowed).'									
								}
							}
						},
						sgst : {
							validators: {
								notEmpty : {
									message : 'please enter numeric value (0 is allowed).'	
								},
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value (0 is allowed).'								
								}
							}
						},
						discount:{
							validators: {
								notEmpty : {
									message : 'please enter numeric value (0 is allowed).'	
								},
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value (0 is allowed).'								
								}
							}
						},
						challanNo: {
							validators: {
								regexp : {
									regexp : /^[0-9]*$/,
									message : 'please enter numeric value.'								
								}
							}
						},
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						submitButton: new FormValidation.plugins.SubmitButton(),	                   
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		);
		$('#submitSaleInvoice').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {		
					document.invoiceDetailsForm.action = 'sell/generateSaleInvoice/'+$('#saleId').val();
					document.getElementById("invoiceDetailsForm").submit();
				}
			});
		});
		$('#submitAppointmentInvoice').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {		
					document.invoiceDetailsForm.action = 'appointment/generateAppointmentInvoice/'+$('#appointmentId').val();
					document.getElementById("invoiceDetailsForm").submit();
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
	handleForm.init();
});