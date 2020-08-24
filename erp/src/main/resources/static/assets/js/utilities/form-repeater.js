//Class definition
var KTFormRepeater = function() {
	var id = 1;
	var editId = 1;
	var orderId = 1;
	var editOrderId = 1;
	var saleId = 1;
	var editSaleId = 1;
	// Private functions
	var repeater = function() {
		//appointment form repeater
		$('#service_repeater').repeater({
			initEmpty: false,

			show: function() {
				var flag = checkIfValid(id);
				if(flag){
					$(this).slideDown();
					totalElements(id);
					fetchStaff(id);
					fetchServices(id);
					fetchTimePicker(id);
					id++;
				}else{
					alert('Please select service and staff');
					$(this).remove();
				}
			},

			hide: function(deleteElement) {
				if(confirm('Are you sure you want to delete this element?')) {
					subTotalCost($(this)[0].children[0].name.substring(1,2));
					subTotalDuration($(this)[0].children[0].name.substring(1,2))
					$(this).slideUp(deleteElement);
					decTotalElements();
					id--;					
				}
			}
		});

		$('#edit_service_repeater').repeater({
			initEmpty: false,
			show:function(){			
				if(localStorage.getItem("fetchedDetails")){
					$(this).slideDown();
					editId++;
				}else{
					var flag = checkIfEditValid(editId);
					if(flag){
						totalElementsEdit(editId);
						fetchStaffEdit(editId);
						fetchServicesEdit(editId);
						fetchTimePickerEdit(editId);
						$(this).slideDown();
						editId++;
					}else{
						alert('Please select service and staff');
						$(this).remove();
					}
				}
			},
			hide:function(deleteElement){
				if(confirm('Are you sure you want to delete this element?')) {										
					$(this).slideUp(deleteElement);
					if("" != $(this)[0].children[0].value){
						removeService($(this)[0].children[0].value);	
					}					
					subTotalCostEdit($(this)[0].children[0].name.substring(1,2));
					subTotalDurationEdit($(this)[0].children[0].name.substring(1,2));					
					editId--;
					decTotalElementsEdit();
				}
			}
		});

		//order form repeater
		$('#order_repeater').repeater({
			initEmpty: false,
			show:function(){
				var flag = checkIfOrderisValid(orderId);
				if(flag){
					$(this).slideDown();
					fetchProducts(orderId)
					fetchSuppliers(orderId);
					totalElements(orderId);
					orderId++;
				}else{					
					$(this).remove();
				}
			},
			hide:function(deleteElement){
				if(confirm('Are you sure you want to delete this element?')) {										
					$(this).slideUp(deleteElement);
					decTotalElements();
					decrementTotalCost($(this)[0].children[0].name.substring(1,2));
					orderId--;
				}
			}
		});
		$('#edit_order_repeater').repeater({
			initEmpty: false,
			show:function(){
				if(localStorage.getItem("fetchedOrderDetails")){
					$(this).slideDown();
					editOrderId++;
				}else{
					var flag = checkIfOrderisValidForEdit(editOrderId);
					if(flag){
						$(this).slideDown();
						totalElementsEdit(editOrderId);
						fetchEditProducts(editOrderId)
						fetchEditSuppliers(editOrderId);
						editOrderId++;
					}else{					
						$(this).remove();
					}
				}
			},
			hide:function(deleteElement){
				if(confirm('Are you sure you want to delete this element?')) {										
					$(this).slideUp(deleteElement);
					decTotalElementsEdit(editOrderId);
					if("" != $(this)[0].children[1].value){
						removeProductFromOrder($(this)[0].children[1].value);	
					}					
					decrementTotalCostForEdit($(this)[0].children[0].name.substring(1,2));
					editOrderId--;
				}
			}
		});
		$('#sale_repeater').repeater({
			initEmpty: false,
			show:function(){
				var flag = checkIfSaleisValid(saleId);
				if(flag){
					$(this).slideDown();
					fetchProducts(saleId);
					saleId++;
				}else{					
					$(this).remove();
				}
			},
			hide:function(deleteElement){
				if(confirm('Are you sure you want to delete this element?')) {										
					$(this).slideUp(deleteElement);
					saleId--;
				}
			}
		});
		$('#edit_sale_repeater').repeater({
			initEmpty: false,
			show:function(){
				if(localStorage.getItem("fetchedOrderDetails")){
					$(this).slideDown();
					editOrderId++;
				}else{
					var flag = checkIfOrderisValidForEdit(editOrderId);
					if(flag){
						$(this).slideDown();
						totalElementsEdit(editOrderId);
						fetchEditProducts(editOrderId)
						fetchEditSuppliers(editOrderId);
						editOrderId++;
					}else{					
						$(this).remove();
					}
				}
			},
			hide:function(deleteElement){
				if(confirm('Are you sure you want to delete this element?')) {										
					$(this).slideUp(deleteElement);
					decTotalElementsEdit(editOrderId);
					if("" != $(this)[0].children[1].value){
						removeProductFromOrder($(this)[0].children[1].value);	
					}					
					decrementTotalCostForEdit($(this)[0].children[0].name.substring(1,2));
					editOrderId--;
				}
			}
		});
	}

	return {
		// public functions
		init: function() {
			repeater();
		}
	};
}();

function checkIfSaleisValid(id){
	return true;
}

function checkIfOrderisValid(id){
	var flag = true;
	var previousId = id - 1;
	var selectedSupplier = $('select[name="['+ previousId +'][product_supplier]"').val();
	var selectedProduct = $('select[name="['+ previousId +'][order_product]"').val();
	var productCost = $('input[name="['+ previousId +'][product_cost]"').val();
	var productQuantity = $('input[name="['+ previousId +'][product_quantity]"').val();
	if(undefined == productCost || undefined == productQuantity || "" == productCost || "" == productQuantity || selectedProduct == 'Select' || selectedSupplier == 'Select'){
		flag = false;
		alert('All Fields are mandatory');
	}else if(parseInt(productQuantity) == 0){
		flag = false;
		alert('Quantity cannot be zero');
	}
	return flag;
}

function checkIfOrderisValidForEdit(id){
	var flag = true;
	var previousId = id - 1;
	var selectedSupplier = $('select[name="['+ previousId +'][edit_product_supplier]"').val();
	var selectedProduct = $('select[name="['+ previousId +'][edit_order_product]"').val();
	var productCost = $('input[name="['+ previousId +'][edit_product_cost]"').val();
	var productQuantity = $('input[name="['+ previousId +'][edit_product_quantity]"').val();
	if(undefined == productCost || undefined == productQuantity || "" == productCost || "" == productQuantity || selectedProduct == 'Select' || selectedSupplier == 'Select'){
		flag = false;
		alert('All Fields are mandatory');
	}else if(parseInt(productQuantity) == 0){
		flag = false;
		alert('Quantity cannot be zero');
	}
	return flag;
}

function checkIfValid(id){
	var flag = true;
	var previousId = id - 1;
	var selectedService = $('select[name="['+ previousId +'][appointment_service]"').val();
	var selectedStaff = $('select[name="['+ previousId +'][appointment_staff]"').val();
	if(selectedService == 'Select' || selectedStaff == 'Select'){
		flag = false;
	}
	return flag;
}

function checkIfEditValid(id){
	var flag = true;
	var previousId = id - 1;
	var selectedService = $('select[name="['+ previousId +'][edit_appointment_service]"').val();
	var selectedStaff = $('select[name="['+ previousId +'][edit_appointment_staff]"').val();
	if(selectedService == 'Select' || selectedStaff == 'Select'){
		flag = false;
	}
	return flag;
}

jQuery(document).ready(function() {
	KTFormRepeater.init();
});