'use strict';

var stockResponseData;

var saleNotInStock = function() {

	var initTable = function() {
		var table = $('#sales_not_in_stock_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/inventory/getAllSalesNotInStock',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'productName'},		
				{data: 'client.clientName'},
				{data: 'supplier.supplierName'},
				{data: 'costPrice'},
				{data: 'sellingPrice'},
				{data: 'quantity'},
				{data: 'profit'},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {							
							return '\
							<a href="javascript:editSale(\'' +full.saleId+'\');" class="btn btn-xs btn-custom" title="Edit Sale">\
							<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteSale(\'' +full.saleId+'\',\''+full.productName+'\');" class="btn btn-xs btn-custom" title="Delete Sale">\
							<i class="lnr lnr-trash"></i>\
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
			initTable();
		},

	};

}();


function submitForm(){	
	document.salesForm.action = "inventory/sales";
	document.getElementById("salesForm").submit();
}

function submitEditForm(){	
	document.editSalesForm.action = "inventory/sales/editSale/"+$('#edit_saleId').val();
	document.getElementById("editSalesForm").submit();
}

function editProduct(id){
	$.ajax({
		url: HOST_URL + '/inventory/sales/editSale/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				if(key == 'data'){					  
					$.each(value, function(k,v){
						$('#edit_saleId').val(v.saleId);
						$('#edit_productName').val(v.productName);
						$('#edit_productBrand').val(v.productBrand);
						if(v.productBarcode == 'No Barcode Provided'){
							$('#edit_productBarcode').val('');
						}else{
							$('#edit_productBarcode').val(v.productBarcode);
						}						  							  
					});
				}
			});
			$('#editProductModal').modal();
		}
	});
}

function deleteProduct(id,productName){
	Swal.fire({
		title: "Are you sure you want to delete " + productName+ "!",
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
			return fetch(`${HOST_URL}/inventory/sales/deleteSale/${id}`)
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
				title: productName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('inventory/sales');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#subPages').addClass('in');
	$('#sales_nav').addClass('active');
	$('#inventory_nav').addClass('active');
}


function fetchClients(){	
	$.ajax({
		url: HOST_URL + '/client/getAllClients',
		type: 'get',
		dataType: 'json',
		success: function(response){      
            for( var i = 0; i<response.data.length; i++){
            	var clientId = response.data[i]['clientId'];
            	var clientName = response.data[i]['fullName'];
            	 $("#sales_client").append("<option value='"+clientId+"'>"+clientName+"</option>");
               }
		}
	});
	
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newProductModal').modal();
	}
	setLinkActive();
	saleNotInStock.init();
	fetchClients();
});
