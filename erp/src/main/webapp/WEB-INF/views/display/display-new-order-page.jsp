<html>
<head>
<base href="../">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
<title>Grokar | Order</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Order</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<div class="d-flex align-items-center">
					<a href="buy/createOrder"
						class="btn btn-dark font-weight-bolder btn-sm">Place New Order
						</a>
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
						<table class="table table-bordered table-hover table-checkable"
							style="margin-top: 13px !important" id="order_dataTable">
							<thead>
								<tr>
									<th>Order No</th>
									<th>Order Date</th>
									<th>Order Total</th>
									<th>Status</th>
									<th>Order Received Date</th>
									<th>Actions</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}";
		jQuery(document).ready(function() {
			$('#loading-spinner').hide();
		});
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/buy/order.js" />"></script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>