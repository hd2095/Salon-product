<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>Grokar | Sign Up</title>
</head>
<body class="login-bg">
	<div class="loader" id="spinner" style="display: none;"></div>
	<div class="login-form">
		<form method="post" name="singUpForm" class="form text-left"
			action="signup" id="singUpForm">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Sign Up to Grokar</h4>
			<div class="form-group">
				<label for="login_field">Full name</label> <input type="text"
					name="fullname" class="form-control" placeholder="Full name"
					required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Phone number</label> <input type="text"
					name="mobileNumber" class="form-control" placeholder="Phone number"
					required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Email address</label> <input type="text"
					name="email" class="form-control" placeholder="Email address"
					required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Password </label> <input type="password"
					id="password" name="password" class="form-control"
					placeholder="Password" required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Confirm Password </label> <input
					type="password" name="cpassword" class="form-control"
					placeholder="Password" required="required" />
			</div>
			<input type="submit" id="signupBtn"
				class="btn btn-primary btn-block btn-lg" value="Create Account">
		</form>
		<div class="text-center small">
			have an account? <a href="login">Sign in</a>
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
	src="<c:url value="/assets/js/pages/login/signup.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/bootstrap/js/bootstrap-notify.min.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		$('#signupBtn').click(function (){
			$('#spinner').show();	
		});
		var alreadySignedUp = '${alreadySignedUp}';
		if (alreadySignedUp.length > 0) {
			$.notify({
				message : alreadySignedUp
			}, {
				type : 'danger',
				delay : 5000
			});
		}
	});
</script>
</html>