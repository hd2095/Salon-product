//Class definition
var select2 = function() {
	// Private functions
	var dropdown = function() {
		// appointment form
		$('#appointment_service').select2({
			placeholder: "Select a service",
		});
		$('#appointment_staff').select2({
			placeholder: "Select staff"
		});
		$('#appointment_client').select2({
			placeholder: "Select client",
			"language": {
				"noResults": function(){
					var newClientToAdd = $('input[type="search"]').val();
					return "<a href='client?add=" + newClientToAdd +"' class='btn btn-sm font-weight-bolder btn-light-primary'><i class='la la-plus'></i>Add client</a>";
				}
			},
			escapeMarkup: function (markup) {
				return markup;
			}
		});	
		$('#edit_appointment_service').select2({
			placeholder: "Select a service",
		});
		$('#edit_appointment_staff').select2({
			placeholder: "Select staff"
		});
		$('#edit_appointment_client').select2({
			placeholder: "Select client"
		});	
		$('#edit_appointment_status').select2({
			placeholder: "Select Appointment Status",
			minimumResultsForSearch: Infinity
		});			
		//service form
		$('#category_dropdown').select2({
			placeholder: "Select Category",
			minimumResultsForSearch: Infinity
		});		
		// disabled mode
		$('#edit_service_category').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		//client form
		$('#client_plan_dropdown').select2({
			placeholder: "Select Plan",
			minimumResultsForSearch: Infinity
		});	
		$('#edit_client_plan_dropdown').select2({
			placeholder: "Select Plan",
			minimumResultsForSearch: Infinity
		});	
		//new order form
		$('#supplier_select').select2({
			placeholder: "Select a supplier",
			minimumResultsForSearch: Infinity
		});		
		$('#order_product_dropdown').select2({
			placeholder: "Select a product",
			minimumResultsForSearch: Infinity
		});		
		//edit order form
		$('#edit_order_status').select2({
			placeholder: "Select order status",
			minimumResultsForSearch: Infinity
		});	
		//new sale form
		$('#sale_client').select2({
			placeholder: "Select Client",
			minimumResultsForSearch: Infinity
		});
		//edit sale form
		$('#edit_sale_client').select2({
			placeholder: "Select Client",
			minimumResultsForSearch: Infinity
		});
		//
		$('#organization_type').select2({
			placeholder: "Select Organization Type",
			minimumResultsForSearch: Infinity
		});
	}


	// Public functions
	return {
		init: function() {
			dropdown();
		}
	};
}();

//Initialization
jQuery(document).ready(function() {
	select2.init();
});