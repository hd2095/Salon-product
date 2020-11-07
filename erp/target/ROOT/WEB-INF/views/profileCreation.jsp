<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>OperateIN | Profile</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
</head>
<body>
	<!--begin::Content-->
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<!--begin::Subheader-->
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<a href="profileCreation"
						class="btn btn-light-success font-weight-bolder btn-sm mr-2">Profile</a>
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<a href="membership"
						class="btn btn-light-primary font-weight-bolder btn-sm mr-2">Membership</a>
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<a href="organization"
						class="btn btn-light-info font-weight-bolder btn-sm mr-2">Organization</a>
				</div>
				<!--end::Info-->				
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<form:form modelAttribute="profileForm" class="form"
						name="profileForm" action="updateProfile" method="post"
						id="profileForm" autocomplete="off">
						<div class="card-body">
							<form:hidden path="memberPassword"></form:hidden>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">First Name:</label>
									<form:input type="text" path="first_name" id="first_name"
										class="form-control" />
									<form:errors id="validation_error" path="first_name"></form:errors>
									<span id="first_name_span" class="form-text text-muted">Please
										enter First Name</span>
								</div>
								<div class="col-lg-6">
									<label class="col-form-label">Last Name</label>
									<form:input type="text" path="last_name" id="last_name"
										class="form-control" />
									<form:errors id="validation_error" path="last_name"></form:errors>
									<span id="last_name_span" class="form-text text-muted">Please
										enter Last Name</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">Email Id:</label>
									<form:input type="email" path="emailId" id="emailId"
										style="cursor: not-allowed;" class="form-control"
										readonly="true" />
									<form:errors id="validation_error" path="emailId"></form:errors>
									<span id="emailId_span" class="form-text text-muted">Please
										enter Email Id </span>
								</div>
								<div class="col-lg-6">
									<label class="col-form-label">Mobile number:</label>
									<form:input type="text" path="mobileNumber" id="mobileNumber"
										style="cursor: not-allowed;" class="form-control"
										readonly="true" />
									<form:errors id="validation_error" path="mobileNumber"></form:errors>
									<span id="mobileNumber_span" class="form-text text-muted">Please
										enter mobile number</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">Expires on:</label>
									<form:input type="text" path="expires_on" id="expires_on"
										style="cursor: not-allowed;" class="form-control"
										readonly="true" />
									<form:errors id="validation_error" path="expires_on"></form:errors>
									<span class="form-text text-muted"> Member Expiry Date </span>
								</div>
								<div class="col-lg-6">
									<label>Change password:</label>
									<div class="col-3">
										<span class="switch"> <label> <input
												type="checkbox" onclick="showPasswordField()"
												id="passwordCheckbox" name="passwordCheckbox" /> <span></span>
										</label>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group row passwordField" style="display: none;">
								<div class="col-lg-6">
									<label class="col-form-label">Password:</label> <input
										type="password" id="password" class="form-control" /> <span
										class="form-text text-muted">Please enter password </span>
								</div>
								<div class="col-lg-6">
									<label class="col-form-label">Confirm Password:</label> <input
										type="password" name="confirmPassword" id="confirmPassword"
										class="form-control" /> <span id="confirmPassword_span"
										class="form-text text-muted">Please Confirm password </span>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<button type="button"
										class="btn font-weight-bold btn-primary btn-shadow mr-2"
										onclick="submitForm()">Submit</button>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script>
		jQuery(document).ready(function() {
			$('#loading-spinner').hide();
			var data = '${successFullyUpdated}';
			if (data.length > 1) {
				$.notify({
					// options
					message : 'Profile Updated Successfully..'
				}, {
					// settings
					type : 'success',
					delay : 5000
				});
			}
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/profile/profile.js" />"></script>
</body>
</html>