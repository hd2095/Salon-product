<html>
<head>
<base href="../">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
<title>Grokar | Sale</title>
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
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
				</div>
				<div class="d-flex align-items-center">
					<a href="sell/createSale"
						class="btn btn-light-warning font-weight-bolder btn-sm">Add
						New</a>
					<!--end::Actions-->
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<div class="card-body">
						<!--begin: Datatable-->
						<table class="table table-bordered table-hover table-checkable"
							style="margin-top: 13px !important" id="sales_dataTable">
							<thead>
								<tr>
									<th>Client</th>
									<th>Sale Date</th>
									<th>Total Sales Cost</th>
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
	<%-- <div class="modal fade" id="newSalesNotInStockModal"
		data-backdrop="static" tabindex="-1" role="dialog"
		aria-labelledby="staticBackdrop" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: max-content;">
				<div class="modal-header">
					<h3 class="modal-title" id="newSalesNotInStockModal">New Sale
						Not in Stock</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-sales-notInStock-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button onclick="submitNotInStockForm();" type="button"
						class="btn btn-black">Save changes</button>
				</div>
			</div>
		</div>
	</div> --%>
	<!--End Modal-->
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/sell/sales.js" />"></script>
	<script type='text/javascript'>
		$(function() {
			$('#loading-spinner').hide();
			$('#edit_service_duration').timepicker({
				showMeridian : false,
				minuteStep : 5,
				defaultTime : false
			});
			$('#service_duration').timepicker({
				showMeridian : false,
				minuteStep : 5,
				defaultTime : false
			});
		});
		jQuery(document).ready(function() {
			var outOfStock = '${outOfStock}';
			console.log(outOfStock);
			if (outOfStock.length > 2) {
				$('#outOfStock').html(outOfStock);
				$('#outOfStock').show();
				$('#newSalesModal').modal();
			}
		});
	</script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>