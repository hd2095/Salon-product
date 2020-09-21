<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Staff</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
			</div>
			<div class="d-flex align-items-center">
				<a href="staff/add/"
					class="btn btn-light-warning font-weight-bolder btn-sm">Add New</a>
				<!--end::Actions-->
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
					<table class="table table-bordered table-hover table-checkable"
						style="margin-top: 13px !important" id="staff_dataTable">
						<thead>
							<tr>
								<th>Staff Name</th>
								<th>Mobile Number</th>
								<th>Email ID</th>
								<th>Revenue Generated</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal-->
<div class="modal fade" id="staffDetailsModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3 id="staffDetailsModalLabel">Staff Details</h3>
			</div>
			<div class="modal-body">
				<form:form modelAttribute="staffDetailsForm" class="form"
					name="staffDetailsForm" autocomplete="off"
					action="staff/addDetails" method="post" id="staffDetailsForm">
					<form:hidden id="detailStaffId" path="staff" />
					<div class="form-group row">
						<div class="col-lg-6">
							<label>From Date:</label>
							<div class="input-group date">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="la la-calendar"></i></span>
								</div>
								<form:input path="fromDate" type="text" class="form-control"
									readonly="readonly" id="staffDetailsFormDate" />
							</div>
							<span class="form-text text-muted ">select staff from date</span>
						</div>
						<div class="col-lg-6">
							<label>To Date:</label>
							<div class="input-group date">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="la la-calendar"></i></span>
								</div>
								<form:input path="toDate" type="text" class="form-control"
									readonly="readonly" id="staffDetailsToDate" />
							</div>
							<span class="form-text text-muted ">select staff to date</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Commission Percentage:</label>
							<div class="input-group">
								<form:input path="commissionPercent" id="commissionPercentage"
									type="text" class="form-control" />
							</div>
							<span id="clientLoyaltyPoints_span" class="form-text text-muted">Staff
								Commission percentage</span>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary mr-2"
					data-dismiss="modal" onclick="calculateCommission();">Calculate
					Commission</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var commission = '${commission}';
		if(commission > 0){
			alert('YA THIS ONE'+commission);
		}
	});
</script>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"/>"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/staff/staff.js" />"></script>