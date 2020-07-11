<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" modelAttribute="salesForm" method="post"
	id="salesForm" name="salesForm">
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product Name:</label>
				<form:input type="text" class="form-control"
					path="product.productName" placeholder="e.g. Large Shampoo" />
				<span class="form-text text-muted">Please enter product name</span>
			</div>
			<label>Stock Id:</label>
			<div class="col-lg-6">
				<form:select path="stock" class="form-control" id="sales_stock_id"
					name="param">
				</form:select>
				<span class="form-text text-muted">Please select stock id</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Cost Price:</label>
				<form:input type="text" class="form-control" path="costPrice"
					placeholder="e.g. 100" />
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
				<label>Quantity:</label>
				<form:input type="text" class="form-control" path="quantity"
					placeholder="e.g. 10" />
				<span class="form-text text-muted">Please enter sales
					quantity</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Sales Description:</label>
				<form:textarea type="text" class="form-control" path="saleNotes"
					placeholder="e.g. 10" />
				<span class="form-text text-muted">Please enter sales
					description</span>
			</div>
		</div>
	</div>
</form:form>