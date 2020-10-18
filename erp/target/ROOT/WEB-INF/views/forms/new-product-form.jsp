<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="productForm" name="productForm"
	modelAttribute="productForm" method="post">
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product Name:</label>
				<form:input type="text" class="form-control" path="productName" id="productName"
					placeholder="e.g. Large Shampoo" />
				<form:errors id="validation_error" path="productName"></form:errors>
				<span id="productName_span" class="form-text text-muted">Please enter product name</span>
			</div>
			<div class="col-lg-6">
				<label>Brand Name:</label>
				<form:input type="text" class="form-control" path="productBrand" id="productBrand"
					placeholder="e.g. L'Oreal" />
				<form:errors id="validation_error" path="productBrand"></form:errors>
				<span id="productBrand_span"class="form-text text-muted">Please enter brand name</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Barcode:</label>
				<form:input type="text" class="form-control" id="productBarcode"
					placeholder="e.g. 123ABC" path="productBarcode" />
				<span class="form-text text-muted">Please enter product
					barcode</span>
			</div>
		</div>
	</div>
</form:form>