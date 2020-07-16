<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.html"><img src="assets/img/logo-dark.png"
					alt="Klorofil Logo" class="img-responsive logo"></a>
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
							data-toggle="dropdown"><span>${sessionScope.session_firstname}</span> <i
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
						<li><a href="index.html" class="active"><i
								class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
						<li><a href="appointment" class=""><i
								class="lnr lnr-code"></i> <span>Appointments</span></a></li>
						<li><a href="calendar" class=""><i
								class="lnr lnr-chart-bars"></i> <span>Calendar</span></a></li>						
						<li><a href="client" class=""><i
								class="lnr lnr-alarm"></i> <span>Clients</span></a></li>
								<li><a href="staff" class=""><i
								class="lnr lnr-alarm"></i> <span>Staff</span></a></li>
								<li><a href="services" class=""><i
								class="lnr lnr-alarm"></i> <span>Services</span></a></li>
						<li><a href="#subPages" data-toggle="collapse"
							class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Inventory</span>
								<i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="inventory/products" class="">Products</a></li>
									<li><a href="inventory/newOrder" class="">New Order</a></li>
									<li><a href="inventory/sales" class="">Sales</a></li>
									<li><a href="inventory/stock" class="">Stock</a></li>
									<li><a href="inventory/addSupplier" class="">Add Supplier</a></li>									
								</ul>
							</div></li>
						<li><a href="marketing" class=""><i
								class="lnr lnr-dice"></i> <span>Marketing</span></a></li>
						<li><a href="profile-creation" class=""><i
								class="lnr lnr-text-format"></i> <span>Profile-Creation</span></a></li>						
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
	</div>
</body>
<script src="assets/js/jquery/jquery-3.5.1.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
</html>