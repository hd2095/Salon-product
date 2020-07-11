<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<link
	href="assets/plugins/custom/fullcalendar/fullcalendar.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
<!--begin::Content-->
</head>
<body>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<!--begin::Subheader-->
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<!--begin::Page Title-->
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Calendar</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class=" container ">
				<div class="card card-custom">
					<div class="card-header">
						<div class="card-toolbar">
							<select class="form-control select2" onchange="fetchEventsFor(this.value);" id="calendar_dropdown"
								 name="param">
							</select>
						</div>
					</div>
					<div class="card-body">
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end::Content-->
	<script src="assets/js/pages/my-script.js"></script>
	<script
		src="assets/plugins/custom/fullcalendar/fullcalendar.bundle.js?v=7.0.5"></script>
	<!--end::Page Vendors-->
	<!--begin::Page Scripts(used by this page)-->
	<script src="assets/js/pages/features/calendar/calendar.js"></script>
	<script src="assets/js/pages/select.js"></script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>