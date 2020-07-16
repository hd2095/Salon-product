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
						<h3 class="panel-title">Sales</h3>
						<div class="btn-group" style="float: right; margin-top: -30px;">
							<a data-toggle="dropdown" id="dropdownMenuButton"
								style="background-color: #252c35; color: white;" class="btn">Add
								New</a>
							<ul class="dropdown-menu" role="menu"
								style="cursor: pointer; min-width: fit-content;">
								<li><a data-toggle="modal" data-target="#newSalesModal">In
										Stock </a></li>
								<li class="divider"></li>
								<li><a data-toggle="modal"
									data-target="#newSalesNotInStockModal">Not In Stock </a></li>
							</ul>
						</div>
					</div>
					<div class="panel-body">
						<!--begin: Datatable-->
						<table class="table table-striped table-bordered dt-responsive"
							style="width: 100%; margin-top: 13px !important"
							id="sales_dataTable">
							<thead>
								<tr>
									<th>Stock Id</th>
									<th>Product Name</th>
									<th>Client</th>
									<th>Supplier</th>
									<th>Cost Price</th>
									<th>Selling price</th>
									<th>Quantity</th>
								</tr>
							</thead>
						</table>
						<!-- <div></div>
						<div></div>
						<div></div>
						<div></div>
						<table class="table table-striped table-bordered dt-responsive"
							style="width: 100%; margin-top: 13px !important"
							id="sales_not_in_stock_dataTable">
							<thead>
								<tr>								
									<th>Product Name</th>
									<th>Client</th>
									<th>Supplier</th>
									<th>Cost Price</th>
									<th>Selling price</th>
									<th>Quantity</th>
									<th>Profit</th>
									<th>actions</th>
								</tr>
							</thead>
						</table> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="newSalesModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: max-content;">
				<div class="modal-header">
					<h3 class="modal-title" id="newSalesModalLabel">New Sale</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-sales-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button onclick="submitForm();" type="button" class="btn btn-black">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<!-- Modal-->
	<div class="modal fade" id="newSalesNotInStockModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: max-content;">
				<div class="modal-header">
					<h3 class="modal-title" id="newSalesNotInStockModal">New Sale Not in Stock </h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<%-- <jsp:include page="../forms/new-sales-notInStock-form.jsp" /> --%>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button onclick="submitNotInStockForm();" type="button" class="btn btn-black">Save
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
<!-- 		<script src="assets/js/pages/inventory/salesNotInStock.js"></script> -->
	<script src="assets/js/pages/inventory/sales.js"></script>
	<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
	<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
	<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>