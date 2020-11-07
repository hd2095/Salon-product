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
	href="${pageContext.request.contextPath}/assets/media/logos/favico.png" />
<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>OperateIN | Create Organization</title>
</head>
<body class="login-bg">
	<div class="loader" id="spinner" style="display: none;"></div>
	<div class="login-form">
		<form method="post" name="createOrganizationForm"
			class="form text-left" action="createOrganization"
			id="createOrganizationForm">
			<h4 class="modal-title">Organization details</h4>
			<div class="form-group">
				<label for="login_field">Organization Name</label> <input
					name="orgName" type="text" class="form-control"
					placeholder="Organization Name" required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">Organization Address</label> <input
					name="orgAddress" type="text" class="form-control"
					placeholder="Organization Address" required="required" />
			</div>
			<div class="form-group">
				<label for="login_field">GSTN No</label> <input type="text"
					name="orgGstnNo" class="form-control" placeholder="GSTN No" />
			</div>
			<div class="form-group">
				<label for="login_field">GSTN percent</label> <input type="text"
					name="orgGstnPercent" class="form-control"
					placeholder="GSTN percent" />
			</div>
			<input id="createOrgBtn" type="submit"
				class="btn btn-primary btn-block btn-lg" value="Create Organization">
		</form>
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
	src="<c:url value="/assets/js/pages/login/client-org-reg.js" />"></script>
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