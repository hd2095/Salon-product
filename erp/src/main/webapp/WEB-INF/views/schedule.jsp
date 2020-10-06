<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Grokar | Schedule</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<link
	href="assets/plugins/custom/fullcalendar/fullcalendar.bundle.css?v=7.0.5"
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Schedule</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
			</div>
			<div class="d-flex align-items-center">
				<a href="schedule/add"
					class="btn btn-light-warning font-weight-bolder btn-sm">Add New</a>
				<!--end::Actions-->
			</div>
		</div>
	</div>
	<div class="d-flex flex-column-fluid">
		<!--begin::Container-->
		<div class="container">
			<div class="card card-custom">
				<div class="card-header">
					<div class="card-title">
						<h3 class="card-label">
							<span class="d-block text-muted pt-2 font-size-sm"></span>
						</h3>
					</div>
					<div class="card-toolbar"></div>
				</div>
				<div class="card-body">
					<div id="schedule-calendar"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
	});
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/plugins/custom/fullcalendar/fullcalendar.bundle.js?v=7.0.5" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/schedule/schedule.js" />"></script>