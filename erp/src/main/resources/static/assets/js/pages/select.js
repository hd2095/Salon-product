// Class definition
var KTSelect2 = function() {
	// Private functions
	var demos = function() {
		// basic
		$('#kt_select2_1').select2({
			placeholder: "Select a state"
		});
		
		// appointment id start
		
		$('#appointment_staff').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		$('#edit_appointment_staff').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		$('#appointment_client').select2({
			placeholder: "Select a client"
		});
		
		$('#edit_appointment_client').select2({
			placeholder: "Select a client"
		});
		
		$('#appointment_service').select2({
			placeholder: "Select a service"
		});
		
		$('#edit_appointment_service').select2({
			placeholder: "Select a service"
		});
		
		$('#appointment_status').select2({
			placeholder: "Select an option",
			width: "max-content",
			minimumResultsForSearch: Infinity
		});
		
		$('#edit_appointment_status').select2({
			placeholder: "Select an option",
			width: "max-content",
			minimumResultsForSearch: Infinity
		});
		
		// appointment id end

		$('#sales_stock_id').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});

	
		// nested
		$('#kt_select2_2').select2({
			placeholder: "Select a state"
		});

		// multi select
		$('#kt_select2_3').select2({
			placeholder: "Select a state",
		});
		
		// multi select
		$('#order_brand_dropdown').select2({
			placeholder: "Select a brand",
		});

		// multi select
		$('#edit_order_brand_dropdown').select2({
			placeholder: "Select a brand",
		});
		// basic
		$('#kt_select2_4').select2({
			placeholder: "Select a state",
			allowClear: true
		});

		// loading data from array
		var data = [{
			id: 0,
			text: 'Enhancement'
		}, {
			id: 1,
			text: 'Bug'
		}, {
			id: 2,
			text: 'Duplicate'
		}, {
			id: 3,
			text: 'Invalid'
		}, {
			id: 4,
			text: 'Wontfix'
		}];

		$('#kt_select2_5').select2({
			placeholder: "Select a value",
			data: data
		});

        function formatRepo(repo) {
            if (repo.loading) return repo.text;
            var markup = "<div class='select2-result-repository clearfix'>" +
                "<div class='select2-result-repository__meta'>" +
                "<div class='select2-result-repository__title'>" + repo.full_name + "</div>";
            if (repo.description) {
                markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
            }
            markup += "<div class='select2-result-repository__statistics'>" +
                "<div class='select2-result-repository__forks'><i class='fa fa-flash'></i> " + repo.forks_count + " Forks</div>" +
                "<div class='select2-result-repository__stargazers'><i class='fa fa-star'></i> " + repo.stargazers_count + " Stars</div>" +
                "<div class='select2-result-repository__watchers'><i class='fa fa-eye'></i> " + repo.watchers_count + " Watchers</div>" +
                "</div>" +
                "</div></div>";
            return markup;
        }

        function formatRepoSelection(repo) {
            return repo.full_name || repo.text;
        }


	    $("#kt_select2_6").select2({
            placeholder: "Search for git repositories",
            allowClear: true,
            ajax: {
                url: "https://api.github.com/search/repositories",
                dataType: 'json',
                delay: 250,
                data: function(params) {
                    return {
                        q: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function(data, params) {
                	console.log(data);
                    // parse the results into the format expected by Select2
                    // since we are using custom formatting functions we do not need to
                    // alter the remote JSON data, except to indicate that infinite
                    // scrolling can be used
                    params.page = params.page || 1;

                    return {
                        results: data.items,
                        pagination: {
                            more: (params.page * 30) < data.total_count
                        }
                    };
                },
                cache: true
            },
            escapeMarkup: function(markup) {
                return markup;
            }, // let our custom formatter work
            minimumInputLength: 1,
            templateResult: formatRepo, // omitted for brevity, see the source of this page
            templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });

        function formatClient(repo) {
            if (repo.loading) return repo.text;
            var markup = "<div class='select2-result-repository clearfix'>" +
                "<div class='select2-result-repository__meta'>" +
                "<div class='select2-result-repository__title'>" + repo.full_name + "</div>";
            if (repo.description) {
                markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
            }
            markup += "<div class='select2-result-repository__statistics'>" +
                "<div class='select2-result-repository__forks'><i class='fa fa-flash'></i> " + repo.forks_count + " Forks</div>" +
                "<div class='select2-result-repository__stargazers'><i class='fa fa-star'></i> " + repo.stargazers_count + " Stars</div>" +
                "<div class='select2-result-repository__watchers'><i class='fa fa-eye'></i> " + repo.watchers_count + " Watchers</div>" +
                "</div>" +
                "</div></div>";
            return markup;
        }

	    // custom styles

		// tagging support
		$('#kt_select2_12_1, #kt_select2_12_2, #kt_select2_12_3, #kt_select2_12_4').select2({
			placeholder: "Select an option",
		});

		// disabled mode
		$('#kt_select2_7').select2({
			placeholder: "Select an option"
		});

		// disabled results
		$('#kt_select2_8').select2({
			placeholder: "Select an option"
		});

		// limiting the number of selections
		$('#kt_select2_9').select2({
			placeholder: "Select an option",
			maximumSelectionLength: 2
		});

		// hiding the search box
		$('#kt_select2_10').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		$('#calendar_dropdown').select2({
			placeholder: "Select staff",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#category_dropdown').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#order_product_dropdown').select2({
			placeholder: "Select a product",
			minimumResultsForSearch: Infinity
		});
		
		$('#edit_order_product_dropdown').select2({
			placeholder: "Select a product",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#edit_order_brand_dropdown').select2({
			placeholder: "Select a product",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#order_status').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#member_type').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#member_status').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		// hiding the search box
		$('#supplier_select').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		// hiding the search box
		$('#edit_supplier_select').select2({
			placeholder: "Select an option",
			minimumResultsForSearch: Infinity
		});
		
		$('#kt_select2_13','#order_status').select2({
			placeholder: "Select an option",
			width: "max-content",
			minimumResultsForSearch: Infinity
		});
		$('#edit_order_status').select2({
			placeholder: "Select an option",
			width: "max-content",
			minimumResultsForSearch: Infinity
		});
		
		$('#kt_select2_14').select2({
			placeholder: "Select an option",
			width: "max-content",
			minimumResultsForSearch: Infinity
		});
		
		// tagging support
		$('#kt_select2_11').select2({
			placeholder: "Add a tag",
			tags: true
		});

		// disabled results
		$('.kt-select2-general').select2({
			placeholder: "Select an option"
		});
	}


	var modalDemos = function() {
		$('#kt_select2_modal').on('shown.bs.modal', function () {
			// basic
			$('#kt_select2_1_modal').select2({
				placeholder: "Select a state"
			});

			// nested
			$('#kt_select2_2_modal').select2({
				placeholder: "Select a state"
			});

			// multi select
			$('#kt_select2_3_modal').select2({
				placeholder: "Select a state",
			});

			// basic
			$('#kt_select2_4_modal').select2({
				placeholder: "Select a state",
				allowClear: true
			});
		});
	}

	// Public functions
	return {
		init: function() {
			demos();
			modalDemos();
		}
	};
}();

// Initialization
jQuery(document).ready(function() {
	KTSelect2.init();
});