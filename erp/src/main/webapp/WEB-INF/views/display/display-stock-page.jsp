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
						<h3 class="panel-title">Stock</h3>
						<!--end::Actions-->
					</div>
					<div class="panel-body">
						<!--begin: Datatable-->
						<table class="table table-striped table-bordered dt-responsive"
							style="width: 100%; margin-top: 13px !important"
							id="stock_dataTable">
							<thead>
								<tr>
									<th>Stock Id</th>
									<th>Supplier Name</th>
									<th>Product Name</th>
									<th>Order Total</th>
									<th>Order Date</th>
									<th>Order Received Date</th>
									<th>Stock Quantity</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="newStockModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="newStockModalLabel">New Stock</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-stock-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary font-weight-bold">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/inventory/stock.js"></script>
	<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
	<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
	<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>
</body>
</html>