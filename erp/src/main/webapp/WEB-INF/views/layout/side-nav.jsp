<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/bootstrap-timepicker.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favico.png">
</head>
<body>
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="dashboard"><img src="assets/img/logon.png"
					alt="operateIn Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth">
						<i class="lnr lnr-arrow-left-circle"></i>
					</button>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span>Hi,
									${sessionScope.session_firstname}</span> <i
								class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="invalidate"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
							</ul></li>
						<!-- <li>
							<a class="update-pro" href="https://www.themeineed.com/downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="dashboard" id="dashboard_nav" class="active"><i
								class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
						<li><a href="appointment" id="appointment_nav" class=""><i
								class="lnr lnr-list"></i> <span>Appointments</span></a></li>
						<li><a href="calendar" id="calendar_nav" class=""><i
								class="lnr lnr-calendar-full"></i> <span>Calendar</span></a></li>
						<li><a href="client" id="client_nav" class=""><i
								class="lnr lnr-users"></i> <span>Clients</span></a></li>
						<li><a href="staff" id="staff_nav" class=""><i
								class="lnr lnr-user"></i> <span>Staff</span></a></li>
						<li><a href="services" id="services_nav" class=""><i
								class="lnr lnr-store"></i> <span>Services</span></a></li>
						<li><a href="#subPages" data-toggle="collapse"
							id="inventory_nav" class="collapsed"><i class="lnr lnr-enter"></i>
								<span>Inventory</span> <i
								class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="inventory/products" id="products_nav"
										class="">Products</a></li>
									<li><a href="inventory/newOrder" id="newOrder_nav"
										class="">New Order</a></li>
									<li><a href="inventory/sales" id="sales_nav" class="">Sales</a></li>
									<li><a href="inventory/stock" id="stock_nav" class="">Stock</a></li>
									<li><a href="inventory/addSupplier" id="supplier_nav"
										class="">Add Supplier</a></li>
								</ul>
							</div></li>
						<li><a href="marketing" id="marketing_nav" class=""><i
								class="lnr lnr-exit-up"></i> <span>Marketing</span></a></li>
						<li><a href="profileCreation" id="profile-creation_nav"
							class=""><i class="lnr lnr-cog"></i> <span>Profile-Creation</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
	</div>
	<div id="loading-gif"
		style="position: fixed; top: 50%; left: 50%; display: none; z-index: 15;">
		<img id="loader" src="assets/img/loading.gif">
	</div>
	<div id="successfullyUpdated" class="alert alert-success"
		style="width: 25%; position: fixed; top: 15%; left: 70%;display:none;">
		<strong id="message"></strong>
	</div>
</body>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script src="assets/js/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script src="assets/js/bootstrap-timepicker.js"></script>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script src="assets/js/common/common.js"></script>
</html>