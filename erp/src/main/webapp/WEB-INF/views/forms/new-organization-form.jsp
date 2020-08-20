<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<base href="../">
<head>
<meta charset="utf-8" />
<title>OperateIn | Register Organization</title>
<meta name="description" content="Organization Registration page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
</head>

<body>
	<h3 class="font-size-h1">Register Organization</h3>

	<form:form action="register/organization" method="post"
		modelAttribute="registerOrganizationForm"
		onsubmit="return(validateForm());" novalidate="novalidate">
		<div class="form-group">
			<form:input path="organization_name"
				class="form-control form-control-solid h-auto py-5 px-6" type="text"
				id="organization_name" placeholder="Organization Name"
				name="organizationName" autocomplete="off"></form:input>
			<form:errors id="validation_error" path="organization_name"></form:errors>
		</div>
		<div class="form-group">
			<form:input path="orgnization_address"
				class="form-control form-control-solid h-auto py-5 px-6" type="text"
				placeholder="Organization Address" name="organizationAddress"
				autocomplete="off" />
			<form:errors id="validation_error" path="orgnization_address"></form:errors>
		</div>
		<div class="form-group">
			<form:input path="gstn_no"
				class="form-control form-control-solid h-auto py-5 px-6" type="text"
				placeholder="GSTN No" name="gstnNo" autocomplete="off" />
			<form:errors id="validation_error" path="gstn_no"></form:errors>
		</div>
		<div class="form-group">
			<form:select path="plan" class="form-control" name="plan" id="plan">
			</form:select>
			<form:errors id="validation_error" path="plan"></form:errors>
		</div>
		<div class="form-group d-flex flex-wrap flex-center">
			<form:button type="submit"
				class="btn btn-primary font-weight-bold px-9 py-4 my-3 mx-4">Submit</form:button>
			<button type="button"
				class="btn btn-light-primary font-weight-bold px-9 py-4 my-3 mx-4">Cancel</button>
		</div>
	</form:form>
	<script src="assets/js/jquery/jquery-3.5.1.min.js"></script>
	<script>var HOST_URL = "${pageContext.request.contextPath}"</script>
	<script src="assets/js/pages/register/organization.js"></script>
</body>
</html>