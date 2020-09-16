<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
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
			</div>
			<div class="d-flex align-items-center">
				<a href="appointment/add/"
					class="btn btn-light-warning font-weight-bolder btn-sm">Add New</a>
				<!--end::Actions-->
			</div>
		</div>
	</div>
	<div class="d-flex flex-column-fluid">
		<!--begin::Container-->
		<div class="container">
			<div class="card card-custom">
				<div class="card-header flex-wrap border-0 pt-6 pb-0">
					<div class="card-title">
						<h3 class="card-label">
							<span class="d-block text-muted pt-2 font-size-sm"></span>
						</h3>
					</div>
					<div class="card-toolbar">
						<div class="d-flex align-items-center">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">View</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="appointment">Table View</a> <a
									class="dropdown-item" href="appointment/calendarView">Calendar
									View</a>
							</div>
							<!--end::Actions-->
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="mb-7">
						<div class="row align-items-center">
							<div class="col-lg-9 col-xl-8">
								<div class="row align-items-center">
									<div class="col-md-4 my-2 my-md-0">
										<div class="input-icon">
											<input type="text" class="form-control"
												placeholder="Search..." id="search_query" /> <span>
												<i class="flaticon2-search-1 text-muted"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--begin: Datatable-->
					<div class="datatable datatable-bordered datatable-head-custom"
						id="appointment_dataTable"></div>
					<!--end: Datatable-->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="assets/js/pages/appointment/appointment.js" />"></script>