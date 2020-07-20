<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="editOrderForm" name="editOrderForm"
	modelAttribute="editOrderForm" method="post" autocomplete="off">
	<form:hidden path="orderId" id="edit_OrderId" />
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product:</label>
				<form:select onchange="populateEditBrandField(this.value)"
					class="form-control select2" id="edit_order_product_dropdown"
					path="product.productName" name="param">
				</form:select>
				<form:errors id="validation_error" path="product.productName"></form:errors>
				<span class="form-text text-muted">Please select product </span>
			</div>
			<div class="col-lg-6">
				<label>Brand:</label>
				<form:input type="text" class="form-control" id="edit_order_brand"
					path="product.productBrand" name="brand" disabled="true" />
				<form:errors id="validation_error" path="product.productBrand"></form:errors>
				<span class="form-text text-muted">Please select product
					brand</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Supplier:</label>
				<form:select class="form-control select2" id="edit_supplier_select"
					path="supplier.supplierId" name="param">
				</form:select>
				<form:errors id="validation_error" path="supplier.supplierId"></form:errors>
				<span class="form-text text-muted">Please select Supplier</span>
			</div>
			<div class="col-lg-6">
				<label>Order Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" path="orderDate"
						readonly="readonly" id="edit_order_date" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"> </span>
					</span>
				</div>
				<form:errors id="validation_error" path="orderDate"></form:errors>
				<span id="edit_order_date_span" class="form-text text-muted">Please enter order Date</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Cost Price:</label>
				<form:input type="text" class="form-control" id="edit_cost_price"
					path="costPrice" placeholder="" />
				<form:errors id="validation_error" path="costPrice"></form:errors>
				<span id="edit_cost_price_span" class="form-text text-muted">Please enter product Cost
					Price</span>
			</div>
			<div class="col-lg-6">
				<label>Quantity:</label>
				<form:input type="text" id="edit_order_quantity"
					class="form-control" path="quantity" placeholder="e.g 100" />
				<form:errors id="validation_error" path="quantity"></form:errors>
				<span id="edit_order_quantity_span" class="form-text text-muted">Please enter product
					quantity</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Status:</label>
				<form:select class="form-control select2" id="edit_order_status"
					path="orderDeliveryStatus" name="orderDeliveryStatus">
					<option value="Select">Select an option</option>
					<option value="Received">Received</option>
					<option value="Delivered">Delivered</option>
					<option value="Cancelled">Cancelled</option>
				</form:select>
				<form:errors id="validation_error" path="orderDeliveryStatus"></form:errors>
				<span class="form-text text-muted">Please select order status
				</span>
			</div>
			<div class="col-lg-6">
				<label>Order Received Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control"
						path="orderReceivedDate" readonly="readonly"
						id="edit_order_receive_date" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"> </span>
					</span>
				</div>
				<form:errors id="validation_error" path="orderReceivedDate"></form:errors>
				<span class="form-text text-muted">Please enter order Date</span>
			</div>
		</div>
	</div>
</form:form>
<script type='text/javascript'>
	$(function() {
		$('#edit_order_date').datepicker({
			todayHighlight : true,
			autoclose : true,
			orientation : 'top auto',
			clearBtn : true
		});
		$('#edit_order_receive_date').datepicker({
			todayHighlight : true,
			autoclose : true,
			orientation : 'bottom auto',
			clearBtn : true
		});
	});
</script>