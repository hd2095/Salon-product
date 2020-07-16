<html>
<head>
<base href="../">
<jsp:include page="../layout/side-nav.jsp" />
<link href="assets/css/dataTable/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/css/dataTable/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="panel panel-headline">
					<div class="panel-heading">
						<h3 class="panel-title">Suppliers</h3>
						<a
							style="float: right; margin-top: -30px; background-color: #252c35; color: white;"
							class="btn" data-toggle="modal" data-target="#newSupplierModal">Add
							New</a>
						<!--end::Actions-->
					</div>
					<div class="panel-body">
						<!--begin: Datatable-->
						<table class="table table-bordered table-hover table-checkable"
							id="supplier_dataTable" style="margin-top: 13px !important">
							<thead>
								<tr>
									<th>Supplier Name</th>
									<th>Supplier Email</th>
									<th>Supplier Contact</th>
									<th>Supplier GST No</th>
									<th>Actions</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="newSupplierModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 id="newSupplierModalLabel">New
						Supplier</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-supplier-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" onclick="submitForm()"
						class="btn btn-black">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<!-- Modal-->
	<div class="modal fade" id="editSupplierModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 id="editSupplierModalLabel">Edit
						Supplier</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/edit-supplier-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" onclick="submitEditForm()"
						class="btn btn-black">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<script>var HOST_URL = "${pageContext.request.contextPath}"</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/inventory/supplier.js"></script>
	<script
		src="assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"></script>
</body>
</html>