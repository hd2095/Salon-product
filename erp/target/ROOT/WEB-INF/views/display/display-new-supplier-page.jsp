<html>
<head>
<base href="../">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
<title>OperateIN | Supplier</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Suppliers</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<c:if test="${showAddBtn}">
					<div class="d-flex align-items-center">
						<a class="btn btn-dark font-weight-bolder btn-sm"
							data-toggle="modal" data-target="#newSupplierModal">Add New
							Supplier</a>
						<!--end::Actions-->
					</div>
				</c:if>
				<!--end::Info-->
			</div>
		</div>
		<!--end::Actions-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<div class="card-body">
						<!--begin: Datatable-->
						<table class="table table-bordered table-hover table-checkable"
							style="margin-top: 13px !important" id="supplier_dataTable">
							<thead>
								<tr>
									<th>Supplier Name</th>
									<th>Supplier Contact</th>
									<th>Supplier Email</th>
									<th>GSTN No</th>
									<th>Actions</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="newSupplierModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 id="newSupplierModalLabel">New Supplier</h3>
					<button type="button" class="close" data-dismiss="modal"
						onclick="clearNewSupplierForm();" aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-supplier-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						onclick="clearNewSupplierForm();" data-dismiss="modal">Close</button>
					<button type="button" id="createSupplierBtn"
						class="btn btn-primary mr-2">Create supplier</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<!-- Modal-->
	<div class="modal fade" id="editSupplierModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 id="editSupplierModalLabel">Edit Supplier</h3>
					<button type="button" class="close" data-dismiss="modal"
						onclick="clearEditSupplierForm();" aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/edit-supplier-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						onclick="clearEditSupplierForm();" data-dismiss="modal">Close</button>
					<button type="button" id="editSupplierBtn"
						class="btn btn-primary mr-2">Edit supplier</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<script type='text/javascript'>
		jQuery(document).ready(function() {
			$('#loading-spinner').hide();
			var supplierExists = '${supplierExists}';
			if (supplierExists.length > 2) {
				$.notify({
					// options
					message : supplierExists
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
				$('#newSupplierModal').modal();
			}
			supplierExists = '${editSupplierExists}';
			if (supplierExists.length > 2) {
				$.notify({
					// options
					message : supplierExists
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
				editSupplier('${editSupplierId}');
			}
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/buy/supplier.js" />"></script>
</body>
</html>