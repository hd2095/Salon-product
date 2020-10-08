<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grokar | Edit Sale</title>
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
						Sale</h5>
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
					<form:form class="form" modelAttribute="editSalesForm"
						method="post" id="editSalesForm" name="editSalesForm"
						autocomplete="off">
						<form:hidden id="edit_saleId" path="saleId" />
						<form:hidden id="edit_saleInvoiceGenerated"
							path="saleInvoiceGenerated" />
						<div class="card-body">
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Sale Date:</label>
									<div class="input-group date">
										<form:input type="text" class="form-control" path="saleDate"
											readonly="readonly" id="edit_sale_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="saleDate"></form:errors>
									<span id="edit_sale_date_span" class="form-text text-muted">Please
										enter sale Date</span>
								</div>
								<div class="col-lg-6">
									<label>Client:</label>
									<form:select path="client" id="edit_sale_client"
										class="form-control" name="param">
									</form:select>
									<span class="form-text text-muted">Please select client</span>
								</div>
							</div>
							<input type="hidden" name="edit_sale_total_elements" value="0" />
							<div id="edit_sale_repeater">
								<div data-repeater-list="" class="col-lg-10">
									<div data-repeater-item class="form-group">
										<input type="hidden" name="elementCount" /> <input
											type="hidden" name="edit_sale_record_id" />
										<div class="card">
											<div class="card-body">
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Product: </label> <select class="form-control"
															name="edit_sale_product"></select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-5">
														<label>Selling Price:</label> <input
															class="form-control form-control-lg form-control-solid"
															name="edit_product_selling_price"></input>
													</div>
													<div class="col-lg-5">
														<label>Quantity:</label> <input
															onkeyup="calculateSaleTotalForEdit(this.value,this.name);"
															class="form-control form-control-lg form-control-solid"
															name="edit_product_quantity"></input>
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
								<div data-repeater-create="" id="addProductBtnSale"
									class="btn btn-sm font-weight-bolder btn-light-primary">
									<i class="la la-plus"></i> Add Product
								</div>
							</div>
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Total Sale Cost:</label>
									<form:input type="text" path="saleTotal" id="edit_sale_cost"
										class="form-control form-control-lg form-control-solid"
										readonly="true" />
									<span class="form-text text-muted span-info">Total sale
										cost</span>
								</div>
								<div class="col-lg-6">
									<label>Sale Notes:</label>
									<form:textarea id="edit_sale_notes" path="saleNotes"
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
									<button type="reset" id="editSaleBtn"
										class="btn font-weight-bold btn-primary btn-shadow mr-2">Edit sale</button>
									<a href = "sell/sales"
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
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/form-repeater.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/select2.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/sell/edit-sale-details.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var selectedClientId = '${editSalesForm.client.clientId}';
		fetchClients(selectedClientId);
		var saleId = '${editSalesForm.saleId}';
		fetchSaleDetails(saleId);
	});
</script>
</html>