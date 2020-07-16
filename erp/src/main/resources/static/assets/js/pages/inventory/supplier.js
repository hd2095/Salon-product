'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#supplier_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/inventory/getAllSuppliers',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'supplierName'},
				{data: 'supplierEmail'},
				{data: 'supplierNumber'},
				{data: 'supplierGstnNo'},
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {							
						return '\
							<a href="javascript:editSupplier(\'' +full.supplierId+'\');" class="btn btn-xs btn-custom" title="Edit Supplier">\
							<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteSupplier(\'' +full.supplierId+'\',\''+full.supplierName+'\');" class="btn btn-xs btn-custom" title="Delete Supplier">\
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
			initTable1();
		},

	};

}();

function submitForm(){	
	document.getElementById("supplierForm").submit();
}

function submitEditForm(){	
	document.editSupplierForm.action = "inventory/supplier/editSupplier/"+$('#edit_supplierId').val();
	document.getElementById("editSupplierForm").submit();
}

function editSupplier(id){
	$.ajax({
		url: HOST_URL + '/inventory/supplier/editSupplier/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				  if(key == 'data'){					  
					  $.each(value, function(k,v){
						  	$('#edit_supplierId').val(v.supplierId);
						  	$('#edit_supplierName').val(v.supplierName);
						  	$('#edit_supplier_address').val(v.supplier_address);
						  	$('#edit_supplierNumber').val(v.supplierNumber);
						  	$('#edit_supplierPincode').val(v.supplierPincode);
						  	$('#edit_supplierEmail').val(v.supplierEmail);						  
						  	if(v.supplierGstnNo == 'No GSTN No Provided'){
						  		$('#edit_supplierGstnNo').val('');
						  	}else{
						  		$('#edit_supplierGstnNo').val(v.supplierGstnNo);
						  	}						  							  
					  });
				  }
				});
			$('#editSupplierModal').modal();
		}
	});
}

function deleteSupplier(id,supplierName){
	Swal.fire({
		title: "Are you sure you want to delete " + supplierName+ "!",
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
			return fetch(`${HOST_URL}/inventory/supplier/deleteSupplier/${id}`)
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
					title: supplierName + " deleted successfully!",
					confirmButtonText: "OK"
				}).then(function(result){
					if(result.value){
						location.replace('inventory/addSupplier');
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
	$('#supplier_nav').addClass('active');
	$('#inventory_nav').addClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newSupplierModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
