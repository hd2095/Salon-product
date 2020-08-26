"use strict";

var KTCalendarBackgroundEvents = function() {

	return {
		//main function to initiate the module
		init: function() {
			var todayDate = moment().startOf('day');
			var startDate = moment().startOf('month').format("YYYY-DD-MM");
			var endDate = moment().endOf("month").format("YYYY-DD-MM");
			var YM = todayDate.format('YYYY-MM');
			var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
			var TODAY = todayDate.format('YYYY-MM-DD');
			var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');

			var calendarEl = document.getElementById('schedule-calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],

				isRTL: KTUtil.isRTL(),
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'dayGridMonth,timeGridWeek,timeGridDay'
				},

				height: 800,
				contentHeight: 780,
				aspectRatio: 3, 

				nowIndicator: true,

				views: {
					dayGridMonth: { buttonText: 'month' },
					timeGridWeek: { buttonText: 'week' },
					timeGridDay: { buttonText: 'day' }
				},

				defaultView: 'dayGridMonth',
				defaultDate: TODAY,

				editable: false,
				eventLimit: true, // allow "more" link when too many events
				navLinks: true,
				businessHours: true, // display business hours 
				events:{	
					//url: HOST_URL + '/schedule/getSchedule?start='+startDate+'&end='+endDate,
					url: HOST_URL + '/schedule/getSchedule',
					method:'GET',
					failure:function(){
						alert('there was an error while fetching events!');
					},
					success:function(res){
						 	
					}
				},
				 eventRender: function(info) {					
	                    var element = $(info.el);
	                    if (info.event.extendedProps && info.event.extendedProps.description) {
	                        if (element.hasClass('fc-day-grid-event')) {
	                        	element.data('content', info.event.extendedProps.description);
	                            element.data('placement', 'top');
	                            KTApp.initPopover(element);
	                        } else if (element.hasClass('fc-time-grid-event')) {
	                            element.find('.fc-title').append('<div class="fc-description">' + info.event.extendedProps.description + '</div>');
	                        } else if (element.find('.fc-list-item-title').lenght !== 0) {
	                            element.find('.fc-list-item-title').append('<div class="fc-description">' + info.event.extendedProps.description + '</div>');
	                        }
	                    }
	                }
				/* events:{
					url: HOST_URL + '/appointment/getAllAppointmentsForCalendar',
					method:'GET',
					failure:function(){
						alert('there was an error while fetching events!');
					},
					success:function(res){

					}
				}, 
				 eventRender: function(info) {					
	                    var element = $(info.el);
	                    if (info.event.extendedProps && info.event.extendedProps.description) {
	                        if (element.hasClass('fc-day-grid-event')) {
	                        	element.data('content', info.event.extendedProps.description);
	                            element.data('placement', 'top');
	                            KTApp.initPopover(element);
	                        } else if (element.hasClass('fc-time-grid-event')) {
	                            element.find('.fc-title').append('<div class="fc-description">' + info.event.extendedProps.description + '</div>');
	                        } else if (element.find('.fc-list-item-title').lenght !== 0) {
	                            element.find('.fc-list-item-title').append('<div class="fc-description">' + info.event.extendedProps.description + '</div>');
	                        }
	                    }
	                }

				 */
			});

			calendar.render();
		}
	};
}();

jQuery(document).ready(function() {
	KTCalendarBackgroundEvents.init();
});
