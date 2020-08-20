<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<base href="../">
<!--begin::Head-->
<head>
<meta charset="utf-8" />
<title>OperateIn | Register Member</title>
<meta name="description" content="Member Registration page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
</head>
<!--end::Head-->
<!--begin::Body-->
<body>
	<h3 class="font-size-h1">Register Member</h3>
	<!--begin::Form-->
	<form:form action="register/member" method="post"
		modelAttribute="registerMemberForm" onsubmit="return(validateForm());"
		novalidate="novalidate" autocomplete="off">
		<div class="form-group row">
			<div class="col-lg-12">
				<label>Organization</label>
				<form:select path="registerOrganization" id="organization"
					class="form-control" name="organization">
				</form:select>
				<form:errors id="validation_error" path="registerOrganization"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>First Name:</label>
				<form:input path="first_name"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="text" id="first_name" placeholder="First Name"
					name="firstName" autocomplete="off"></form:input>
				<form:errors id="validation_error" path="first_name"></form:errors>
			</div>
			<div class="col-lg-6">
				<label>Last Name:</label>
				<form:input path="last_name"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="text" id="last_name" placeholder="Last Name" name="lastName"
					autocomplete="off"></form:input>
				<form:errors id="validation_error" path="last_name"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Mobile Number:</label>
				<form:input path="mobileNumber"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="text" placeholder="Mobile Number" name="mobileNumber"
					autocomplete="off" />
				<form:errors id="validation_error" path="mobileNumber"></form:errors>
			</div>
			<div class="col-lg-6">
				<label>Email Id:</label>
				<form:input path="emailId"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="email" placeholder="Email Id" name="emailId" />
				<form:errors id="validation_error" path="emailId"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-12">
				<label>Member Password:</label>
				<form:input path="memberPassword"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="password" id="memberPassword" placeholder="Password"
					name="memberPassword"></form:input>
				<form:errors id="validation_error" path="memberPassword"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Creation Date:</label>
				<div class="input-group date">
					<form:input type="text" path="created_on" class="form-control"
						readonly="readonly" id="member_created_on" />
				</div>
				<span class="form-text text-muted">Please enter member
					creation Date</span>
				<form:errors id="validation_error" path="created_on"></form:errors>
			</div>
			<div class="col-lg-6">
				<label>Expiry Date:</label>
				<div class="input-group date">
					<form:input type="text" path="expires_on" class="form-control"
						readonly="readonly" id="member_expires_on" />
				</div>
				<span class="form-text text-muted">Please enter member expiry
					Date</span>
				<form:errors id="validation_error" path="expires_on"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Member Type:</label>
				<form:select path="member_type" class="form-control select2"
					id="member_type" name="member_type">
					<form:option value="Full">Full</form:option>
					<form:option value="ReadOnly">ReadOnly</form:option>
				</form:select>
				<span class="form-text text-muted">Please select member type</span>
				<form:errors id="validation_error" path="member_type"></form:errors>
			</div>
			<div class="col-lg-6">
				<label>Member Status:</label>
				<form:select path="member_status" class="form-control select2"
					id="member_status" name="member_status">
					<form:option value="Active">Active</form:option>
					<form:option value="InActive">InActive</form:option>
				</form:select>
				<span class="form-text text-muted">Please select member
					status</span>
				<form:errors id="validation_error" path="member_status"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Member photo:</label>
				<form:input path="member_photo"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="text" placeholder="Member Photo" name="memberPhoto"
					autocomplete="off" />
				<form:errors id="validation_error" path="member_photo"></form:errors>
			</div>
			<div class="col-lg-6">
				<label>Gstn %:</label>
				<form:input path="gstn_percent"
					class="form-control form-control-solid h-auto py-5 px-6"
					type="text" placeholder="Gstn %" name="gstn_percent"
					autocomplete="off" />
				<form:errors id="validation_error" path="gstn_percent"></form:errors>
			</div>
		</div>
		<div class="form-group row d-flex flex-wrap flex-center">
			<form:button type="submit" id="kt_login_signup_submit"
				class="btn btn-primary font-weight-bold px-9 py-4 my-3 mx-4">Submit</form:button>
			<button type="button" id="kt_login_signup_cancel"
				class="btn btn-light-primary font-weight-bold px-9 py-4 my-3 mx-4">Cancel</button>
		</div>
	</form:form>
	<script src="assets/js/jquery/jquery-3.5.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/register/member.js"></script>
	<script type='text/javascript'>
		$(function() {
			$('#member_created_on').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#member_expires_on').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
		});
	</script>
</body>
<!--end::Body-->
</html>