<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servmore | Sale Details</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Sale
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
					<div class="col-xl-4">
						<div class="card card-custom gutter-b card-stretch">
							<div class="card-header border-0 pt-5">
								<div class="card-title">
									<div class="card-label">
										<div class="font-weight-bolder">Sale Total : &#8360;
											${sale.saleTotal}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4">
						<div class="card card-custom gutter-b card-stretch">
							<div class="card-header border-0 pt-5">
								<div class="card-title">
									<div class="card-label">
										<div class="font-weight-bolder">Sale Date : ${saleDate}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4">
						<div class="card card-custom gutter-b card-stretch">
							<div class="card-header border-0 pt-5">
								<div class="card-title">
									<div class="card-label">
										<div class="font-weight-bolder">Client :
											${sale.client.fullName}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<c:forEach items="${saleDetails}" var="item">
						<div class="col-xl-4">
							<div class="card card-custom gutter-b">
								<div class="card-header">
									<div class="card-title">
										<h3 class="card-label">
											${item.product.productName} <small>${item.product.productBrand}</small>
										</h3>
									</div>
								</div>
								<div class="card-body">
									<p>Selling Price : &#8360; ${item.sellingPrice}</p>
									<p>Quantity : ${item.quantity}</p>
									<p>Total : &#8360; ${item.sellingPrice * item.quantity}</p>
								</div>
							</div>
						</div>						
					</c:forEach>
				</div>
				<div class="row">
					<div class="col-xl-6">
						<div class="card card-custom gutter-b card-stretch">
							<div class="card-header border-0 pt-5">
								<div class="card-title">
									<div class="card-label">
										<div class="font-weight-bolder">Sale Notes :
											${sale.saleNotes}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end::Content-->
	<script src="assets/js/utilities/push-divs.js"></script>
</body>
</html>