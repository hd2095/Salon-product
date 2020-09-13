'use strict'

function submitForm(){
	document.invoiceDetailsForm.action = 'sell/generateSaleInvoice/'+$('#saleId').val();
	document.getElementById("invoiceDetailsForm").submit();
}

function submitFinalInvoice(){
	window.open( 
			"sell/saveSaleInvoice/saleId/"+$('#saleId').val()+"/invoiceId/"+$('#invoiceId').val(), "_blank")
	/*document.saleInvoiceForm.action = 'sell/saveSaleInvoice/'+$('#saleId').val();
	document.getElementById("saleInvoiceForm").submit();*/
}

/*window.open( 
		"sell/saveSaleInvoice/"+$('#saleId').val(), "_blank"); */