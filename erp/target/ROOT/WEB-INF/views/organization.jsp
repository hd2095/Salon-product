<html>
<head>
<title>OperateIN | Organization</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<form:form modelAttribute="clientOrganizationForm" class="form"
						name="clientOrganizationForm" action="updateClientOrganization"
						method="post" id="clientOrganizationForm" autocomplete="off">
						<div class="card-body">
							<form:hidden path="master_id"></form:hidden>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">Organization Name:</label>
									<form:input type="text" path="organization_name"
										id="organization_name" class="form-control" readonly="true" />
									<form:errors id="validation_error" path="organization_name"></form:errors>
									<span class="form-text text-muted"> Organization Name</span>
								</div>
								<div class="col-lg-6">
									<label class="col-form-label">Organization Address:</label>
									<form:input type="text" path="orgnization_address"
										id="orgnization_address" class="form-control" readonly="true" />
									<form:errors id="validation_error" path="orgnization_address"></form:errors>
									<span class="form-text text-muted">Organization Address</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">Organization Gstn no:</label>
									<form:input type="text" path="gstn_no" id="gstn_no"
										class="form-control" />
									<form:errors id="validation_error" path="gstn_no"></form:errors>
									<span class="form-text text-muted"> Organization Gstn no
									</span>
								</div>
								<div class="col-lg-6">
									<label class="col-form-label">Organization Gstn %:</label>
									<form:input type="text" path="gstn_percent" id="gstn_percent"
										class="form-control" />
									<form:errors id="validation_error" path="gstn_percent"></form:errors>
									<span class="form-text text-muted">Organization Gstn %:</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label class="col-form-label">Account Type:</label>
									<form:input type="text" path="plan" id="plan"
										class="form-control" readonly="true" />
									<form:errors id="validation_error" path="plan"></form:errors>
									<span class="form-text text-muted"> Account type </span>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<input type="submit"
										class="btn font-weight-bold btn-primary btn-shadow mr-2"
										value="Submit" />
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
			var elementToFind = $('li.menu-item-active');
			var element = $('ul.menu-nav').find(elementToFind);
			$(element).removeClass('menu-item-active');
			$('#profile-creation_nav').addClass('menu-item-active');
			$('#inventory_nav').removeClass('menu-item-active');
			var data = '${successFullyUpdated}';
			if (data.length > 1) {
				$('#successfullyUpdated').show();
				$('#successfullyUpdated').delay(2000).fadeOut(500);
			}
			$('#message').html(data);
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/profile/clientOrganization.js" />"></script>
</body>
</html>