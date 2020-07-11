<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<!--begin::Content-->
	<%-- <form:form id="staffForm" method="get"></form:form> --%>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<!--begin::Subheader-->
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<!--begin::Page Title-->
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Staff</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<!--end::Actions-->
				</div>
				<div class="d-flex align-items-center">
					<%-- form:form action="staff/add" method="get" id="staffForm" name="staffForm">
					<input type="hidden"/> --%>
						<a href="staff/add"
							class="btn btn-light-warning font-weight-bolder btn-sm">New
							Staff</a>
					<%-- </form:form> --%>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<div class="card-body">
						<!--begin: Datatable-->
						<table class="table table-bordered table-hover table-checkable"
							id="staff_dataTable" style="margin-top: 13px !important">
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
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/staff/staff.js"></script>
	<script
		src="assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"></script>
	<script src="assets/js/pages/datePicker.js"></script>
	<script src="assets/js/pages/timePicker.js"></script>
</body>
</html>