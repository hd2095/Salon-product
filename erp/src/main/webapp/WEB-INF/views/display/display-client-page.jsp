<jsp:include page="../layout/side-nav.jsp" />
<jsp:include page="../layout/header.jsp" />
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Clients</h5>
				<!--end::Page Title-->
			</div>
			<!--begin::Actions-->
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newClientModal">Add New</button>
			</div>
			<!--end::Actions-->
			<!--end::Info-->
		</div>
	</div>
	<!--end::Subheader-->
	<div class="card-body">		
		<!--begin::Search Form-->
		<div class="mb-7">
			<div class="row align-items-center">
				<div class="col-lg-9 col-xl-8">
					<div class="row align-items-center">
						<div class="col-md-4 my-2 my-md-0">
							<div class="input-icon">
								<input type="text" class="form-control" placeholder="Search..."
									id="kt_datatable_search_query" /> <span> <i
									class="flaticon2-search-1 text-muted"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-xl-4 mt-5 mt-lg-0">
					<a href="#" class="btn btn-light-primary px-6 font-weight-bold">Search</a>
				</div>
			</div>
		</div>
		<!--end::Search Form-->
		<!--end: Search Form-->
		<!--begin: Datatable-->
		<div class="datatable datatable-bordered datatable-head-custom"
			id="kt_datatable"></div>
		<!--end: Datatable-->
	</div>
	<!--end::Card-->

</div>
<!--end::Content-->
<div class="modal fade" id="newClientModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newClientModalLabel">New Client</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="../forms/new-client-form.jsp" />
			</div>
		</div>
	</div>
</div>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/searchClient/search-client-dataTable.js"></script>
<script src="assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"></script>