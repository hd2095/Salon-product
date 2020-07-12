// Class definition

var KTBootstrapTimepicker = function () {

 // Private functions
 var demos = function () {
  // minimum setup
  $('#kt_timepicker_1, #kt_timepicker_1_modal').timepicker();
  
  $('#edit_appointment_start_time').timepicker();
  
  $('#staff_in_time,#staff_out_time, #kt_timepicker_1_modal').timepicker();

  // minimum setup
  $('#kt_timepicker_2, #service_duration, #kt_timepicker_2_modal').timepicker({
   minuteStep: 1,
   defaultTime: '',
   showSeconds: true,
   showMeridian: false,
   snapToStep: true
  });

  // default time
  $('#kt_timepicker_3, #kt_timepicker_3_modal').timepicker({
   defaultTime: '11:45:20 AM',
   minuteStep: 1,
   showSeconds: true,
   showMeridian: true
  });

  // default time
  $('#kt_timepicker_4, #kt_timepicker_4_modal').timepicker({
   defaultTime: '10:30:20 AM',
   minuteStep: 1,
   showSeconds: true,
   showMeridian: true
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
 KTBootstrapTimepicker.init();
});