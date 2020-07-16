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
						<h3 class="panel-title">Products</h3>
						<a style="float:right; margin-top:-30px;background-color: #252c35;color: white;"
							class="btn"
							data-toggle="modal" data-target="#newProductModal">Add
							New</a>
						<!--end::Actions-->
					</div>
					<div class="panel-body">
						<!--begin: Datatable-->
						<table class="table table-striped table-bordered dt-responsive"
							style="width: 100%; margin-top: 13px !important"
							id="product_dataTable">
							<thead>
								<tr>
									<th>Product Name</th>
									<th>Product Brand</th>
									<th>Product Barcode</th>
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
	<div class="modal fade" id="newProductModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="newProductModalLabel">New Product</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-product-form.jsp" />
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
	<div class="modal fade" id="editProductModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="editProductModalLabel">Edit
						Product</h3>
					<a class="close" data-dismiss="modal"
						aria-label="Close">					
					</a>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/edit-product-form.jsp" />
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
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
	<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
	<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>
	<script src="assets/js/pages/inventory/product.js"></script>
</body>
</html>