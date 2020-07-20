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
				{data: 'order.orderTotal',
					render: function(order){					
						return '<p> &#8377; ' + order + '</p>';
					}	
				},
				{data: 'order.orderDate',
					render: function(order){					
						return order.split("12:")[0];
					}	
				},
				{data: 'order.orderReceivedDate',
					render: function(order){					
						return order.split("12:")[0];
					}	
				},
				{data: 'stockQuantity'}								
				]
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
