<html>
<head>
<jsp:include page="layout/side-nav.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
</head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="panel panel-headline">
					<div class="panel-heading">
						<h3 class="panel-title">Profile</h3>
						<div class="panel-body">
							<form:form modelAttribute="profileForm" class="form"
								name="profileForm" action="updateProfile" method="post"
								id="profileForm" autocomplete="off">
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
											class="form-control" />
										<form:errors id="validation_error" path="emailId"></form:errors>
										<span id="emailId_span" class="form-text text-muted">Please
											enter Email Id </span>
									</div>
									<div class="col-lg-6">
										<label class="col-form-label">Mobile number:</label>
										<form:input type="text" path="mobileNumber" id="mobileNumber"
											class="form-control" />
										<form:errors id="validation_error" path="mobileNumber"></form:errors>
										<span id="mobileNumber_span" class="form-text text-muted">Please
											enter mobile number</span>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-lg-6">
										<label class="col-form-label">Expires on:</label>
										<form:input type="text" path="expires_on" id="expires_on"
											class="form-control" disabled="true" />
										<form:errors id="validation_error" path="expires_on"></form:errors>
										<span class="form-text text-muted"> Member Expiry Date
										</span>
									</div>
									<div class="col-lg-6">
										<label>Change passowrd:</label>
										<div class="input-group">
											<label> <input type="checkbox"
												onclick="showPasswordField()" id="passwordCheckbox"
												name="passwordCheckbox" />
											</label>
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

								<div class="form-group">
									<button type="button" class="btn btn-lg"
										style="float: right; margin: 3%; background-color: #252c35; color: white; padding: 10px 28px"
										onclick="submitForm()">Submit</button>
								</div>

							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/profile/profile.js"></script>
</body>
</html>