<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="editSupplierForm" name="editSupplierForm"
	modelAttribute="editSupplierForm" method="post">
	<form:hidden path="supplierId" id ="edit_supplierId"/>
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Full Name:</label>
				<form:input type="text" id="edit_supplierName" class="form-control" placeholder="e.g John Doe" path="supplierName"/>
				<form:errors id="validation_error" path="supplierName"></form:errors>
				<span class="form-text text-muted">Please enter supplier's full name</span>
			</div>
			<div class="col-lg-6">
				<label>Email:</label>
				<form:input type="email" id="edit_supplierEmail" path="supplierEmail" class="form-control" placeholder="e.g john.doe@gmail.com"/>
				<form:errors id="validation_error" path="supplierEmail"></form:errors>
				<span class="form-text text-muted">Please enter supplier's email</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Contact:</label>
				<form:input type="text" id="edit_supplierNumber" class="form-control" path="supplierNumber" placeholder="e.g 970xxxxxxxx"/>
				<form:errors id="validation_error" path="supplierNumber"></form:errors>
				<span class="form-text text-muted">Please enter supplier's contact</span>
			</div>
			<div class="col-lg-6">
				<label>Address:</label>
				<div class="input-group">
					<form:input type="text" id="edit_supplier_address" path="supplier_address" class="form-control" placeholder="Enter your address"/>
					<div class="input-group-append"><span class="input-group-text"><i class="la la-map-marker"></i></span></div>
				</div>
				<form:errors id="validation_error" path="supplier_address"></form:errors>
				<span class="form-text text-muted">Please enter supplier's address</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Pincode:</label>
				<div class="input-group">
					<div class="input-group-append"><span class="input-group-text"><i class="la la-bookmark-o"></i></span></div>
					<form:input type="text" id="edit_supplierPincode" class="form-control" placeholder="e.g 400101" path="supplierPincode"/>
				</div>
				<form:errors id="validation_error" path="supplierPincode"></form:errors>
				<span class="form-text text-muted">Please enter supplier's pincode</span>
			</div>
			<div class="col-lg-6">
				<label>GSTN no:</label>
				<div class="input-group">
					<div class="input-group-append"><span class="input-group-text"><i class="la la-bookmark-o"></i></span></div>
					<form:input type="text" id="edit_supplierGstnNo" class="form-control" placeholder="" path="supplierGstnNo"/>
				</div>
				<span class="form-text text-muted">Please enter supplier's gst no</span>
			</div>			
		</div>
	</div>
</form:form>