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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Products</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div></div>				
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newAppointmentModal">Add
					New</button>
				<!--end::Actions-->
			</div>
			<!--end::Info-->
		</div>
	</div>
	<!--end::Subheader-->
</div>
<!--end::Content-->

<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/push-page.js"></script>