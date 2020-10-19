<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GrowKar | Appointment Invoice</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Appointment
						Invoice Final</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Download, print or save invoice</span>
					</div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom overflow-hidden">
					<form class="form" method="post" id="appointmentInvoiceForm"
						name="appointmentInvoiceForm" autocomplete="off">
						<input type="hidden" name="invoiceId" id="invoiceId"
							value="${invoiceDetailsForm.invoice.invoiceId}"> <input
							type="hidden" name="invoiceDetailsId" id="invoiceDetailsId"
							value="${invoiceDetailsForm.invoiceDetailsId}">
						<spring:bind path="invoiceDetailsForm.cgst">
							<input type="hidden" name="cgst"
								value="${invoiceDetailsForm.cgst}">
							<input type="hidden" name="sgst"
								value="${invoiceDetailsForm.sgst}">
							<input type="hidden" name="discount"
								value="${invoiceDetailsForm.discount}">
							<input type="hidden" name="challanNo"
								value="${invoiceDetailsForm.challanNo}">
							<input type="hidden" name="challanDate"
								value="${invoiceDetailsForm.challanDate}">
						</spring:bind>
						<spring:bind path="appointmentInvoiceForm.appointmentId">
							<input type="hidden" name="appointmentId" id="appointmentId"
								value="${appointmentInvoiceForm.appointmentId}">
							<input type="hidden" name="appointmentTotal"
								id="appointmentTotal"
								value="${appointmentInvoiceForm.appointmentExpectedTotal}">
							<input type="hidden" name="appointmentNotes"
								id="appointmentNotes"
								value="${appointmentInvoiceForm.appointmentNotes}">
						</spring:bind>
						<div class="card-body p-0">
							<div id="htmlToPdf">
								<div
									class="row justify-content-center py-8 px-8 py-md-27 px-md-0">
									<div class="col-md-9">
										<div
											class="d-flex justify-content-between pb-10 pb-md-20 flex-column flex-md-row">
											<div class="d-flex flex-column flex-root">
												<span class="display-4 font-weight-boldest mb-10">${appointmentInvoiceForm.organization.organizationName}</span>
												<span class="opacity-70" style="width: 30%;">${appointmentInvoiceForm.organization.organizationAddress}
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
													class="opacity-70">${invoiceDetailsForm.invoice.invoiceNo}</span>
												<c:if test="${not empty invoiceDetailsForm.challanNo}">
													<span class="font-weight-bolder mb-2">Challan no.</span>
													<span class="opacity-70">
														${invoiceDetailsForm.challanNo} </span>
												</c:if>
												<c:if test="${not empty invoiceDetailsForm.challanDate}">
													<span class="font-weight-bolder mb-2">Challan Date.</span>
													<span class="opacity-70">
														${invoiceDetailsForm.challanDate} </span>
												</c:if>
											</div>
											<div class="d-flex flex-column flex-root">
												<span class="font-weight-bolder mb-2">Invoice to.</span> <span>
													<span class="opacity-70">Name : </span><span
													class="font-weight-bolder mb-2">${appointmentInvoiceForm.client.fullName}</span>
													<br /> <span class="opacity-70">Number : </span><span
													class="font-weight-bolder mb-2">${appointmentInvoiceForm.client.mobileNumber}</span>
													<br /> <span class="opacity-70">Email : </span><span
													class="font-weight-bolder mb-2">${appointmentInvoiceForm.client.emailId}</span>
													<br /> <span class="opacity-70">Address : </span><span
													class="font-weight-bolder mb-2">${appointmentInvoiceForm.client.client_address}</span>
													<br /> <span class="opacity-70">Pin code : </span><span
													class="font-weight-bolder mb-2">${appointmentInvoiceForm.client.clientPincode}</span>
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
														<th class="font-weight-bold text-muted">Service &
															Description</th>
														<th class="text-right font-weight-bold text-muted">Duration</th>
														<th class="text-right pr-0 font-weight-bold text-muted">Amount</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${appointmentDetailsInvoiceForm}"
														var="item" varStatus="status">
														<tr class="font-weight-boldest">
															<td class="pl-0 pt-7">${status.index + 1}</td>
															<td class="pl-0 pt-7">${item.service.serviceName}</td>
															<td class="text-right pl-0 pt-7">${item.appointmentDuration}</td>
															<td class="text-right pl-0 pt-7">${item.serviceCost}</td>
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
															<span class="font-weight-bold mr-15">4) Delivery
																Ex-Premises.</span>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="d-flex flex-column flex-md-row">
													<div class="d-flex flex-column">
														<div
															class="d-flex justify-content-between font-size-lg mb-3">
															<span class="font-weight-boldest mr-15">Sub Total</span>
															<span class="text-right font-weight-boldest">&#8360;
																${appointmentInvoiceForm.appointmentTotal}</span>
														</div>
														<div
															class="d-flex justify-content-between font-size-lg mb-3">
															<span class="font-weight-bold mr-15">CGST (%)</span> <span
																class="text-right">${cgstAmt}(${invoiceDetailsForm.cgst})</span>
														</div>
														<div class="d-flex justify-content-between font-size-lg">
															<span class="font-weight-bold mr-15">SGST (%)</span> <span
																class="text-right">${sgstAmt}(${invoiceDetailsForm.sgst})</span>
														</div>
														<div class="d-flex justify-content-between font-size-lg">
															<span class="font-weight-bold mr-15">Total Tax</span> <span
																class="text-right">${totalTax}</span>
														</div>
														<div class="d-flex justify-content-between font-size-lg">
															<span class="font-weight-bold mr-15">Discount</span> <span
																class="text-right">${discount}</span>
														</div>
														<div class="d-flex justify-content-between font-size-lg">
															<span class="font-weight-boldest mr-15">Total
																After Tax </span> <span class="text-right font-weight-boldest">&#x20a8;
																${totalAfterTax}</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div
								class="row justify-content-center py-8 px-8 py-md-10 px-md-0">
								<div class="col-md-9">
									<div class="d-flex justify-content-between">
										<button type="button"
											class="btn btn-light-primary font-weight-bold"
											onclick="submitAppointmentFinalInvoice();">Download
											Invoice</button>
										<button type="button" class="btn btn-primary font-weight-bold"
											onclick="window.print();">Print Invoice</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var HOST_URL = "${pageContext.request.contextPath}";
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
		var elementToFind = $('li.menu-item-active');
		var element = $('ul.menu-nav').find(elementToFind);
		$(element).removeClass('menu-item-active');
		$('#bill_appointment_nav').addClass('menu-item-active');
		$('#bill_nav').addClass('menu-item-open menu-item-here');
	});
</script>
<style>
@media print {
	body * {
		visibility: hidden;
	}
	#htmlToPdf * {
		visibility: visible;
	}
}
</style>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/invoice/invoice.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
</html>