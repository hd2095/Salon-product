<jsp:include page="../layout/side-nav.jsp" />
<jsp:include page="../layout/header.jsp" />
<link
	href="../assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Sales</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				<!--end::Actions-->
			</div>
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newSalesModal">Add New</button>
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
					<!--begin: Datatable-->
					<table class="table table-bordered table-hover table-checkable"
						id="sales_dataTable" style="margin-top: 13px !important">
						<thead>
							<tr>
								<th>Stock Id</th>
								<th>Product Name</th>
								<th>Cost Price</th>
								<th>Selling price</th>
								<th>Quantity</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
					<!--end: Datatable-->
				</div>
			</div>
		</div>
	</div>
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newSalesModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content" style="width: max-content;">
			<div class="modal-header">
				<h5 class="modal-title" id="newSalesModalLabel">New Sale</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="../forms/new-sales-form.jsp" />
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
<script src="../assets/js/pages/my-script.js"></script>
<script src="../assets/js/pages/select.js"></script>
<script src="../assets/js/pages/inventory/sales.js"></script>
<script
	src="../assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"></script>