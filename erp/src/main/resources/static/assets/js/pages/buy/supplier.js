'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#supplier_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/buy/getAllSuppliers',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'supplierName'},
				{data: 'supplierNumber'},
				{data: 'supplierEmail'},				
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
							<a href="javascript:editSupplier(\'' +full.supplierId+'\');" class="btn btn-sm btn-clean btn-icon" title="Edit Supplier">\
							<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteSupplier(\'' +full.supplierId+'\',\''+full.supplierName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Supplier">\
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

function clearNewSupplierForm(){
	$('.error').remove();
	$('#supplierExists').hide();
	$("span[id$='_span']").show();
	$('#validation_error').remove();
	$('#supplierFullName').val('');
	$('#supplierEmail').val('');
	$('#supplierNumber').val('');
	$('#supplier_address').val('');
	$('#supplierPincode').val('');
	$('#supplierGstnNo').val('');
}

function clearEditSupplierForm(){
	$('.error').remove();
	$('#validation_error').remove();
	$("span[id$='_span']").show();
}


function submitForm(){	
	var valid = true;
	$('.error').remove();
	$('#supplierExists').hide();
	$('#validation_error').remove();
	var supplierFullName = $('#supplierFullName').val();
	var supplierNumber = $('#supplierNumber').val();
	var supplierEmail = $('#supplierEmail').val();
	var supplierPincode = $('#supplierPincode').val();
	if (supplierFullName.length < 1) {
		$('#supplierFullName').after('<span id="supplierFullName_error" class="error">please enter supplier name</span>');
		$('#supplierFullName_span').hide();
		valid = false;
	}else{
		$('#supplierFullName_span').show();
		$('#supplierFullName_error').hide();
	}
	if(supplierEmail.length > 0){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(supplierEmail);
		if (!validEmail) {
			valid = false;
			$('#supplierEmail_span').hide();
			$('#supplierEmail_span').after('<span id="supplierEmail_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(supplierEmail).toLowerCase());
			$('#supplierEmail_span').show();
			$('#supplierEmail_error').hide();
		}
	}
	if(supplierNumber < 1){
		$('#supplierNumber_span').after('<span id="supplierNumber_error" class="error">please enter supplier mobile number</span>');
		$('#supplierNumber_span').hide();
		valid = false;
	}else if(supplierNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var mobileNumber = regEx.test(supplierNumber);
		if (!mobileNumber) {
			valid = false;
			$('#supplierNumber_span').after('<span id="supplierNumber_error" class="error">Enter a valid mobile number</span>');
			$('#supplierNumber_span').hide();
		}else{
			$('#supplierNumber_error').hide();
			$('#supplierNumber_span').show();
		}
	}else{
		valid = false;
		$('#supplierNumber_span').after('<span id="supplierNumber_error" class="error">Enter a valid mobile number (10 digits)</span>');
		$('#supplierNumber_span').hide();
	}
	if(supplierPincode < 1){
		valid = false;
		$('#supplierPincode_span').after('<span id="supplierPincode_error" class="error">please enter supplier pin code</span>');
		$('#supplierPincode_span').hide();
	}else if(supplierPincode.length == 6){
		if(isNaN(supplierPincode)){
			valid = false;
			$('#supplierPincode_span').after('<span id="supplierPincode_error" class="error">Invalid supplier pin code enter 6 digits</span>');
			$('#supplierPincode_span').hide();
		}else{
			$('#supplierPincode_span').show();	
		}
	}else{
		valid = false;
		$('#supplierPincode_span').after('<span id="supplierPincode_error" class="error">Invalid supplier pin code enter 6 digits</span>');
		$('#supplierPincode_span').hide();
	}
	if(valid){
		document.getElementById("supplierForm").submit();
	}
}

function submitEditForm(){
	var valid = true;
	$('.error').remove();
	$('#validation_error').remove();
	var supplierFullName = $('#edit_supplierName').val();
	var supplierNumber = $('#edit_supplierNumber').val();
	var supplierEmail = $('#edit_supplierEmail').val();
	var supplierPincode = $('#edit_supplierPincode').val();
	if (supplierFullName.length < 1) {
		$('#edit_supplierName').after('<span id="edit_supplierName_error" class="error">please enter supplier name</span>');
		$('#edit_supplierName_span').hide();
		valid = false;
	}else{
		$('#edit_supplierName_span').show();
		$('#edit_supplierName_error').hide();
	}
	if(supplierEmail.length > 0){
		var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmail = regEx.test(supplierEmail);
		if (!validEmail) {
			valid = false;
			$('#edit_supplierEmail_span').hide();
			$('#edit_supplierEmail_span').after('<span id="edit_supplierEmail_error" class="error">Enter a valid email</span>');
		}else{
			regEx.test(String(supplierEmail).toLowerCase());
			$('#edit_supplierEmail_span').show();
			$('#edit_supplierEmail_error').hide();
		}
	}
	if(supplierNumber < 1){
		$('#edit_supplierNumber_span').after('<span id="edit_supplierNumber_error" class="error">please enter supplier mobile number</span>');
		$('#edit_supplierNumber_span').hide();
		valid = false;
	}else if(supplierNumber.length == 10){
		var regEx  = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		var mobileNumber = regEx.test(supplierNumber);
		if (!mobileNumber) {
			valid = false;
			$('#edit_supplierNumber_span').after('<span id="edit_supplierNumber_error" class="error">Enter a valid mobile number</span>');
			$('#edit_supplierNumber_span').hide();
		}else{
			$('#edit_supplierNumber_error').hide();
			$('#edit_supplierNumber_span').show();
		}
	}else{
		$('#edit_supplierNumber_span').after('<span id="edit_supplierNumber_error" class="error">Enter a valid mobile number (10 digits)</span>');
		$('#edit_supplierNumber_span').hide();
	}
	if(supplierPincode < 1){
		valid = false;
		$('#edit_supplierPincode_span').after('<span id="edit_supplierPincode_error" class="error">please enter supplier pin code</span>');
		$('#edit_supplierPincode_span').hide();
	}else if(supplierPincode.length == 6){
		if(isNaN(supplierPincode)){
			valid = false;
			$('#edit_supplierPincode_span').after('<span id="edit_supplierPincode_error" class="error">Invalid supplier pin code enter 6 digits</span>');
			$('#edit_supplierPincode_span').hide();
		}else{
			$('#edit_supplierPincode_span').show();	
		}
	}else{
		valid = false;
		$('#edit_supplierPincode_span').after('<span id="edit_supplierPincode_error" class="error">Invalid supplier pin code enter 6 digits</span>');
		$('#edit_supplierPincode_span').hide();
	}
	if(valid){
		document.editSupplierForm.action = "buy/supplier/editSupplier/"+$('#edit_supplierId').val();
		document.getElementById("editSupplierForm").submit();
	}
}

function editSupplier(id){
	$.ajax({
		url: HOST_URL + '/buy/supplier/editSupplier/'+id,
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
			return fetch(`${HOST_URL}/buy/supplier/deleteSupplier/${id}`)
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
					location.replace('buy/addSupplier');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#buy_nav').addClass('menu-item-open menu-item-here');
	$('#supplier_nav').addClass('menu-item-active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newSupplierModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
