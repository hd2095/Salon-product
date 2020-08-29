<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIn | New Order</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">New
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
					<form:form class="form" id="orderForm" name="orderForm"
						modelAttribute="orderForm" method="post" autocomplete="off">
						<div class="card-body">
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Order
									Date:</label>
								<div class="col-lg-4 col-xl-4">
									<div class="input-group date">
										<form:input type="text" class="form-control" path="orderDate"
											readonly="readonly" id="order_date" />
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
							<input type="hidden" name="total_elements" value="0" />
							<div id="order_repeater">
								<div data-repeater-list="" class="col-lg-10">
									<div data-repeater-item class="form-group">
									<input type="hidden" name="elementCount"/>
										<div class="card">
											<div class="card-body">
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Product: </label> <select onchange="populateBrandField(this.name,this.value)"
															class="form-control"
															name="order_product"></select>
													</div>
													<div class="col-lg-5">
														<label>Brand: </label> <input 
															type="text"
															class="form-control form-control-lg form-control-solid dropdown"
															name="product_brand" readonly/>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Supplier:</label> <select
															class="form-control"
															name="product_supplier"></select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Cost Price:</label> <input 
															class="form-control"
															name="product_cost"></input>
													</div>
													<div class="col-lg-5">
														<label>Quantity:</label> <input onkeyup="calculateOrderTotal(this.value,this.name);"
															class="form-control"
															name="product_quantity"></input>
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
								<div data-repeater-create=""
									class="btn btn-sm font-weight-bolder btn-light-primary">
									<i class="la la-plus"></i> Add Product
								</div>
							</div>
							<%-- <div class="form-group row">
								<div class="col-lg-6">
									<label>Product:</label> <select
										onchange="populateBrandField(this.value)"
										class="form-control select2" id="order_product_dropdown"
										name="param" multiple="multiple">
									</select>
									<form:errors id="validation_error" path="product.productName"></form:errors>
									<span class="form-text text-muted">Please select product
									</span>
								</div>
								<div class="col-lg-6">
									<label>Brand:</label>
									<form:input type="text" class="form-control" id="order_brand"
										path="product.productBrand" name="brand" disabled="true" />
									<form:errors id="validation_error" path="product.productBrand"></form:errors>
									<span class="form-text text-muted">Please select product
										brand</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Supplier:</label>
									<form:select class="form-control select2" id="supplier_select"
										path="supplier.supplierId" name="param">
									</form:select>
									<form:errors id="validation_error" path="supplier.supplierId"></form:errors>
									<span class="form-text text-muted">Please select
										Supplier</span>
								</div>
								<div class="col-lg-6">
									<label>Order Date:</label>
									<div class="input-group date">
										<form:input type="text" class="form-control" path="orderDate"
											readonly="readonly" id="order_date" />
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
							</div> --%>
							<%-- 							<div class="form-group row">
								<div class="col-lg-6">
									<label>Cost Price:</label>
									<form:input type="text" class="form-control" path="costPrice"
										id="costPrice" placeholder="" />
									<form:errors id="validation_error" path="costPrice"></form:errors>
									<span id="costPrice_span" class="form-text text-muted">Please
										enter product Cost Price</span>
								</div>
								<div class="col-lg-6">
									<label>Quantity:</label>
									<form:input type="text" class="form-control" path="quantity"
										id="quantity" placeholder="e.g 100" />
									<form:errors id="validation_error" path="quantity"></form:errors>
									<span id="quantity_span" class="form-text text-muted">Please
										enter product quantity</span>
								</div>
							</div> --%>
							<%-- 							<div class="form-group row">
								<div class="col-lg-6">
									<label>Status:</label>
									<form:select class="form-control" id="order_status"
										path="orderStatus" name="param" disabled="true">
										<option value="Booked" selected>Booked</option>
										<option value="Receieved">Received</option>
										<option value="Delivered">Delivered</option>
										<option value="Cancelled">Cancelled</option>
									</form:select>
									<form:errors id="validation_error" path="orderStatus"></form:errors>
									<span class="form-text text-muted">Please select order
										status </span>
								</div>
							</div> --%>
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Total
									Order Cost:</label>
								<div class="col-lg-4 col-xl-4">
									<form:input type="text" path="orderTotal" id="order_cost"
										class="form-control form-control-lg form-control-solid"
										readonly="true"/> <span
										class="form-text text-muted span-info">Total order cost</span>
								</div>
							</div>
							<div class="card-footer">
								<div class="row">
									<div class="col-lg-6"></div>
									<div class="col-lg-6 text-right">
										<button type="reset" onclick="submitForm();"
											class="btn font-weight-bold btn-primary btn-shadow mr-2">Submit</button>
										<button type="reset"
											class="btn font-weight-bold btn-secondary btn-shadow">Cancel</button>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="assets/js/utilities/form-repeater.js"></script>
<script src="assets/js/utilities/push-divs.js"></script>
<script src="assets/js/utilities/datePicker.js"></script>
<script src="assets/js/utilities/select2.js"></script>
<script src="assets/js/pages/buy/order-details.js"></script>
</html>