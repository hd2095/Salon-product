<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/vendor/bootstrap/css/bootstrap.min.css"/>"
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
<title>OperateIN | Complete Registration</title>
</head>
<body class="login-bg">
	<div class="login-form">
		<form method="post" name="otp" class="form text-left" action="verify"
			id="otp">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Verify it's you</h4>
			<div class="form-group">
				<label for="login_field">Enter OTP sent to your registered
					mobile number.</label> <input type="text" class="form-control" name="otp"
					placeholder="OTP" required="required" />
			</div>
			<input type="submit" class="btn btn-primary btn-block btn-lg"
				value="Submit OTP">
		</form>
<!-- 		<div class="text-center small">
			<a href="login">Request OTP</a>
		</div> -->
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/jquery/jquery.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/vendor/bootstrap/js/bootstrap-notify.min.js" />"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/login/forgot.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var OtpSendFailure = '${OtpSendFailure}';
		if (OtpSendFailure.length > 0) {
			$.notify({
				message : OtpSendFailure
			}, {
				type : 'danger',
				delay : 5000
			});
		}
		var OtpDoesntMatch = '${OtpDoesntMatch}';
		if (OtpDoesntMatch.length > 0) {
			$.notify({
				message : OtpDoesntMatch
			}, {
				type : 'danger',
				delay : 5000
			});
		}
		var OtpSendSuccess = '${OtpSendSuccess}';
		if (OtpSendSuccess.length > 0) {
			$.notify({
				message : OtpSendSuccess
			}, {
				type : 'success',
				delay : 5000
			});
		}
	});
</script>
</html>