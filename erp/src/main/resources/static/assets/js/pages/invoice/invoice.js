'use strict'

function submitForm(){
	document.invoiceDetailsForm.action = 'sell/generateSaleInvoice/'+$('#saleId').val();
	document.getElementById("invoiceDetailsForm").submit();
}

function submitAppointmentForm(){
	document.invoiceDetailsForm.action = 'appointment/generateAppointmentInvoice/'+$('#appointmentId').val();
	document.getElementById("invoiceDetailsForm").submit();
}

function submitFinalInvoice(){
	window.open( 
			"sell/saveSaleInvoice/saleId/"+$('#saleId').val()+"/invoiceId/"+$('#invoiceId').val(), "_blank")
	/*$('#htmlObj').val($('#htmlToPdf').html());
	document.saleInvoiceForm.action = 'sell/saveSaleInvoice/saleId/'+$('#saleId').val()+"/invoiceId/"+$('#invoiceId').val();
	document.getElementById("saleInvoiceForm").submit();*/
}

/*window.open( 
		"sell/saveSaleInvoice/"+$('#saleId').val(), "_blank"); */