<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<meta charset="utf-8" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<title>Gabmor | Login</title>
<meta name="description" content="Gabmor Login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- GOOGLE FONTS -->
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
							<form:form method="post" modelAttribute="loginMember"
								class="form" novalidate="novalidate">
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">Email/Phone
										Number</label>
									<form:input type="email" path="username" class="form-control" id="signin-email"
										placeholder="Email/Phone Number" />
										<form:errors id="validation_error" path="username"></form:errors>
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<form:input type="password" path="password" class="form-control"
										id="signin-password" placeholder="Password" />
										<form:errors id="validation_error" path="password"></form:errors>
								</div>
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left"> <input
										type="checkbox"> <span>Remember me</span>
									</label>
								</div>
								<button type="submit" class="btn btn-black btn-lg btn-block">LOGIN</button>
								<div class="bottom">
									<span class="helper-text"><i class="fa fa-lock"></i> <a
										href="#">Forgot password?</a></span>
								</div>
								</form:form>
						</div>
					</div>
					<!-- <div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">Free Bootstrap dashboard template</h1>
							<p>by The Develovers</p>
						</div>
					</div> -->
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<style type="text/css">
span#validation_error {
	float: left !important;
}
</style>
</html>