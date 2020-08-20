"use strict";

var calendar;
var empty = false;
var KTCalendarBackgroundEvents = function() {

	return {
		//main function to initiate the module
		init: function() {
			var todayDate = moment().startOf('day');
			var YM = todayDate.format('YYYY-MM');
			var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
			var TODAY = todayDate.format('YYYY-MM-DD');
			var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');
			var calendarEl = document.getElementById('calendar');
			if(!empty){
				calendar =new FullCalendar.Calendar(calendarEl, {

					headerToolbar: {
						left: 'prev,next today',
						center: 'title',
						right: 'dayGridMonth,timeGridWeek,timeGridDay'
					},

					height: 800,
					contentHeight: 780,
					aspectRatio: 3,  // see: https://fullcalendar.io/docs/aspectRatio

					nowIndicator: true,
					now: TODAY + 'T09:25:00', // just for demo

					views: {
						dayGridMonth: { buttonText: 'month' },
						timeGridWeek: { buttonText: 'week' },
						timeGridDay: { buttonText: 'day' }
					},

					editable: false,
					navLinks: true,
					businessHours: false, // display business hours
					events:{
						url: HOST_URL + '/calendar/getAllEvents/'+$('#calendar_dropdown :selected').val(),
						method:'GET',
						failure:function(){
							alert('there was an error while fetching events!');
						},
						success:function(res){
							console.log(res);                	
						}
					}
				});
			}else{
				calendar =new FullCalendar.Calendar(calendarEl, {
					headerToolbar: {
						left: 'prev,next today',
						center: 'title',
						right: 'dayGridMonth,timeGridWeek,timeGridDay'
					},

					height: 800,
					contentHeight: 780,
					aspectRatio: 3,  // see: https://fullcalendar.io/docs/aspectRatio

					nowIndicator: true,
					now: TODAY + 'T09:25:00', // just for demo

					views: {
						dayGridMonth: { buttonText: 'month' },
						timeGridWeek: { buttonText: 'week' },
						timeGridDay: { buttonText: 'day' }
					},

					editable: false,
					navLinks: true,
					businessHours: false, // display business hours
				});
			}
			calendar.render();
		}
	};
}();

function fetchEventsFor(){
	calendar.destroy();
	KTCalendarBackgroundEvents.init();
}

function fetchStaff(){
	$.ajax({
		url: HOST_URL + '/staff/getAllStaff',
		type: 'get',
		dataType: 'json',
		success: function(response){
			for( var i = 0; i<response.data.length; i++){
				var staff_id = response.data[i]['staffId'];
				var staff_name = response.data[i]['fullName'];                
				$("#calendar_dropdown").append("<option value='"+staff_id+"'>"+staff_name+"</option>");
			}
			if(response.data.length < 1){
				empty = true;
			}
			KTCalendarBackgroundEvents.init();
		}
	});
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#calendar_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function() {
	setLinkActive();
	fetchStaff();
});
