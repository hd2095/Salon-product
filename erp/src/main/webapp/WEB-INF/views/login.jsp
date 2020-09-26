<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>Grokar | Login</title>
<meta name="description" content="OperateIn Login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet" href="assets/css/main.css">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favico.png">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center">
									<!-- logon here -->
								</div>
								<p class="lead">Login to your account</p>
							</div>
							<c:if test="${not empty errorMessge}">
								<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div>
							</c:if>
							<form:form method="post" modelAttribute="loginMember"
								name="loginForm" id="loginForm" class="form"
								novalidate="novalidate">
								<div class="form-group">
									<label for="email/phone" class="control-label sr-only">Email/Phone
										Number</label>
									<form:input type="text" path="username" class="form-control"
										id="username" name="username" placeholder="Email/Phone Number"
										required="required" />
									<form:errors id="validation_error" path="username"></form:errors>
								</div>
								<div class="form-group">
									<label for="password" class="control-label sr-only">Password</label>
									<form:input type="password" path="password"
										class="form-control" id="password" name="password"
										placeholder="Password" required="required" />
									<form:errors id="validation_error" path="password"></form:errors>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</div>
								<button type="submit" class="btn btn-black btn-lg btn-block">LOGIN</button>
								<div class="bottom">
									<span class="helper-text"><i class="fa fa-lock"></i> <a
										href="#">Forgot password?</a></span>
								</div>
							</form:form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery/jquery-3.5.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#loginForm')
									.bootstrapValidator(
											{
												fields : {
													username : {
														validators : {
															notEmpty : {
																message : 'Please Enter your Email/Phone Number'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'Please Enter your password'
															}
														}
													}
												}
											});
						});
	</script>
</body>
<style type="text/css">
span#validation_error {
	float: left !important;
}

.help-block {
	float: left !important;
}
</style>
</html> --%>

<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<base href="../../../../">
<meta charset="utf-8" />
<title>Grokar | Login</title>
<meta name="description" content="Login page example" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!--begin::Fonts-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Page Custom Styles(used by this page)-->
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
								<h2 class="font-weight-bold">Sign In</h2>
								<p class="text-muted font-weight-bold">Enter your mobile
									number or email id and password</p>
							</div>
							<form:form method="post" name="loginForm" class="form text-left"
								id="kt_login_signin_form" modelAttribute="loginMember">
								<div class="form-group py-2 m-0">
									<form:input type="text" path="username"
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										placeholder="Mobile number or email id" autocomplete="off" />
								</div>
								<div class="form-group py-2 border-top m-0">
									<form:input type="password" path="password"
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										placeholder="Password" />
								</div>
								<div
									class="form-group d-flex flex-wrap justify-content-between align-items-center mt-5">
									<div class="checkbox-inline">
										<!-- <label class="checkbox m-0 text-muted font-weight-bold">
											<input type="checkbox" name="remember" /> <span></span>Remember
											me
										</label> -->
									</div>
									<a href="javascript:;" id="kt_login_forgot"
										class="text-muted text-hover-primary font-weight-bold">Forgot
										Password ?</a>
								</div>
								<div class="text-center mt-15">
									<button type="submit" id="kt_login_signin_submit"
										class="btn btn-primary btn-pill shadow-sm py-4 px-9 font-weight-bold">Sign
										In</button>
								</div>
							</form:form>
						</div>
<!-- 						<a href="javascript:;" id="kt_login_verify"
							class="text-muted text-hover-primary font-weight-bold"></a> -->
						<!--end:Sign In Form-->
						<!--begin:Sign Up Form-->
						<div class="login-signup">
							<div class="text-center mb-10 mb-lg-20">
								<h3 class="">Sign Up</h3>
								<p class="text-muted font-weight-bold">Enter your details to
									create your account</p>
							</div>
							<form method="post" class="form text-left"
								id="kt_login_signup_form">
								<div class="form-group py-2 m-0">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Fullname" name="fullname" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Mobile Number" name="mobileNumber"
										autocomplete="off" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Email" name="email"
										autocomplete="off" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="password" placeholder="Password" name="password" />
								</div>
								<div class="form-group py-2 m-0 border-top">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="password" placeholder="Confirm Password"
										name="cpassword" />
								</div>
								<div class="form-group mt-5">
									<div class="checkbox-inline">
										<label class="checkbox checkbox-outline font-weight-bold">
											<input type="checkbox" name="agree" /> <span></span>I Agree
											the <a href="#" class="ml-1">terms and conditions</a>.
										</label>
									</div>
								</div>
								<div class="form-group d-flex flex-wrap flex-center">
									<button id="kt_login_signup_submit"
										class="btn btn-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Submit</button>
									<button id="kt_login_signup_cancel"
										class="btn btn-outline-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Cancel</button>
								</div>
							</form>
						</div>
						<!--end:Sign Up Form-->
						<!--begin:Forgot Password Form-->
						<div class="login-forgot">
							<div class="text-center mb-10 mb-lg-20">
								<h3 class="">Forgotten Password ?</h3>
								<p class="text-muted font-weight-bold">Enter your mobile
									number to reset your password</p>
							</div>
							<form method="post" class="form text-left"
								id="kt_login_forgot_form">
								<div class="form-group py-2 m-0 border-bottom">
									<input
										class="form-control h-auto border-0 px-0 placeholder-dark-75"
										type="text" placeholder="Mobile Number" name="mobileNumber"
										autocomplete="off" />
								</div>
								<div class="form-group d-flex flex-wrap flex-center mt-10">
									<button id="kt_login_forgot_submit"
										class="btn btn-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Submit</button>
									<button id="kt_login_forgot_cancel"
										class="btn btn-outline-primary btn-pill font-weight-bold px-9 py-4 my-3 mx-2">Cancel</button>
								</div>
							</form>
						</div>
						<!--end:Forgot Password Form-->
					</div>
				</div>
				<!--end:Content-->
			</div>
		</div>
		<!--end::Login-->
	</div>
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
		src="<c:url value="/assets/js/pages/login/login-general.js?v=7.0.5" />"></script>
	<!--end::Page Scripts-->
	<script type='text/javascript'>
		jQuery(document).ready(function() {
			var invalidUserInForgot = '${invalidUser}';
			if (invalidUserInForgot.length > 0) {
				$('#kt_login_forgot').click();
				$.notify({
					// options
					message : invalidUserInForgot
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
			}
			var alreadySignedUp = '${alreadySignedUp}';
			if (alreadySignedUp.length > 0) {
				$.notify({
					// options
					message : alreadySignedUp
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
			}	
			var userCreatedSuccessfully = '${userCreatedSuccessfully}';
			if (userCreatedSuccessfully.length > 0) {
				$.notify({
					// options
					message : userCreatedSuccessfully
				}, {
					// settings
					type : 'success',
					delay : 5000
				});
			}
			var userCreatedFailure = '${userCreatedFailure}';
			if (userCreatedFailure.length > 0) {
				$.notify({
					// options
					message : userCreatedFailure
				}, {
					// settings
					type : 'danger',
					delay : 5000
				});
			}
			var errorMessge = '${errorMessge}';
			if (errorMessge.length > 0) {
				$.notify({
					// options
					message : errorMessge
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
</html>