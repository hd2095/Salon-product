<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>Grokar | Login</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/pages/login/login.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/media/logos/favicon.jpeg" />
<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
</head>
<body class="login-bg">
	<div class="login-form">
		<form:form method="post" name="loginForm" class="form text-left"
			action="login" id="loginForm" modelAttribute="loginMember">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Login to Grokar</h4>
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
	});
</script>
</html>