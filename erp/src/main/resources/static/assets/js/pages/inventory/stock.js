'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#stock_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/inventory/stock/getAllStock',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'stockId'},
				{data: 'supplier.supplierName'},
				{data: 'product.productName'},
				{data: 'order.orderTotal'},
				{data: 'order.orderDate'},
				{data: 'stockQuantity'}								
				/*{data: 'actions', responsivePriority: -1},*/
			]
/*			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {	
						console.log(full);
						return '\
							<a href="javascript:editOrder(\'' +full.orderId+'\');" class="btn btn-xs btn-custom" title="Edit Stock">\
							<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteOrder(\'' +full.orderId+'\',\''+full.product.productName+'\');" class="btn btn-xs btn-custom" title="Delete Stock">\
							<i class="lnr lnr-trash"></i>\
							</a>\
						';
					},
				},
			],*/
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#subPages').addClass('in');
	$('#stock_nav').addClass('active');
	$('#inventory_nav').addClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newOrderModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
