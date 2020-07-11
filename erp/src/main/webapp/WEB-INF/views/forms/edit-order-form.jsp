<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="editOrderForm" name="editOrderForm"
	modelAttribute="editOrderForm" method="post">
	<form:hidden path="orderId" id="edit_OrderId"/>
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product:</label> <form:select onchange="populateEditBrandField(this.value)" class="form-control select2"
					id="edit_order_product_dropdown" path="product.productName" name="param">
				</form:select> 
				<form:errors id="validation_error" path="product.productName"></form:errors>
				<span class="form-text text-muted">Please select product </span>
			</div>
			<div class="col-lg-6">
				<label>Brand:</label> <form:input type="text" class="form-control"
					id="edit_order_brand" path="product.productBrand" name="brand" disabled="true"/>
				<form:errors id="validation_error" path="product.productBrand"></form:errors>
				 <span class="form-text text-muted">Please select product
					brand</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Supplier:</label> <form:select class="form-control select2"
					id="edit_supplier_select" path="supplier.supplierId" name="param">					
				</form:select>
				<form:errors id="validation_error" path="supplier.supplierId"></form:errors>
				<span class="form-text text-muted">Please select Supplier</span>
			</div>
			<div class="col-lg-6">
				<label>Order Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" path="orderDate" readonly = "readonly"
						id="edit_order_date" />
					<div class="input-group-append">
						<span class="input-group-text"> <i class="la la-calendar"></i>
						</span>
					</div>
				</div>
				<form:errors id="validation_error" path="orderDate"></form:errors>
				<span class="form-text text-muted">Please enter order
					Date</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Cost Price:</label>
				<div class="input-group">
					<form:input type="text" class="form-control" id = "edit_cost_price"
						path="costPrice" placeholder="" />					
				</div>
				<form:errors id="validation_error" path="costPrice"></form:errors>
				<span class="form-text text-muted">Please enter product Cost
					Price</span>
			</div>
			<div class="col-lg-6">
				<label>Quantity:</label>
				<div class="input-group">
					<form:input type="text" id="edit_order_quantity" class="form-control" path="quantity" placeholder="e.g 100" />					
				</div>
				<form:errors id="validation_error" path="quantity"></form:errors>
				<span class="form-text text-muted">Please enter product
					quantity</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Status:</label> <form:select class="form-control select2"
					id="edit_order_status" path="orderStatus" name="param">					
					<option value="Received">Received</option>
					<option value="Delivered">Delivered</option>
					<option value="Cancelled">Cancelled</option>
				</form:select>
				<form:errors id="validation_error" path="orderStatus"></form:errors>
				<span class="form-text text-muted">Please select order
					status </span>
			</div>
		</div>
	</div>
</form:form>
<script src="http://localhost:8081/assets/js/pages/select.js"></script>