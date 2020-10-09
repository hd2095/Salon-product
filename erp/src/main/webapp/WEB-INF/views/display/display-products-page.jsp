<html>
<head>
<base href="../">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
<title>Grokar | Products</title>
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Products</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<div class="d-flex align-items-center">
					<a class="btn btn-dark font-weight-bolder btn-sm"
						onclick="clearNewProductForm();" data-toggle="modal"
						data-target="#newProductModal">Add New Product</a>
					<!--end::Actions-->
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<div class="card-body">
						<!--begin: Datatable-->
						<table class="table table-bordered table-hover table-checkable"
							style="margin-top: 13px !important" id="product_dataTable">
							<thead>
								<tr>
									<th>Product Name</th>
									<th>Brand Name</th>
									<th>Product Barcode</th>
									<th>Actions</th>
								</tr>
							</thead>
						</table>
						<!--end: Datatable-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end::Content-->
	<!-- Modal-->
	<div class="modal fade" id="newProductModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="newProductModalLabel">New Product</h3>
					<button type="button" class="close" data-dismiss="modal"
						onclick="clearNewProductForm();" aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/new-product-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						onclick="clearNewProductForm();" data-dismiss="modal">Close</button>
					<button type="button" id="createProductBtn"
						class="btn btn-primary mr-2">Create product</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<!-- Modal-->
	<div class="modal fade" id="editProductModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="editProductModalLabel">Edit
						Product</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="../forms/edit-product-form.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" id="editProductBtn"
						class="btn btn-primary mr-2">Edit product</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<script type='text/javascript'>
		var HOST_URL = "${pageContext.request.contextPath}";
		jQuery(document).ready(function() {
			$('#loading-spinner').hide();
			var invalidProduct = '${productExists}';
			if (invalidProduct.length > 2) {
				/* $('#alreadyExistsMessage').html(invalidProduct);
				$('#alreadyExists').show();
				$('#alreadyExists').delay(2000).fadeOut(500); */
				if (invalidProduct.length > 2) {
					$.notify({
						// options
						message : invalidProduct
					}, {
						// settings
						type : 'danger',
						delay : 5000
					});
					$('#newProductModal').modal();
				}
			}
			invalidProduct = '${editProductExists}';
			if (invalidProduct.length > 2) {
				$.notify({
					// options
					message : invalidProduct
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
				editProduct('${editProductId}');
			}
		});
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/inventory/product.js" />"></script>
</body>
</html>