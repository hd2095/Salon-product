<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>OperateIN | Login</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/vendor/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/pages/login/login.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/media/logos/favico.png" />
<link rel="stylesheet" href="assets/landing-page/css/LineIcons.2.0.css">
<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
</head>
<body class="login-bg">
	<nav class="navbar navbar-default" style="background-color: white;">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<img id="mobile_logon" style="height:140%;display:none"
						src="assets/img/Operatein-mobile-logo.png" />
				</div>
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" id="logon"
					style="font-weight: bold; font-size: x-large;" href="/">OperateIN</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="signup" style="font-weight: bold;"><span
							class="lni lni-user"></span> Sign Up</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="login-form">
		<form:form method="post" name="loginForm" class="form text-left"
			action="login" id="loginForm" modelAttribute="loginMember">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Login to OperateIN</h4>
			<div class="form-group">
				<label for="login_field">Phone number or email address </label>
				<form:input type="text" path="username" class="form-control"
					placeholder="Phone number or email address" required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Password </label>
				<form:input type="password" path="password" class="form-control"
					placeholder="Password" required="required" />
			</div>
			<div class="form-group small clearfix">
				<a href="forgot-password" class="forgot-link">Forgot Password?</a>
			</div>
			<input type="submit" class="btn btn-primary btn-block btn-lg"
				value="Sign in">
		</form:form>
		<div class="text-center small">
			Don't have an account? <a href="signup">Sign up</a>
		</div>
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
		var OtpSentSuccessFully = '${OtpSentSuccessFully}';
		if (OtpSentSuccessFully.length > 0) {
			$.notify({
				message : OtpSentSuccessFully
			}, {
				type : 'success',
				delay : 5000
			});
		}
	});
</script>
</html>