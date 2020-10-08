<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grokar | Edit Order</title>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Edit
						Order</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter order details and submit</span>
					</div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<form:form class="form" id="editOrderForm" name="editOrderForm"
						modelAttribute="editOrderForm" method="post" autocomplete="off">
						<form:hidden id="edit_orderId" path="orderId" />
						<div class="card-body">
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Order
									Date:</label>
								<div class="col-lg-4 col-xl-4">
									<div class="input-group date">
										<form:input type="text" class="form-control" path="orderDate"
											readonly="readonly" id="edit_order_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="orderDate"></form:errors>
									<span id="order_date_span" class="form-text text-muted">Please
										enter order Date</span>
								</div>
							</div>
							<input type="hidden" name="edit_total_elements" value="0" />
							<div id="edit_order_repeater">
								<div data-repeater-list="" class="col-lg-10">
									<div data-repeater-item class="form-group">
										<input type="hidden" name="elementCount" /> <input
											type="hidden" name="edit_order_record_id" />
										<div class="card">
											<div class="card-body">
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Product: </label> <select
															onchange="populateBrandFieldForEdit(this.name,this.value)"
															class="form-control" name="edit_order_product"></select>
													</div>
													<div class="col-lg-5">
														<label>Brand: </label> <input type="text"
															class="form-control form-control-lg form-control-solid dropdown"
															name="edit_product_brand" readonly />
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Supplier:</label> <select class="form-control"
															name="edit_product_supplier"></select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Cost Price:</label> <input class="form-control "
															name="edit_product_cost"></input>
													</div>
													<div class="col-lg-5">
														<label>Quantity:</label> <input
															onkeyup="calculateOrderTotalForEdit(this.value,this.name);"
															class="form-control " name="edit_product_quantity"></input>
													</div>
												</div>
												<div class="form-group row" name="edit_product_received_div">
													<div class="col-lg-5">
														<label>Product Received </label> <span
															class="switch switch-icon"> <label> <input
																type="checkbox" onclick="receiveProduct(this.name);"
																name="edit_product_received" /> <span></span>
														</label>
														</span>
													</div>
												</div>
											</div>
											<div class="card-footer d-flex justify-content-between">
												<a href="javascript:;"> </a> <a href="javascript:;"
													data-repeater-delete=""
													class="btn btn-sm btn-danger font-weight-bold"> <i
													class="la la-trash-o"></i>Delete
												</a>
											</div>
										</div>
									</div>
								</div>
								<div data-repeater-create="" id="addProductBtn"
									class="btn btn-sm font-weight-bolder btn-light-primary">
									<i class="la la-plus"></i> Add Product
								</div>
							</div>
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Status :</label>
								<div class="col-lg-4 col-xl-4">
									<form:select class="form-control select2"
										id="edit_order_status" path="orderDeliveryStatus"
										name="orderDeliveryStatus">
										<option value="Select">Select an option</option>
										<option value="Received">Received</option>
										<option value="Cancelled">Cancelled</option>
									</form:select>
									<form:errors id="validation_error" path="orderDeliveryStatus"></form:errors>
									<span class="form-text text-muted">Please select order
										status </span>
								</div>
								<label class="col-xl-2 col-lg-2 col-form-label">Order
									Received Date:</label>
								<div class="col-lg-4 col-xl-4">
									<div class="input-group date">
										<form:input type="text" class="form-control"
											path="orderReceivedDate" readonly="readonly"
											id="edit_order_receive_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="orderReceivedDate"></form:errors>
									<span class="form-text text-muted">Please enter order
										Date</span>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Total
									Order Cost:</label>
								<div class="col-lg-4 col-xl-4">
									<form:input type="text" path="orderTotal" id="edit_order_cost"
										class="form-control form-control-lg form-control-solid"
										readonly="true" />
									<span class="form-text text-muted span-info">Total order
										cost</span>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<button type="reset" id="editOrderBtn"
										class="btn font-weight-bold btn-primary btn-shadow mr-2">Edit Order</button>
									<a href = "buy/newOrder"
										class="btn font-weight-bold btn-secondary btn-shadow">Cancel</a>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/form-repeater.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/select2.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/buy/edit-order.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var orderId = '${editOrderForm.orderId}';
		fetchOrderDetails(orderId);
	});
</script>
</html>