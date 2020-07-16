<jsp:include page="layout/side-nav.jsp" />
<link href="assets/css/dataTable/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/css/dataTable/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<div class="panel panel-headline">
				<div class="panel-heading">
					<h3 class="panel-title">Appointments</h3>
					<a href="appointment/add" style="float: right; margin-top: -30px;background-color: #252c35;color: white;"
						class="btn btn-light-warning font-weight-bolder btn-sm">Add
						New</a>
					<!--end::Actions-->
				</div>
				<!--end::Info-->
				<!--begin::Toolbar-->
				<div class="panel-body">
					<!--begin: Datatable-->
					<table class="table table-striped table-bordered dt-responsive"
						style="width: 100%; margin-top: 13px !important"
						id="appointment_dataTable">
						<thead>
							<tr>
								<th>Appointment Date</th>
								<th>Appointment Time</th>
								<th>Appointment Service</th>
								<th>Staff</th>
								<th>Client</th>
								<th>Appointment Cost</th>
								<th>Appointment Status</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
					<!--end: Datatable-->
				</div>
			</div>
		</div>
		<!--end::Card-->
	</div>
</div>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>
<script src="assets/js/pages/appointment/appointment.js"></script>