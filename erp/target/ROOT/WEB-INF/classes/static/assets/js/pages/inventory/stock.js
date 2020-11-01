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
				{data: 'product.productName'},								
				{data: 'stockQuantity'},
				{data: 'lastUpdatedDate',
					render: function(lastUpdatedDate){
						return lastUpdatedDate.substring(0,lastUpdatedDate.lastIndexOf(','));
					},	
					defaultContent: ''
				},	
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
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#inventory_nav').addClass('menu-item-open menu-item-here');
	$('#stock_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newOrderModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
