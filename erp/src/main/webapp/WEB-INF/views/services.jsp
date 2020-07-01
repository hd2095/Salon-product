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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Services</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
			</div>
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="dropdown" id="dropdownMenuButton">Add New</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" data-toggle="modal"
						data-target="#newServiceModal">Add Service</a> <a
						class="dropdown-item" data-toggle="modal"
						data-target="#newCategoryModal">Add Category</a>
				</div>
				<!--end::Actions-->
			</div>
			<!--end::Info-->
		</div>
	</div>
	<!--end::Subheader-->
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newServiceModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newServiceModalLabel">New Service</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="forms/new-service-form.jsp" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary font-weight-bold">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="newCategoryModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newCategoryModal">New Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="forms/new-category-form.jsp" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary font-weight-bold">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<script src="http://localhost:8081/assets/js/pages/my-script.js"></script>