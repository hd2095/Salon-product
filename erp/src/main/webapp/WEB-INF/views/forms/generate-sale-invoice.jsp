<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIN | Sale Invoice</title>
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
						Invoice Preview</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter sale invoice details and
							submit to generate final invoice</span>
					</div>
				</div>
				<!--end::Info-->
				<div class="d-flex align-items-center">
					<a class="btn btn-dark font-weight-bolder btn-sm"
						data-toggle="modal" data-target="#invoiceDetailsModal">Add
						Invoice Details</a>
					<!--end::Actions-->
				</div>
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom overflow-hidden">
					<form:form class="form" modelAttribute="saleInvoiceForm"
						method="post" id="saleInvoiceForm" name="saleInvoiceForm"
						autocomplete="off">
						<form:hidden id="saleId" path="saleId" />
						<div class="card-body p-0">
							<div
								class="row justify-content-center py-8 px-8 py-md-27 px-md-0">
								<div class="col-md-9">
									<div
										class="d-flex justify-content-between pb-10 pb-md-20 flex-column flex-md-row">
										<div class="d-flex flex-column flex-root">
											<span class="display-4 font-weight-boldest mb-10">${saleInvoiceForm.organization.organizationName}</span>
											<span class="opacity-70" style="width: 30%;">${saleInvoiceForm.organization.organizationAddress}
											</span>
										</div>
									</div>
									<div class="border-bottom w-100"></div>
									<div class="d-flex justify-content-between pt-6">
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">Invoice Date.</span> <span
												class="opacity-70">${invoiceDate}</span>
										</div>
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">Invoice no.</span> <span
												class="opacity-70">${invoiceNo}</span>
											<span class="font-weight-bolder mb-2">Challan no.</span> <span
												class="opacity-70"> - </span> <span
												class="font-weight-bolder mb-2">Challan Date.</span> <span
												class="opacity-70"> - </span>
										</div>
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">Invoice to.</span> <span>
												<span class="opacity-70">Name : </span><span
												class="font-weight-bolder mb-2">${saleInvoiceForm.client.fullName}</span>
												<br /> <span class="opacity-70">Number : </span><span
												class="font-weight-bolder mb-2">${saleInvoiceForm.client.mobileNumber}</span>
												<br /> <span class="opacity-70">Email : </span><span
												class="font-weight-bolder mb-2">${saleInvoiceForm.client.emailId}</span>
												<br /> <span class="opacity-70">Address : </span><span
												class="font-weight-bolder mb-2">${saleInvoiceForm.client.client_address}</span>
												<br /> <span class="opacity-70">Pin code : </span><span
												class="font-weight-bolder mb-2">${saleInvoiceForm.client.clientPincode}</span>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div
								class="row justify-content-center py-8 px-8 py-md-10 px-md-0">
								<div class="col-md-9">
									<div class="table-responsive">
										<table class="table table-bordered ">
											 <thead class="thead-dark">
												<tr>
													<th class="pl-0 font-weight-bold text-muted">#</th>
													<th class="font-weight-bold text-muted">Item &
														Description</th>
													<th class="text-right font-weight-bold text-muted">Qty</th>
													<th class="text-right font-weight-bold text-muted">Rate</th>
													<th class="text-right pr-0 font-weight-bold text-muted">Amount</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${saleDetailsInvoiceForm}" var="item"
													varStatus="status">
													<tr class="font-weight-boldest">
														<td class="pl-0 pt-7">${status.index + 1}</td>
														<td class="pl-0 pt-7">${item.product.productName}</td>
														<td class="text-right pl-0 pt-7">${item.quantity}</td>
														<td class="text-right pl-0 pt-7">${item.sellingPrice}</td>
														<td class="text-right pl-0 pt-7">${item.sellingPrice * item.quantity}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div
								class="row justify-content-center py-8 px-8 py-md-27 px-md-0">
								<div class="col-md-9">
									<div class="border-bottom w-100"></div>
									<br>
									<div class="row">
										<div class="col-md-8">
										<div class="d-flex flex-column flex-md-row">
												<div class="d-flex flex-column">
													<div
														class="d-flex justify-content-between font-size-lg mb-3">
														<span class="font-weight-boldest mr-15">Terms &
															Conditions</span>
													</div>
													<div
														class="d-flex justify-content-between font-size-md mb-3">
														<span class="font-weight-bold mr-15">1) Subject to
															our home jurisdiction.</span>
													</div>
													<div
														class="d-flex justify-content-between font-size-md mb-3">
														<span class="font-weight-bold mr-15">2) Our
															Responsibility Ceases as soon as goods leaves our
															premises.</span>
													</div>
													<div
														class="d-flex justify-content-between font-size-md mb-3">
														<span class="font-weight-bold mr-15">3) Goods once
															sold will not be taken back.</span>
													</div>
													<div
														class="d-flex justify-content-between font-size-md mb-3">
														<span class="font-weight-bold mr-15">4) Delivery Ex-Premises.</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="d-flex flex-column flex-md-row">
												<div class="d-flex flex-column">
													<div
														class="d-flex justify-content-between font-size-lg mb-3">
														<span class="font-weight-boldest mr-15">Sub Total</span> <span
															class="text-right font-weight-boldest">&#8360; ${saleInvoiceForm.saleTotal}</span>
													</div>
													<div
														class="d-flex justify-content-between font-size-lg mb-3">
														<span class="font-weight-bold mr-15">CGST (%)</span> <span
															class="text-right">-</span>
													</div>
													<div class="d-flex justify-content-between font-size-lg">
														<span class="font-weight-bold mr-15">SGST (%)</span> <span
															class="text-right">-</span>
													</div>
													<div class="d-flex justify-content-between font-size-lg">
														<span class="font-weight-bold mr-15">Total Tax</span> <span
															class="text-right">0.00</span>
													</div>
													<div class="d-flex justify-content-between font-size-lg">
														<span class="font-weight-bold mr-15">Discount</span> <span
															class="text-right">0.00</span>
													</div>
													<div class="d-flex justify-content-between font-size-lg">
														<span class="font-weight-boldest mr-15">Total
															After Tax </span> <span class="text-right font-weight-boldest">&#x20a8; ${saleInvoiceForm.saleTotal}</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="invoiceDetailsModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="invoiceDetailsModal">Invoice
						Details</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<form:form class="form" modelAttribute="invoiceDetailsForm"
						action="sell/generateSaleInvoice" method="post"
						id="invoiceDetailsForm" name="invoiceDetailsForm"
						autocomplete="off">
						<input type="hidden" name="invoiceNo" value="${invoiceNo}">
						<input type="hidden" name="clientId" value="${saleInvoiceForm.client.clientId}">						
						<div class="form-group row">
							<div class="col-lg-6">
								<label>CGST(%):</label>
								<form:input type="text" class="form-control" path="cgst"
									id="cgst" placeholder="e.g 4" />
							</div>
							<div class="col-lg-6">
								<label>SGST(%):</label>
								<form:input type="text" class="form-control" path="sgst"
									id="sgst" placeholder="e.g. 4" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-6">
								<label>Discount(%):</label>
								<form:input type="text" class="form-control" path="discount"
									id="discount" placeholder="e.g 10" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-6">
								<label>Challan No:</label>
								<form:input type="text" class="form-control" path="challanNo"
									id="challanNo" placeholder="e.g 335565" />
							</div>
							<div class="col-lg-6">
								<label>Challan Date:</label>
								<div class="input-group date">
									<form:input type="text" class="form-control" path="challanDate"
										readonly="readonly" id="challanDate" />
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="la la-calendar"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" onclick="submitForm()"
						class="btn btn-primary mr-2">Generate Invoice</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
</body>
<script>
	var HOST_URL = "${pageContext.request.contextPath}";
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
		var elementToFind = $('li.menu-item-active');
		var element = $('ul.menu-nav').find(elementToFind);
		$(element).removeClass('menu-item-active');
		$('#bill_sale_nav').addClass('menu-item-active');
		$('#bill_nav').addClass('menu-item-open menu-item-here');
	});
</script>
<script src="assets/js/utilities/push-divs.js"></script>
<script src="assets/js/pages/invoice/invoice.js"></script>
<script src="assets/js/utilities/datePicker.js"></script>
</html>