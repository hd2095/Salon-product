<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIn | Sale Invoice</title>
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
						Invoice</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter sale invoice details and
							submit</span>
					</div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<form:form class="form" modelAttribute="saleInvoiceForm"
						method="post" id="saleInvoiceForm" name="saleInvoiceForm"
						autocomplete="off">
						<div class="card-body">
							<div
								class="row justify-content-center py-8 px-8 py-md-27 px-md-0">
								<div class="col-md-9">
									<div
										class="d-flex justify-content-between pb-10 pb-md-20 flex-column flex-md-row">
										<div class="d-flex flex-column flex-root">
											<span class="display-4 font-weight-boldest mb-10">${saleInvoiceForm.organization.organizationName}</span> <span
												class="opacity-70">${saleInvoiceForm.organization.organizationAddress} </span>
										</div>									
										<div class="d-flex flex-column align-items-md-end px-0">
											<!--begin::Logo-->
											<a href="#" class="mb-5"> <img
												src="assets/media/logos/logo-dark.png" alt="" />
											</a>
											<!--end::Logo-->
											<span
												class="d-flex flex-column align-items-md-end opacity-70">
												${saleInvoiceForm.organization.organizationAddress} </span>
										</div>
									</div>
									<div class="border-bottom w-100"></div>
									<div class="d-flex justify-content-between pt-6">
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">DATA</span> <span
												class="opacity-70">${saleInvoiceForm.saleDate}</span>
										</div>
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">INVOICE NO.</span> <span
												class="opacity-70">GS 000014</span>
										</div>
										<div class="d-flex flex-column flex-root">
											<span class="font-weight-bolder mb-2">INVOICE TO.</span> <span
												class="opacity-70">Iris Watson, P.O. Box 283 8562
												Fusce RD. <br />Fredrick Nebraska 20620
											</span>
										</div>
									</div>
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
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="assets/js/utilities/push-divs.js"></script>
</html>