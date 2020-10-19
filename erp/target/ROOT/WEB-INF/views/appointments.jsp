<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>OperateIN | Appointments</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
</head>
<!--begin::Content-->
<div class="content d-flex flex-column flex-column-fluid"
	id="kt_content">
	<!--begin::Subheader-->
	<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
		<div
			class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
			<!--begin::Info-->
			<div class="d-flex align-items-center flex-wrap mr-2">
				<!--begin::Page Title-->
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Appointments</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">View</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="appointment/calendarView">Calendar
							view</a> <a class="dropdown-item" href="appointment">Table view</a>
					</div>
				</div>
			</div>
			<c:if test="${showAddBtn}">
				<div class="d-flex align-items-center">
					<a href="appointment/add"
						class="btn btn-dark font-weight-bolder btn-sm">Create New
						Appointment</a>
					<!--end::Actions-->
				</div>
			</c:if>
		</div>
	</div>
	<div class="d-flex flex-column-fluid">
		<!--begin::Container-->
		<div class="container">
			<div class="card card-custom">
				<div class="card-body">
					<!--begin: Datatable-->
					<table class="table table-bordered table-hover table-checkable"
						style="margin-top: 13px !important" id="appointment_dataTable">
						<thead>
							<tr>
								<th>Appointment Date</th>
								<th>Client</th>
								<th>Appointment Total</th>
								<th>Appointment Time</th>
								<th>Status</th>
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
<script>
	jQuery(document).ready(function() {
		var userNotifiedSuccess = '${userNotifiedSuccess}';
		if (userNotifiedSuccess.length > 1) {
			$.notify({
				// options
				message : userNotifiedSuccess
			}, {
				// settings
				type : 'success',
				delay : 5000
			});
		}
		var userNotifiedFailure = '${userNotifiedFailure}';
		if (userNotifiedFailure.length > 1) {
			$.notify({
				// options
				message : userNotifiedFailure
			}, {
				// settings
				type : 'danger',
				delay : 5000
			});
		}
	});
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"/>"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/appointment/appointment.js" />"></script>