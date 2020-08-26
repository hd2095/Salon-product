<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>OperateIn | Register Organization</title>
<meta name="description" content="Organization Registration page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
</head>
<body>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<!--begin::Subheader-->
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<!--begin::Page Title-->
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Register
						Organization</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<form:form action="register/organization" method="post"
						modelAttribute="registerOrganizationForm"
						onsubmit="return(validateForm());" novalidate="novalidate">
						<div class="card-body">
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Organization Name:</label>
									<form:input path="organization_name"
										class="form-control form-control-solid h-auto py-5 px-6"
										type="text" id="organization_name"
										placeholder="Organization Name" name="organizationName"
										autocomplete="off"></form:input>
									<form:errors id="validation_error" path="organization_name"></form:errors>
								</div>
								<div class="col-lg-6">
									<label>Organization Address:</label>
									<form:input path="orgnization_address"
										class="form-control form-control-solid h-auto py-5 px-6"
										type="text" placeholder="Organization Address"
										name="organizationAddress" autocomplete="off" />
									<form:errors id="validation_error" path="orgnization_address"></form:errors>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Organization GSTN no:</label>
									<form:input path="gstn_no"
										class="form-control form-control-solid h-auto py-5 px-6"
										type="text" placeholder="GSTN No" name="gstnNo"
										autocomplete="off" />
									<form:errors id="validation_error" path="gstn_no"></form:errors>
								</div>
								<div class="col-lg-6">
									<label>Organization GSTN %:</label>
									<form:input path="gstn_percent"
										class="form-control form-control-solid h-auto py-5 px-6"
										type="text" placeholder="GSTN %" name="gstn_percent"
										autocomplete="off" />
									<form:errors id="validation_error" path="gstn_percent"></form:errors>
								</div>
								</div>
								<div class="form-group row">
								<div class="col-lg-6">
									<label>Organization Plan:</label>
									<form:select path="plan" class="form-control" name="plan"
										id="plan">
									</form:select>
									<form:errors id="validation_error" path="plan"></form:errors>
								</div>
							</div>
							<div class="card-footer">
								<div class="row">
									<div class="col-lg-6"></div>
									<div class="col-lg-6 text-right">
										<form:button type="submit"
											class="btn btn-primary mr-2">Submit</form:button>
										<button type="button"
											class="btn btn-secondary">Cancel</button>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/register/organization.js"></script>
	<script src="assets/js/utilities/push-divs.js"></script>
</body>
</html>