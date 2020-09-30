<%-- <!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<base href="../../../../">
<meta charset="utf-8" />
<title>Grokar | Complete Organization Registration</title>
<meta name="description" content="Login page example" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!--begin::Fonts-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Page Custom Styles(used by this page)-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/pages/login/login-6.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/plugins/global/plugins.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/style.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/header/base/light.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/brand/dark.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/header/menu/light.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/aside/dark.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="assets/media/logos/favicon.ico" />
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_body"
	class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed aside-minimize-hoverable page-loading">
	<!--begin::Main-->
	<div class="d-flex flex-column flex-root">
		<!--begin::Login-->
		<div
			class="login login-6 login-signin-on login-signin-on d-flex flex-row-fluid"
			id="kt_login">
			<div
				class="d-flex flex-column flex-lg-row flex-row-fluid text-center"
				style="background-image: url(assets/media/bg/bg-3.jpg);">
				<!--begin:Aside-->
				<div class="d-flex w-100 flex-center p-15">
					<div class="login-wrapper">
						<!--begin:Aside Content-->
						<div class="text-dark-75">
							<a href="#"> <img src="assets/media/logos/logo-letter-13.png"
								class="max-h-75px" alt="" />
							</a>
							<h3 class="mb-8 mt-22 font-weight-bold">JOIN OUR GREAT
								COMMUNITY</h3>
							<p class="mb-15 text-muted font-weight-bold">The ultimate
								Bootstrap &amp; Angular 6 admin theme framework for next
								generation web apps.</p>
							<button type="button" id="kt_login_signup"
								class="btn btn-outline-primary btn-pill py-4 px-9 font-weight-bold">Get
								An Account</button>
						</div>
						<!--end:Aside Content-->
					</div>
				</div>
				<!--end:Aside-->
				<!--begin:Divider-->
				<div class="login-divider">
					<div></div>
				</div>
				<!--end:Divider-->
				<!--begin:Content-->
				<div
					class="d-flex w-100 flex-center p-15 position-relative overflow-hidden">
					<div class="login-wrapper">
						<!--begin:Sign In Form-->
						<div class="login-signin">
							<div class="text-center mb-10 mb-lg-20">
								<h2 class="font-weight-bold">Organization Details</h2>
								<p class="text-muted font-weight-bold">Please fill up your
									organization details to start using Grokar, this information
									will be printed on your invoice.</p>
							</div>
							<form method="post" class="form text-left"
								id="org_form">
								<div class="form-group py-2 m-0">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Organization Name" name="orgName" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<select class="form-control select2" id="organization_type"
										name="organization_type">
										<option value="Select">Select organization type</option>
										<option value="Manufacturer/Trader">Manufacturer/Trader</option>
										<option value="Personal">Personal</option>
										<option value="Salon/spa">Salon/spa</option>
										<option value="SMEs">SMEs</option>
										<option value="Shops">Shops</option>
									</select>
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Organization Address"
										name="orgAddress" autocomplete="off" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="GSTN No" name="orgGstnNo"
										autocomplete="off" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="GSTN Percent" name="orgGstnPercent" />
								</div>
								<div class="form-group d-flex flex-wrap flex-center">
									<button id="org_form_submit"
										class="btn btn-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Submit</button>
									<button id="org_form_cancel"
										class="btn btn-outline-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--end:Content-->
		</div>
	</div>
	<!--end::Login-->
	<!--end::Main-->
	<script>
		var HOST_URL = "https://keenthemes.com/metronic/tools/preview";
	</script>
	<!--begin::Global Config(global config for global JS scripts)-->
	<script>
		var KTAppSettings = {
			"breakpoints" : {
				"sm" : 576,
				"md" : 768,
				"lg" : 992,
				"xl" : 1200,
				"xxl" : 1400
			},
			"colors" : {
				"theme" : {
					"base" : {
						"white" : "#ffffff",
						"primary" : "#3699FF",
						"secondary" : "#E5EAEE",
						"success" : "#1BC5BD",
						"info" : "#8950FC",
						"warning" : "#FFA800",
						"danger" : "#F64E60",
						"light" : "#E4E6EF",
						"dark" : "#181C32"
					},
					"light" : {
						"white" : "#ffffff",
						"primary" : "#E1F0FF",
						"secondary" : "#EBEDF3",
						"success" : "#C9F7F5",
						"info" : "#EEE5FF",
						"warning" : "#FFF4DE",
						"danger" : "#FFE2E5",
						"light" : "#F3F6F9",
						"dark" : "#D6D6E0"
					},
					"inverse" : {
						"white" : "#ffffff",
						"primary" : "#ffffff",
						"secondary" : "#3F4254",
						"success" : "#ffffff",
						"info" : "#ffffff",
						"warning" : "#ffffff",
						"danger" : "#ffffff",
						"light" : "#464E5F",
						"dark" : "#ffffff"
					}
				},
				"gray" : {
					"gray-100" : "#F3F6F9",
					"gray-200" : "#EBEDF3",
					"gray-300" : "#E4E6EF",
					"gray-400" : "#D1D3E0",
					"gray-500" : "#B5B5C3",
					"gray-600" : "#7E8299",
					"gray-700" : "#5E6278",
					"gray-800" : "#3F4254",
					"gray-900" : "#181C32"
				}
			},
			"font-family" : "Poppins"
		};
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/custom/prismjs/prismjs.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/plugins/global/plugins.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/scripts.bundle.js?v=7.0." />"></script>
	<!--end::Global Theme Bundle-->
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/login/client-org-reg.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/select2.js" />"></script>
	<!--end::Page Scripts-->
	<script type='text/javascript'>
		jQuery(document).ready(function() {
			var OtpSendSuccess = '${OtpSendSuccess}';
			if (OtpSendSuccess.length > 0) {
				$.notify({
					// options
					message : OtpSendSuccess
				}, {
					// settings
					type : 'success',
					delay : 5000
				});
			}
			var OtpSendFailure = '${OtpSendFailure}';
			if (OtpSendFailure.length > 0) {
				$.notify({
					// options
					message : OtpSendFailure
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
			}
			var OtpDoesntMatch = '${OtpDoesntMatch}';
			if (OtpDoesntMatch.length > 0) {
				$.notify({
					// options
					message : OtpDoesntMatch
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
			}
		});
	</script>
</body>
<!--end::Body-->
</html> --%>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/pages/login/login.css"/>"
	rel="stylesheet" type="text/css" />
<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>Grokar | Create Organization</title>
</head>
<body class="login-bg">
	<div class="login-form">
		<form method="post" name="createOrganizationForm"
			class="form text-left" action="createOrganization" id="createOrganizationForm">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Organization details</h4>
			<div class="form-group">
				<label for="login_field">Organization Name</label> <input name = "orgName"
					type="text" class="form-control" placeholder="Organization Name"
					required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Organization Type</label> <select
					class="form-control select2" id="organization_type"
					name="organization_type">
					<option value="Select">Select organization type</option>
					<option value="Manufacturer/Trader">Manufacturer/Trader</option>
					<option value="Personal">Personal</option>
					<option value="Salon/spa">Salon/spa</option>
					<option value="SMEs">SMEs</option>
					<option value="Shops">Shops</option>
				</select>
			</div>
			<div class="form-group">
				<label for="login_field">Organization Address</label> <input name = "orgAddress"
					type="text" class="form-control" placeholder="Organization Address"
					required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">GSTN No</label> <input type="text" name = "orgGstnNo"
					class="form-control" placeholder="GSTN No" />
			</div>
			<div class="form-group">
				<label for="login_field">GSTN percent</label> <input type="text" name = "orgGstnPercent"
					class="form-control" placeholder="GSTN percent" />
			</div>
			<input type="submit" class="btn btn-primary btn-block btn-lg"
				value="Create Organization">
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/jquery/jquery.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/login/login.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/bootstrap/js/bootstrap-notify.min.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var errorMessge = '${errorMessge}';
		if (errorMessge.length > 0) {
			$.notify({
				message : errorMessge
			}, {
				type : 'danger',
				delay : 5000
			});
		}
	});
</script>
</html>