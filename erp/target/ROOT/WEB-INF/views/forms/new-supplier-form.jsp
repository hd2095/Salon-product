<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="supplierForm" name="supplierForm"
	modelAttribute="supplierForm" method="post">
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Full Name:</label>
				<form:input type="text" class="form-control" id="supplierFullName"
					placeholder="e.g John Doe" path="supplierName" />
				<form:errors id="validation_error" path="supplierName"></form:errors>
				<span id="supplierFullName_span" class="form-text text-muted">Please enter supplier's
					full name</span>
			</div>
			<div class="col-lg-6">
				<label>Email:</label>
				<form:input type="email" id="supplierEmail" path="supplierEmail" class="form-control"
					placeholder="e.g john.doe@gmail.com" />
				<form:errors id="validation_error" path="supplierEmail"></form:errors>
				<span id="supplierEmail_span" class="form-text text-muted">Please enter supplier's
					email</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Contact:</label>
				<form:input type="email" class="form-control" id="supplierNumber" path="supplierNumber"
					placeholder="e.g 970xxxxxxxx" />
				<form:errors id="validation_error" path="supplierNumber"></form:errors>
				<span id="supplierNumber_span" class="form-text text-muted">Please enter supplier's
					contact</span>
			</div>
			<div class="col-lg-6">
				<label>Address:</label>
				<form:input type="text" path="supplier_address" class="form-control" id="supplier_address"
					placeholder="Enter your address" />
				<form:errors id="validation_error" path="supplier_address"></form:errors>
				<span id="supplier_address_span" class="form-text text-muted">Please enter supplier's
					address</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Pincode:</label>
				<form:input type="text" class="form-control"
					placeholder="e.g 400101" id="supplierPincode" path="supplierPincode" />
				<form:errors id="validation_error" path="supplierPincode"></form:errors>
				<span id="supplierPincode_span" class="form-text text-muted">Please enter supplier's
					pincode</span>
			</div>
			<div class="col-lg-6">
				<label>GSTN no:</label>
					<form:input type="text" id="supplierGstnNo" class="form-control" placeholder=""
						path="supplierGstnNo" />
				<span class="form-text text-muted">Please enter supplier's
					gst no</span>
			</div>
		</div>
	</div>
</form:form>