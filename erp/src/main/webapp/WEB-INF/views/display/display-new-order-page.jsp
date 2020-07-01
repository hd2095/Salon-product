<jsp:include page="../layout/side-nav.jsp" />
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../global-imports/all-global-imports.jsp" />
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Orders</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>			
				<!--begin::Search Form-->
				<div class="d-flex align-items-center" id="kt_subheader_search">
					<span class="text-dark-50 font-weight-bold" id="kt_subheader_total">450
						Total</span>
					<form class="ml-5">
						<div class="input-group input-group-sm input-group-solid"
							style="max-width: 175px">
							<input type="text" class="form-control"
								id="kt_subheader_search_form" placeholder="Search..." />
							<div class="input-group-append">
								<span class="input-group-text"> <span class="svg-icon">
										<!--begin::Svg Icon | path:assets/media/svg/icons/General/Search.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
																<g stroke="none" stroke-width="1" fill="none"
												fill-rule="evenodd">
																	<rect x="0" y="0" width="24" height="24" />
																	<path
												d="M14.2928932,16.7071068 C13.9023689,16.3165825 13.9023689,15.6834175 14.2928932,15.2928932 C14.6834175,14.9023689 15.3165825,14.9023689 15.7071068,15.2928932 L19.7071068,19.2928932 C20.0976311,19.6834175 20.0976311,20.3165825 19.7071068,20.7071068 C19.3165825,21.0976311 18.6834175,21.0976311 18.2928932,20.7071068 L14.2928932,16.7071068 Z"
												fill="#000000" fill-rule="nonzero" opacity="0.3" />
																	<path
												d="M11,16 C13.7614237,16 16,13.7614237 16,11 C16,8.23857625 13.7614237,6 11,6 C8.23857625,6 6,8.23857625 6,11 C6,13.7614237 8.23857625,16 11,16 Z M11,18 C7.13400675,18 4,14.8659932 4,11 C4,7.13400675 7.13400675,4 11,4 C14.8659932,4 18,7.13400675 18,11 C18,14.8659932 14.8659932,18 11,18 Z"
												fill="#000000" fill-rule="nonzero" />
																</g>
															</svg> <!--end::Svg Icon-->
								</span> <!--<i class="flaticon2-search-1 icon-sm"></i>-->
								</span>
							</div>
						</div>
					</form>
				</div>
				<!--end::Search Form-->
				<!--end::Actions-->
			</div>
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newOrderModal">Add New</button>
			</div>
			<!--end::Info-->
		</div>
	</div>
	<!--end::Subheader-->
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newOrderModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newOrderModalLabel">New
					Order</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="../forms/new-order-form.jsp" />
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
<script src="assets/js/pages/datePicker.js"></script>