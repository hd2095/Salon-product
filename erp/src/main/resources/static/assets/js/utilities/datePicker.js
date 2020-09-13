//Class definition

var datePicker = function () {

	var arrows;
	if (KTUtil.isRTL()) {
		arrows = {
				leftArrow: '<i class="la la-angle-right"></i>',
				rightArrow: '<i class="la la-angle-left"></i>'
		}
	} else {
		arrows = {
				leftArrow: '<i class="la la-angle-left"></i>',
				rightArrow: '<i class="la la-angle-right"></i>'
		}
	}

	// Private functions
	var pickers = function () { 
		// appointment form
		$('#appointment_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#edit_appointment_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});		
		//client form
		$('#clientCreatedDate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#editClientCreatedDate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#client_end_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#client_start_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#client_birthday').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//edit client form
		$('#edit_client_end_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#edit_client_start_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#edit_client_birthday').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//staff form 
		$('#staff_birthday').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#staff_start_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#staff_end_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#staffDetailsFormDate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#staffDetailsToDate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//new order form
		$('#order_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//edit order form
		$('#edit_order_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#edit_order_receive_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});	
		//sale form
		$('#sale_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//edit sale form
		$('#edit_sale_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//new schedule form
		$('#schedule_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//edit schedule form
		$('#edit_schedule_date').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//new member form
		$('#member_expires_on').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		$('#member_created_on').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
		//invoice details form
		$('#challanDate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});
	}

	return {
		// public functions
		init: function() {
			pickers();
		}
	};
}();

jQuery(document).ready(function() {
	datePicker.init();
});