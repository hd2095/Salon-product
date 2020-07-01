<jsp:include page="layout/side-nav.jsp" />
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
						<a href="#" class="btn btn-light-primary font-weight-bold"> <i
							class="ki ki-plus "></i> Filter
						</a>
					</div>
				</div>
				<div class="card-body">
					<div id="kt_calendar"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--end::Content-->
	<script src="http://localhost:8081/assets/js/pages/my-script.js"></script>
	<script src="http://localhost:8081/assets/js/pages/features/calendar/background-events.js"></script>