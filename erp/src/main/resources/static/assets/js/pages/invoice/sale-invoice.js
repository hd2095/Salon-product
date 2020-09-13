'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#sale_invoice_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/sell/getAllSaleInvoices',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'invoiceNo'},
				{data: 'invoiceDate'},
				{data: 'client.fullName'},								
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {							
							return '\
							<a href="sell/viewInvoiceDetails/' +full.invoiceId+'" class="btn btn-sm btn-clean btn-icon" title="Edit details">\
							<i class="la la-cog"></i>\
							</a>\
							<a href="javascript:deleteInvoice(\'' +full.invoiceId+'\',\''+full.invoiceNo+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete">\
							<i class="la la-trash"></i>\
							</a>\
							';
						},
					},
					],
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

function deleteInvoice(id,invoiceNo){
	Swal.fire({
		title: "Are you sure you want to delete " + invoiceNo+ "!",
		icon: "warning",		  
		confirmButtonText: "Yes, delete it!",
		showCancelButton: true,
		cancelButtonText: "No, Cancel!",
		customClass: {
			confirmButton: "btn btn-danger",
			cancelButton: "btn btn-default"
		},
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return fetch(`${HOST_URL}/sell/deleteInvoice/${id}`)
			.then(response => {
				if(!response.ok){
					throw new Error(response.statusText);	
				}
				return response.json();
			})
			.catch(error => {
				Swal.showValidationMessage(
						`Request failed: ${error}`
				)
			})  
		}
	}).then(function(result){
		if(result.value){
			Swal.fire({
				title: invoiceNo + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('sell/showSaleInvoices');
				}
			});
		}
	});	
}

jQuery(document).ready(function() {
	//setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
