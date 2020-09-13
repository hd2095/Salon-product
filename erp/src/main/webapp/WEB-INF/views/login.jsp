<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>Servmor | Login</title>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
								<!-- 								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left"> <input
										type="checkbox"> <span>Remember me</span>
									</label>
								</div> -->
								<button type="submit" class="btn btn-black btn-lg btn-block">LOGIN</button>
<!-- 								<div class="bottom">
									<span class="helper-text"><i class="fa fa-lock"></i> <a
										href="#">Forgot password?</a></span>
								</div> -->
							</form:form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
						</div>
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
</html>