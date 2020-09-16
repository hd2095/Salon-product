<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
</head>
<body>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Dashboard</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<!--begin::Card-->
						<div class="card card-custom gutter-b">
							<!--begin::Header-->
							<div class="card-header h-auto">
								<!--begin::Title-->
								<div class="card-title py-5">
									<h3 class="card-label">Line Chart</h3>
								</div>
								<!--end::Title-->
							</div>
							<!--end::Header-->
							<div class="card-body">
								<!--begin::Chart-->
								<div id="chart_1"></div>
								<!--end::Chart-->
							</div>
						</div>
						<!--end::Card-->
					</div>
					<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
						<div class="card card-custom card-stretch gutter-b">
							<!--begin::Header-->
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bolder text-dark">Top
									Clients</h3>
							</div>
							<div class="card-body pt-2">
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">PHP, SQLite, Artisan CLI</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
						<div class="card card-custom card-stretch gutter-b">
							<!--begin::Header-->
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bolder text-dark">Top
									Staff</h3>
							</div>
							<div class="card-body pt-2">
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">PHP, SQLite, Artisan CLI</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
						<div class="card card-custom card-stretch gutter-b">
							<!--begin::Header-->
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bolder text-dark">Top
									Services</h3>
							</div>
							<div class="card-body pt-2">
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">PHP, SQLite, Artisan CLI</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="assets/js/pages/dashboard/dashboard.js" />"></script>
<script type="text/javascript"
	src="<c:url value="assets/js/chart/chart.js" />"></script>
</html>