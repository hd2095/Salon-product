<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIN | Order Details</title>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
</head>
<body>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<!--begin::Subheader-->
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<!--begin::Page Title-->
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Order
						Details</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="row">
					<div class="col-xl-6">
						<div class="card card-custom card-stretch gutter-b">
							<div class="card-header border-0"></div>
							<div class="card-body pt-0">
								<div
									class="d-flex align-items-center mb-9 bg-light-warning rounded p-5">
									<div class="d-flex flex-column flex-grow-1 mr-2">
										<span class="font-weight-bold text-dark-75 font-size-lg mb-1">Order
											Total </span> <span class="font-weight-bold"> &#8360;
											${order.orderTotal}</span>
									</div>
								</div>
								<div
									class="d-flex align-items-center mb-9 bg-light-success rounded p-5">
									<div class="d-flex flex-column flex-grow-1 mr-2">
										<span class="font-weight-bold text-dark-75 font-size-lg mb-1">Order
											Date </span> <span class="font-weight-bold">
											${orderDate} </span>
									</div>
								</div>
								<div
									class="d-flex align-items-center mb-9 bg-light-info rounded p-5">
									<div class="d-flex flex-column flex-grow-1 mr-2">
										<span class="font-weight-bold text-dark-75 font-size-lg mb-1">Order
											Received Date </span> <span class="font-weight-bold">
											${orderReceivedDate}</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-6">
						<div class="card card-custom card-stretch gutter-b">
							<div class="card-header border-0"></div>
							<div class="card-body pt-0">
								<table class="table table-striped table-bordered">
									<thead class="thead-dark">
										<tr>
											<th>Product Name</th>
											<th>Brand</th>
											<th>Cost Price</th>
											<th>Quantity</th>
											<th>Total</th>
										</tr>
									</thead>
									<c:forEach items="${orderDetails}" var="item">
										<tr>
											<td>${item.product.productName}</td>
											<td>${item.product.productBrand}</td>
											<td>&#8360; ${item.productCostPrice}</td>
											<td>${item.productQuantity}</td>
											<td>&#8360; ${item.productCostPrice * item.productQuantity}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end::Content-->
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/view-details/view-order-details.js" />"></script>
</body>
</html>