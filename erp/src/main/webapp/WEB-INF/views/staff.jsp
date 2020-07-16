<jsp:include page="layout/side-nav.jsp" />
<link href="assets/css/dataTable/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/css/dataTable/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<div class="main">
	<div class="main-content">
		<div class="container-fluid">
			<div class="panel panel-headline">
				<div class="panel-heading">
					<h3 class="panel-title">Staff</h3>
					<a href="staff/add/"
						style="float: right; margin-top: -30px; background-color: #252c35; color: white;"
						class="btn">Add New</a>
					<!--end::Actions-->
				</div>
				<div class="panel-body">
					<!--begin: Datatable-->
					<table class="table table-striped table-bordered dt-responsive"
						style="width: 100%; margin-top: 13px !important"
						id="staff_dataTable">
						<thead>
							<tr>
								<th>Staff Name</th>
								<th>Mobile Number</th>
								<th>Email ID</th>
								<th>Gender</th>
								<th>Revenue Generated</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
					<!--end: Datatable-->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/staff/staff.js"></script>
<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>