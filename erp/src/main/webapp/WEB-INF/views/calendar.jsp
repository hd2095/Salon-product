<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<jsp:include page="layout/side-nav.jsp" />
<link href="assets/css/fullCalendar.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="panel panel-headline">
					<div class="panel-heading">
						<h3 class="panel-title">Calendar</h3>
						<div id="staff_dropdown" style="float: right; margin-top: -3%;">
							<select class="form-control"
								onchange="fetchEventsFor(this.value);" id="calendar_dropdown"
								name="param">
							</select>
						</div>
					</div>
					<div class="panel-body">
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end::Content-->
	<script src="assets/js/pages/features/calendar/calendar.js"></script>
	<script src="assets/js/pages/my-script.js"></script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/vendor/fullCalendar/moment.min.js"></script>
	<script src="assets/vendor/fullCalendar/fullCalendar.min.js"></script>
<!-- 	 <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth'
        });
        calendar.render();
      });

    </script> -->
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>