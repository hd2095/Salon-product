//Class definition

var timePicker = function () {

	// Private functions
	var pickers = function () {
		//appointment form
		$('#appointment_time').timepicker({
			showMeridian:false
		});
		$('#edit_appointment_start_time').timepicker({
			showMeridian:false
		});
		//service form
		$('#service_duration').timepicker({
			showMeridian: false,
			maxHours: 6,
			minuteStep: 5,
			defaultTime: false
		});
		//edit service form
		$('#edit_service_duration').timepicker({
			showMeridian: false,
			maxHours: 6,
			minuteStep: 5,
			defaultTime: false
		});				
		//staff form
		$('#staff_in_time').timepicker({
			defaultTime: '9:00 AM'
		});
		$('#staff_out_time').timepicker({
			defaultTime: '6:00 PM'
		});
		//edit staff form
		$('#edit_staff_in_time').timepicker({
			defaultTime: false
		});
		$('#edit_staff_out_time').timepicker({
			defaultTime: false
		});
		//new schedule form
		$('#scheduleFrom').timepicker({});
		$('#scheduleTo').timepicker({});
		//edit schedule form
		$('#edit_scheduleFrom').timepicker({});
		$('#edit_scheduleTo').timepicker({});
	}

	return {
		// public functions
		init: function() {
			pickers();
		}
	};
}();

jQuery(document).ready(function() {
	timePicker.init();
});