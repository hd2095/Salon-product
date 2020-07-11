<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="editProductForm" name="editProductForm"
	modelAttribute="editProductForm" method="post">
	<form:hidden id="edit_productId" path="productId"/>
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product Name:</label>
				<form:input type="text" class="form-control" path="productName" id="edit_productName"
					placeholder="e.g. Large Shampoo" />
				<form:errors id="validation_error" path="productName"></form:errors>
				<span class="form-text text-muted">Please enter product name</span>
			</div>
			<div class="col-lg-6">
				<label>Brand Name:</label>
				<form:input type="text" class="form-control" path="productBrand" id="edit_productBrand"
					placeholder="e.g. L'Oreal" />
				<form:errors id="validation_error" path="productBrand"></form:errors>
				<span class="form-text text-muted">Please enter brand name</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Barcode:</label>
				<div class="input-group">
					<form:input type="text" class="form-control" id="edit_productBarcode"
						placeholder="e.g. 123ABC" path="productBarcode" />
					<div class="input-group-append">
						<span class="input-group-text"><i class="la la-map-marker"></i></span>
					</div>
				</div>
				<span class="form-text text-muted">Please enter product
					barcode</span>
			</div>
		</div>
	</div>
</form:form>