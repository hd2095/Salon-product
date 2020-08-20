<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIn | New Sale</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">New Sale</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter sale details and submit</span>
					</div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<form:form class="form" modelAttribute="salesForm" method="post"
						id="salesForm" name="salesForm" autocomplete="off">
						<div class="card-body">
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Sale
									Date:</label>
								<div class="col-lg-4 col-xl-4">
									<div class="input-group date">
										<form:input type="text" class="form-control" path="saleDate"
											readonly="readonly" id="sale_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="saleDate"></form:errors>
									<span id="sale_date_span" class="form-text text-muted">Please
										enter sale Date</span>
								</div>
								<label class="col-xl-2 col-lg-2 col-form-label">Client:</label>
								<div class="col-lg-4 col-xl-4">
									<form:select path="client" id="sale_client"
										class="form-control select2" name="param">
									</form:select>
									<span class="form-text text-muted">Please select client</span>
								</div>
							</div>
							<input type="hidden" name="sale_total_elements" value="0" />
							<div id="sale_repeater">
								<div data-repeater-list="" class="col-lg-10">
									<div data-repeater-item class="form-group">
										<input type="hidden" name="elementCount" />
										<div class="card">
											<div class="card-body">
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Product: </label> <select
															class="form-control form-control-lg form-control-solid dropdown"
															name="sale_product"></select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Selling Price:</label> <input
															class="form-control form-control-lg form-control-solid"
															name="product_selling_price"></input>
													</div>
													<div class="col-lg-5">
														<label>Quantity:</label> <input
															onkeyup="calculateOrderTotal(this.value,this.name);"
															class="form-control form-control-lg form-control-solid"
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
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Total
									Sale Cost:</label>
								<div class="col-lg-4 col-xl-4">
									<form:input type="text" path="saleTotal" id="sale_cost"
										class="form-control form-control-lg form-control-solid" />
									<span class="form-text text-muted span-info">Total sale
										cost</span>
								</div>
								<label class="col-xl-2 col-lg-2 col-form-label">Sale
									Notes:</label>
								<div class="col-lg-4 col-xl-4">
									<form:textarea id="sale_notes" path="saleNotes"
										class="form-control form-control-lg form-control-solid"></form:textarea>
									<span class="form-text text-muted span-info">Please
										enter sale notes</span>
								</div>
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
						<%-- 	
						<input type="hidden" id="salesProductId" name="salesProductId"></input>
						<input type="hidden" id="sales_CostPrice" name="sales_CostPrice"></input>
							<div class="card-body">
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Product Name:</label>
									<form:input type="text" class="form-control"
										id="sales_product_name" path="product.productName"
										disabled="true" />
									<span class="form-text text-muted">Please enter product
										name</span>
								</div>
								<div class="col-lg-6">
									<label>Stock Id:</label>
									<form:select path="stock"
										onchange="populateCostAndProductName(this.value);"
										class="form-control" id="sales_stock_id" name="param">
									</form:select>
									<span class="form-text text-muted">Please select stock
										id</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Cost Price:</label>
									<form:input type="text" class="form-control" path="costPrice"
										id="salesCostPrice" disabled="true" />
									<form:errors id="validation_error" path="costPrice"></form:errors>
									<span id="salesCostPrice_span" class="form-text text-muted">Please
										enter sale cost price</span>
								</div>
								<div class="col-lg-6">
									<label>Selling Price:</label>
									<form:input type="text" class="form-control"
										path="sellingPrice" id="sales_sellingPrice"
										placeholder="e.g. 100" />
									<form:errors id="validation_error" path="sellingPrice"></form:errors>
									<span id="sales_sellingPrice_span" class="form-text text-muted">Please
										enter sale selling price</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Client:</label>
									<form:select id="sales_client" path="client"
										class="form-control select2" name="param">
									</form:select>
									<span class="form-text text-muted">Please select client</span>
								</div>
								<div class="col-lg-6">
									<label>Quantity:</label>
									<form:input type="text" class="form-control" path="quantity"
										id="sales_quantity" placeholder="e.g. 10" />
									<form:errors id="validation_error" path="quantity"></form:errors>
									<span id="sales_quantity_span" class="form-text text-muted">Please
										enter sales quantity</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Sale Date:</label>
									<div class="input-group date">
										<form:input type="text" class="form-control"
											readonly="readonly" path="saleDate" id="saleDate" />
										<span class="input-group-addon"><i
											class="lnr lnr-calendar-full"></i></span>
									</div>
									<form:errors id="validation_error" path="saleDate"></form:errors>
									<span id="saleDate_span" class="form-text text-muted span-info">Please
										select sale date</span>
								</div>
								<div class="col-lg-6">
									<label>Sales Description:</label>
									<form:textarea type="text" class="form-control"
										path="saleNotes"
										placeholder="This sale was done with pleasure" />
									<span class="form-text text-muted">Please enter sales
										description</span>
								</div>
							</div>
						</div> --%>
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
<script src="assets/js/pages/sell/sale-details.js"></script>
</html>