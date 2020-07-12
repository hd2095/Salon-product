<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="editClientForm" name="editClientForm" modelAttribute="editClientForm" method="post">
	<div class="card-body">
		<div class="form-group row">
		<form:hidden id="edit_clientId" path="clientId"/>
			<div class="col-lg-6">
				<label>Full Name:</label>
				<form:input type="text" id="edit_fullName" path="fullName" class="form-control"
					placeholder="Enter full name" />
				<form:errors id="validation_error" path="fullName"></form:errors>
				<span class="form-text text-muted span-info">Please enter client's full
					name</span>
			</div>
			<div class="col-lg-6">
				<label>Contact Number:</label>
				<form:input type="text" id="edit_mobileNumber" path="mobileNumber" class="form-control"
					placeholder="Enter contact number" />
				<form:errors id="validation_error" path="mobileNumber"></form:errors>
				<span class="form-text text-muted span-info">Please enter client's
					contact number</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Address:</label>
				<div class="input-group">
					<form:input type="text" id="edit_address" class="form-control" path="client_address"
						placeholder="Enter client's address" />
					<div class="input-group-append">
						<span class="input-group-text"><i class="la la-map-marker"></i></span>
					</div>
				</div>
				<form:errors id="validation_error" path="client_address"></form:errors>
				<span class="form-text text-muted span-info">Please enter client's
					address</span>
			</div>
			<div class="col-lg-6">
				<label>Pincode:</label>
				<div class="input-group">
					<form:input type="text" id="edit_clientPincode" class="form-control" path="clientPincode"
						placeholder="Enter client's pincode" />
					<div class="input-group-append">
						<span class="input-group-text"><i class="la la-bookmark-o"></i></span>
					</div>
				</div>
				<form:errors id="validation_error" path="clientPincode"></form:errors>
				<span class="form-text text-muted span-info">Please enter client's
					pincode</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Gender:</label>
				<div class="radio-inline">
					<label class="radio radio-solid"> <form:radiobutton
							path="gender" id="edit_male" name="example_2" checked="checked" value="Male" />Male
						<span></span>
					</label> <label class="radio radio-solid"> <form:radiobutton
							path="gender" id="edit_female" name="example_2" value="Female" /> Female <span></span>
					</label>
				</div>
				<span class="form-text text-muted">Please select client's
					gender</span>
			</div>
			<div class="col-lg-6">
				<label>Email Id:</label>
				<form:input type="text" path="emailId" class="form-control" id="edit_emailId"
					placeholder="Enter email id" />
				<form:errors id="validation_error" path="emailId"></form:errors>
				<span class="form-text text-muted span-info">Please enter client's
					Email id</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Birthday:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="birthday" id="edit_client_birthday" />
					<div class="input-group-append">
						<span class="input-group-text"> <i class="la la-calendar"></i>
						</span>
					</div>
				</div>
				<form:errors id="validation_error" path="birthday"></form:errors>
				<span class="form-text text-muted span-info">Please select client's
					birthday</span>
			</div>
			<div class="col-lg-6">
				<label>Is member:</label>
				<div class="input-group">
					<span class="switch"> <label> <form:checkbox onclick="isEditMemberValue()"
								path="isMember" id="edit_isMember" name="isMember" value="1"/> <span></span>
					</label>
					</span>
				</div>
			</div>
		</div>
		<div class="form-group row edit_client-duration-details" style="display:none;">
			<div class="col-lg-6">
				<label>Client Start Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="client_start_date" id="edit_client_start_date" />
					<div class="input-group-append">
						<span class="input-group-text"> <i class="la la-calendar"></i>
						</span>
					</div>
				</div>
				<span class="form-text text-muted span-info">Please select client's
					start date</span>
			</div>
			<div class="col-lg-6">
				<label>Client End Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="client_end_date" id="edit_client_end_date" />
					<div class="input-group-append">
						<span class="input-group-text"> <i class="la la-calendar"></i>
						</span>
					</div>
				</div>
				<span class="form-text text-muted span-info">Please select client's
					end date</span>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
function isEditMemberValue(){
	if($('input[name="isMember"]:checked').val()){
		$('.edit_client-duration-details').show();
	}else{
		$('.edit_client-duration-details').hide();
	}
}
</script>