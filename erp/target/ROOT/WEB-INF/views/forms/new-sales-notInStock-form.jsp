<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" modelAttribute="salesNotInStockForm" method="post"
	id="salesNotInStockForm" name="salesNotInStockForm">
	<input type="hidden" id="sales_CostPrice" name="sales_CostPrice"></input>
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product Name:</label>
				<form:input type="text" class="form-control" id="sales_product_name"
					path="productName" />
				<span class="form-text text-muted">Please enter product name</span>
			</div>
			<div class="col-lg-6">
				<label>Quantity:</label>
				<form:input type="text" class="form-control" path="quantity"
					placeholder="e.g. 10" />
				<span class="form-text text-muted">Please enter sales
					quantity</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Cost Price:</label>
				<form:input type="text" class="form-control" path="costPrice"
					id="costPrice" />
				<span class="form-text text-muted">Please enter sale cost
					price</span>
			</div>
			<div class="col-lg-6">
				<label>Selling Price:</label>
				<form:input type="text" class="form-control" path="sellingPrice"
					placeholder="e.g. 100" />
				<span class="form-text text-muted">Please enter sale selling
					price</span>
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
				<label>Supplier:</label>
				<form:select id="sales_supplier" path="supplier"
					class="form-control select2" name="param">
				</form:select>
				<span class="form-text text-muted">Please select supplier</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Sales Description:</label>
				<form:textarea type="text" class="form-control" path="saleNotes"
					placeholder="This sale was done with pleasure" />
				<span class="form-text text-muted">Please enter sales
					description</span>
			</div>
		</div>
	</div>
</form:form>