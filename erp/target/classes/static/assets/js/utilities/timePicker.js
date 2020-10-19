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
			showMeridian:false,
			defaultTime: false
		});
		$('#staff_out_time').timepicker({
			showMeridian:false,
			defaultTime: false
		});
		//new schedule form
		$('#scheduleFrom').timepicker({
			showMeridian:false
		});
		$('#scheduleTo').timepicker({
			showMeridian:false
		});
		//edit schedule form
		$('#edit_scheduleFrom').timepicker({
			showMeridian:false
		});
		$('#edit_scheduleTo').timepicker({
			showMeridian:false
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
	timePicker.init();
});