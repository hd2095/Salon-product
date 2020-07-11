// Class definition

var KTBootstrapDatepicker = function () {

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
	var demos = function () {
		// minimum setup
		$('#kt_datepicker_1').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			orientation: "bottom left",
			templates: arrows
		});

		// minimum setup for modal demo
		$('#kt_datepicker_1_modal').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			orientation: "bottom left",
			templates: arrows
		});

		// input group layout
		$('#kt_datepicker_2').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			orientation: "bottom left",
			templates: arrows
		});

		// input group layout for modal demo
		$('#kt_datepicker_2_modal').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			orientation: "bottom left",
			templates: arrows
		});

		// enable clear button
		$('#kt_datepicker_3,#staff_birthday,#edit_staff_birthday,#edit_client_birthday,#kt_datepicker_3_validate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			orientation: "top right",
			todayHighlight: true,
			templates: arrows
		});


		// enable clear button
		$('#staff_start_date,#staff_end_date, #kt_datepicker_3_validate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			orientation: "top right",
			todayHighlight: true,
			templates: arrows
		});

		
		// enable clear button
		$('#kt_datepicker_4,#edit_appointment_date,#order_date,#edit_order_date,#kt_datepicker_4_validate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,		
			todayHighlight: true,
			templates: arrows
		});
		
		// enable clear button
		$('#member_created_on,#edit_client_start_date,#client_start_date,#member_created_on_validate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,		
			todayHighlight: true,
			templates: arrows
		});
		
		// enable clear button
		$('#member_expires_on,#edit_client_end_date,#client_end_date,#member_expires_on_validate').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,		
			todayHighlight: true,
			templates: arrows
		});
		
		// enable clear button for modal demo
		$('#kt_datepicker_3_modal').datepicker({
			rtl: KTUtil.isRTL(),
			todayBtn: "linked",
			clearBtn: true,
			todayHighlight: true,
			templates: arrows
		});

		// orientation
		$('#kt_datepicker_4_1').datepicker({
			rtl: KTUtil.isRTL(),
			orientation: "top left",
			todayHighlight: true,
			templates: arrows
		});

		$('#kt_datepicker_4_2').datepicker({
			rtl: KTUtil.isRTL(),
			orientation: "top right",
			todayHighlight: true,
			templates: arrows
		});

		$('#kt_datepicker_4_3').datepicker({
			rtl: KTUtil.isRTL(),
			orientation: "bottom left",
			todayHighlight: true,
			templates: arrows
		});

		$('#kt_datepicker_4_4').datepicker({
			rtl: KTUtil.isRTL(),
			orientation: "bottom right",
			todayHighlight: true,
			templates: arrows
		});

		// range picker
		$('#kt_datepicker_5').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			templates: arrows
		});

		 // inline picker
		$('#kt_datepicker_6').datepicker({
			rtl: KTUtil.isRTL(),
			todayHighlight: true,
			templates: arrows
		});
	}

	return {
		// public functions
		init: function() {
			demos();
		}
	};
}();

jQuery(document).ready(function() {
	KTBootstrapDatepicker.init();
});