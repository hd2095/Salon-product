<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--
Template Name: Metronic - Bootstrap 4 HTML, React, Angular 9 & VueJS Admin Dashboard Theme
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: https://1.envato.market/EA4JP
Renew Support: https://1.envato.market/EA4JP
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<html lang="en">
<!--begin::Head-->
<head>
<base href="../../../../">
<meta charset="utf-8" />
<title>Gabmor | Register Member</title>
<meta name="description" content="Member Registration page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!--begin::Fonts-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Page Custom Styles(used by this page)-->
<link href="assets/css/pages/login/classic/login-1.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<!--end::Page Custom Styles-->
<!--begin::Global Theme Styles(used by all pages)-->
<link href="assets/plugins/global/plugins.bundle.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/custom/prismjs/prismjs.bundle.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link href="assets/css/style.bundle.css?v=7.0.4" rel="stylesheet"
	type="text/css" />
<!--end::Global Theme Styles-->
<!--begin::Layout Themes(used by all pages)-->
<link href="assets/css/themes/layout/header/base/light.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link href="assets/css/themes/layout/header/menu/light.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link href="assets/css/themes/layout/brand/dark.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link href="assets/css/themes/layout/aside/dark.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<!--end::Layout Themes-->
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
			class="login login-1 login-signin-on d-flex flex-column flex-lg-row flex-row-fluid bg-white"
			id="kt_login">
			<!--begin::Aside-->
			<div
				class="login-aside d-flex flex-row-auto bgi-size-cover bgi-no-repeat p-10 p-lg-10"
				style="background-image: url(assets/media/bg/bg-4.jpg);">
				<!--begin: Aside Container-->
				<div
					class="d-flex flex-row-fluid flex-column justify-content-between">
					<!--begin: Aside header-->
					<a href="#" class="flex-column-auto mt-5"> <img
						src="assets/media/logos/logo-letter-1.png" class="max-h-70px"
						alt="" />
					</a>
					<!--end: Aside header-->
					<!--begin: Aside content-->
					<div
						class="flex-column-fluid d-flex flex-column justify-content-center">
						<h3 class="font-size-h1 mb-5 text-white">Welcome to Metronic!</h3>
						<p class="font-weight-lighter text-white opacity-80">The
							ultimate Bootstrap, Angular 8, React &amp; VueJS admin theme
							framework for next generation web apps.</p>
					</div>
					<!--end: Aside content-->
					<!--begin: Aside footer for desktop-->
					<div
						class="d-none flex-column-auto d-lg-flex justify-content-between mt-10">
						<div class="opacity-70 font-weight-bold text-white">© 2020
							Metronic</div>
						<div class="d-flex">
							<a href="#" class="text-white">Privacy</a> <a href="#"
								class="text-white ml-10">Legal</a> <a href="#"
								class="text-white ml-10">Contact</a>
						</div>
					</div>
					<!--end: Aside footer for desktop-->
				</div>
				<!--end: Aside Container-->
			</div>
			<!--begin::Aside-->
			<!--begin::Content-->
			<div
				class="flex-row-fluid d-flex flex-column position-relative p-7 overflow-hidden">
				<!--begin::Content body-->
				<div class="d-flex flex-column-fluid flex-center mt-30 mt-lg-0">
					<!--begin::Signup-->
					<div class="login-form login-signup">
						<div class="text-center mb-10 mb-lg-20">
							<h3 class="font-size-h1">Register Member</h3>
							<p class="text-muted font-weight-bold">Enter Member details</p>
						</div>
						<!--begin::Form-->
						<form:form action="register/member" method="post"
							modelAttribute="registerMemberForm"
							onsubmit="return(validateForm());" novalidate="novalidate">
							<div class="form-group row">
								<div class="col-lg-12">
									<label>Organization</label>
									<form:select path="registerOrganization"
										class="form-control select2" id="kt_select2_10"
										name="organization">
									</form:select>
									<span class="form-text text-muted">Please select
										Organization</span>
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
										type="text" id="last_name" placeholder="Last Name"
										name="lastName" autocomplete="off"></form:input>
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
										type="email" placeholder="Email Id" name="emailId"/>
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
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
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
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<span class="form-text text-muted">Please enter member
										expiry Date</span>
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
									<span class="form-text text-muted">Please select member
										type</span>
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
						<!--end::Form-->
					</div>
					<!--end::Signup-->
				</div>
				<!--end::Content body-->
				<!--begin::Content footer for mobile-->
				<div
					class="d-flex d-lg-none flex-column-auto flex-column flex-sm-row justify-content-between align-items-center mt-5 p-5">
					<div class="text-dark-50 font-weight-bold order-2 order-sm-1 my-2">©
						2020 Metronic</div>
					<div class="d-flex order-1 order-sm-2 my-2">
						<a href="#" class="text-dark-75 text-hover-primary">Privacy</a> <a
							href="#" class="text-dark-75 text-hover-primary ml-4">Legal</a> <a
							href="#" class="text-dark-75 text-hover-primary ml-4">Contact</a>
					</div>
				</div>
				<!--end::Content footer for mobile-->
			</div>
			<!--end::Content-->
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
				"xxl" : 1200
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
						"light" : "#F3F6F9",
						"dark" : "#212121"
					},
					"light" : {
						"white" : "#ffffff",
						"primary" : "#E1F0FF",
						"secondary" : "#ECF0F3",
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
						"secondary" : "#212121",
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
					"gray-200" : "#ECF0F3",
					"gray-300" : "#E5EAEE",
					"gray-400" : "#D6D6E0",
					"gray-500" : "#B5B5C3",
					"gray-600" : "#80808F",
					"gray-700" : "#464E5F",
					"gray-800" : "#1B283F",
					"gray-900" : "#212121"
				}
			},
			"font-family" : "Poppins"
		};
	</script>
	<!--end::Global Config-->
	<!--begin::Global Theme Bundle(used by all pages)-->
	<script src="assets/plugins/global/plugins.bundle.js?v=7.0.4"></script>
	<script src="assets/plugins/custom/prismjs/prismjs.bundle.js?v=7.0.4"></script>
	<script src="assets/js/scripts.bundle.js?v=7.0.4"></script>
	<!--end::Global Theme Bundle-->
	<!--begin::Page Scripts(used by this page)-->
	<script src="assets/js/pages/custom/register/member.js"></script>
	<script src="assets/js/pages/select.js"></script>
	<script src="assets/js/pages/datePicker.js"></script>
	<!--end::Page Scripts-->
</body>
<!--end::Body-->
</html>