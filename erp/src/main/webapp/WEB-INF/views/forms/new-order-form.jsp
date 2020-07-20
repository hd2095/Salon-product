<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="orderForm" name="orderForm"
	modelAttribute="orderForm" method="post" autocomplete="off">
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Product:</label>
				<form:select onchange="populateBrandField(this.value)"
					class="form-control select2" id="order_product_dropdown"
					path="product.productName" name="param">
				</form:select>
				<form:errors id="validation_error" path="product.productName"></form:errors>
				<span class="form-text text-muted">Please select product </span>
			</div>
			<div class="col-lg-6">
				<label>Brand:</label>
				<form:input type="text" class="form-control" id="order_brand"
					path="product.productBrand" name="brand" disabled="true" />
				<form:errors id="validation_error" path="product.productBrand"></form:errors>
				<span class="form-text text-muted">Please select product
					brand</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Supplier:</label>
				<form:select class="form-control select2" id="supplier_select"
					path="supplier.supplierId" name="param">
				</form:select>
				<form:errors id="validation_error" path="supplier.supplierId"></form:errors>
				<span class="form-text text-muted">Please select Supplier</span>
			</div>
			<div class="col-lg-6">
				<label>Order Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" path="orderDate"
						readonly="readonly" id="order_date" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"> </span>
					</span>
				</div>
				<form:errors id="validation_error" path="orderDate"></form:errors>
				<span id="order_date_span" class="form-text text-muted">Please enter order Date</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Cost Price:</label>				
					<form:input type="text" class="form-control" path="costPrice"
						id="costPrice" placeholder="" />				
				<form:errors id="validation_error" path="costPrice"></form:errors>
				<span id="costPrice_span" class="form-text text-muted">Please enter product Cost
					Price</span>
			</div>
			<div class="col-lg-6">
				<label>Quantity:</label>			
					<form:input type="text" class="form-control" path="quantity" id="quantity"
						placeholder="e.g 100" />				
				<form:errors id="validation_error" path="quantity"></form:errors>
				<span id="quantity_span" class="form-text text-muted">Please enter product
					quantity</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Status:</label>
				<form:select class="form-control" id="order_status"
					path="orderStatus" name="param" disabled="true">
					<option value="Booked" selected>Booked</option>
					<option value="Receieved">Received</option>
					<option value="Delivered">Delivered</option>
					<option value="Cancelled">Cancelled</option>
				</form:select>
				<form:errors id="validation_error" path="orderStatus"></form:errors>
				<span class="form-text text-muted">Please select order status
				</span>
			</div>
		</div>
	</div>
</form:form>
<script type='text/javascript'>
		$(function() {
			$('#order_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
		});
	</script>