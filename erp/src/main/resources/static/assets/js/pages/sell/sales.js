'use strict';

var stockResponseData;

var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#sales_dataTable');

		// begin first table
		table.DataTable({
			aaSorting : [[1, 'desc']],
			responsive: true,
			ajax: {
				url: HOST_URL + '/sell/getAllSales',
				type: 'GET',
				data: {					
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'client.fullName'},				
				{data: 'saleDate',
					render : function(saleDate){
						return saleDate.substring(0,saleDate.lastIndexOf(','));						
					}	
				},
				{data: 'saleTotal',
					render : function(saleTotal){
						return '<p> &#8377; ' + saleTotal + '</p>'; 
					}	
				},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: true,					
						render: function(data, type, full, meta) {
							if(full.saleInvoiceGenerated){
								return '\
								<a href="sell/viewSaleDetails/' +full.saleId+'" class="btn btn-sm btn-clean btn-icon" title="View Sale Details">\
								<i class="la la-eye"></i>\
								</a>';
							}else{
								return '\
								<a href="sell/generateSaleInvoice/' +full.saleId+'" class="btn btn-sm btn-clean btn-icon" title="Generate Invoice">\
								<i class="la la-file-invoice"></i>\
								</a>\
								<a href="sell/editSale/'+full.saleId+'" class="btn btn-sm btn-clean btn-icon" title="Edit Sale">\
								<i class="la la-edit"></i>\
								</a>\
								<a href="javascript:deleteSale(\'' +full.saleId+'\',\''+full.client.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Sale">\
								<i class="la la-trash"></i>\
								</a>\
								';	
							}							
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

function populateCostAndProductName(value){
	for( var i = 0; i<stockResponseData.length; i++){
		var stockId = stockResponseData[i]['stockId'];     
		var productName = stockResponseData[i]['product']['productName'];
		var productId = stockResponseData[i]['product']['productId'];
		var costPrice = stockResponseData[i]['order']['costPrice'];
		if(value == stockId){
			$("#sales_product_name").val(productName);	
			$("#salesProductId").val(productId);
			$("#salesCostPrice").val(costPrice);
			$("#sales_CostPrice").val(costPrice);
		}      	 
	}
}

function clearNewSalesForm(){
	$('.error').remove();
	$('#outOfStock').hide();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
	$('#sales_sellingPrice').val(0);
	$('#sales_quantity').val(0);	
	$('#saleDate').val('');
}

function clearEditSalesForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}

function submitForm(){	
	$('.error').remove();
	$('#validation_error').remove();
	var valid = true;
	var sales_sellingPrice = $('#sales_sellingPrice').val();
	var sales_quantity = $('#sales_quantity').val();
	var saleDate = $('#saleDate').val();
	if (saleDate.length < 1) {
		$('#saleDate_span').after('<span id="saleDate_error" class="error">please enter sale date</span>');
		$('#saleDate_span').hide();
		valid = false;
	}else{
		$('#saleDate_span').show();
		$('#saleDate_error').hide();
	}
	if (sales_sellingPrice.length < 1) {
		$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">please enter sale selling price</span>');
		$('#sales_sellingPrice_span').hide();
		valid = false;
	}else{
		if(isNaN(sales_sellingPrice)){
			$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">Selling price only allows numeric data</span>');
			$('#sales_sellingPrice_span').hide();
			valid = false;
		}else{
			if(Math.sign(sales_sellingPrice) == -1){
				$('#sales_sellingPrice').after('<span id="sales_sellingPrice_error" class="error">Selling price cannot be negative</span>');
				$('#sales_sellingPrice_span').hide();
				valid = false;
			}else{
				$('#sales_sellingPrice_span').show();
				$('#sales_sellingPrice_error').hide();
			}
		}
	}
	if (sales_quantity.length < 1) {
		$('#sales_quantity').after('<span id="sales_quantity_error" class="error">please enter sale quantity</span>');
		$('#sales_quantity_span').hide();
		valid = false;
	}else{
		if(isNaN(sales_quantity)){
			$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity only allows numeric data</span>');
			$('#sales_quantity_span').hide();
			valid = false;
		}else{
			if(Math.sign(sales_quantity) == -1){
				$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity cannot be negative</span>');
				$('#sales_quantity_span').hide();
				valid = false;
			}else if(sales_quantity == 0){
				$('#sales_quantity').after('<span id="sales_quantity_error" class="error">Sale quantity cannot be 0</span>');
				$('#sales_quantity_span').hide();
				valid = false;
			}else{
				$('#sales_quantity_span').show();
				$('#sales_quantity_error').hide();
			}
		}
	}
	if(valid){
		document.salesForm.action = "inventory/sales";
		document.getElementById("salesForm").submit();
	}
}

function submitEditForm(){	
	document.editSalesForm.action = "sell/editSale/"+$('#edit_saleId').val();
	document.getElementById("editSalesForm").submit();
}

function deleteSale(id,clientName){
	Swal.fire({
		title: "Are you sure you want to delete sale for " + clientName+ "!",
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
			return fetch(`${HOST_URL}/sell/deleteSale/${id}`)
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
				title: clientName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('sell/sales');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#sell_nav').addClass('menu-item-open menu-item-here');
	$('#sales_nav').addClass('menu-item-active');
}

function fetchStocks(){
	$.ajax({
		url: HOST_URL + '/sell/stock/getAllStock',
		type: 'get',
		dataType: 'json',
		success: function(response){
			stockResponseData = response.data;
			var productName;
			var productId;
			var costPrice;
			for( var i = 0; i<response.data.length; i++){
				var stockId = response.data[i]['stockId'];     
				productName = response.data[i]['product']['productName'];
				productId = response.data[i]['product']['productId'];
				costPrice = response.data[i]['order']['costPrice'];
				if(i == 0){
					$("#sales_product_name").val(productName);	
					$("#salesProductId").val(productId);
					$("#salesCostPrice").val(costPrice);
					$("#sales_CostPrice").val(costPrice);					
				}
				$("#sales_stock_id").append("<option value='"+stockId+"'>"+stockId+"</option>");
			}
		}
	});
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newSalesModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
