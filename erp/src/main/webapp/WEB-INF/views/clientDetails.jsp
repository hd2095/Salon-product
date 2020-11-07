<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>OperateIn | Client Details</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">All
					Clients</h5>
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
		<div class="container">
			<div class="card card-custom">
				<div class="card-body">
					<table class="table table-bordered table-hover table-checkable"
						style="margin-top: 13px !important" id="all_clients_dataTable">
						<thead>
							<tr>
								<th>Client First Name</th>
								<th>Client Last Name</th>
								<th>Mobile Number</th>
								<th>Client Organization Name</th>
								<th>Client Current Plan</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var HOST_URL = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"/>"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/adminOperations/clientDetails.js" />"></script> 