<!doctype html>
<html class="no-js" lang="en">
<head>
<!--====== Title ======-->
<title>OperateIN - Salon management software by Gabmor</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Salon Management software and much more by Gabmor" />
<meta name="keywords"
	content="free responsive salon management software, salon management, spa management, mobile friendly" />
<!--====== Favicon Icon ======-->
<link rel="shortcut icon" href="assets/images/favicon.png"
	type="image/png">
<!--====== Animate CSS ======-->
<link rel="stylesheet" href="assets/landing-page/css/animate.css">
<!--====== Line Icons CSS ======-->
<link rel="stylesheet" href="assets/landing-page/css/LineIcons.2.0.css">
<!--====== Bootstrap CSS ======-->
<link rel="stylesheet"
	href="assets/landing-page/css/bootstrap-4.5.0.min.css">
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/landing-page/css/default.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/landing-page/css/style.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/media/logos/favico.png" />
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<%
			response.sendRedirect("dashboard");
		%>
	</sec:authorize>
	<!--====== PRELOADER PART START ======-->
	<div class="preloader">
		<div class="loader">
			<div class="ytp-spinner">
				<div class="ytp-spinner-container">
					<div class="ytp-spinner-rotator">
						<div class="ytp-spinner-left">
							<div class="ytp-spinner-circle"></div>
						</div>
						<div class="ytp-spinner-right">
							<div class="ytp-spinner-circle"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--====== PRELOADER PART ENDS ======-->
	<!--====== HEADER PART START ======-->
	<header class="header-area">
		<div class="navbar-area">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<nav class="navbar navbar-expand-lg">
							<a class="navbar-brand" href="#"
								style="color: navy; font-size: xx-large; font-weight: 900;">
								OperateIN </a>
							<button class="navbar-toggler" type="button"
								data-toggle="collapse" data-target="#navbarSupportedContent"
								aria-controls="navbarSupportedContent" aria-expanded="false"
								aria-label="Toggle navigation">
								<span class="toggler-icon"></span> <span class="toggler-icon"></span>
								<span class="toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse sub-menu-bar"
								id="navbarSupportedContent">
								<ul id="nav" class="navbar-nav ml-auto">
									<li class="nav-item active"><a class="page-scroll"
										href="#home">Home</a></li>
									<li class="nav-item"><a class="page-scroll"
										href="#features">Features</a></li>
									<li class="nav-item"><a class="page-scroll" href="#about">About</a>
									</li>
									<li class="nav-item"><a class="page-scroll" href="login">Login</a>
									</li>
								</ul>
							</div>
							<!-- navbar collapse -->
							<div class="navbar-btn d-none d-sm-inline-block">
								<a class="main-btn" data-scroll-nav="0" href="signup"
									rel="nofollow">Sign up for free</a>
							</div>
						</nav>
						<!-- navbar -->
					</div>
				</div>
				<!-- row -->
			</div>
			<!-- container -->
		</div>
		<!-- navbar area -->
		<div id="home" class="header-hero bg_cover"
			style="background-image: url(assets/landing-page/images/bg.jpeg)">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-8">
						<div class="header-hero-content text-center">
							<h3 class="header-sub-title wow fadeInUp"
								data-wow-duration="1.3s" data-wow-delay="0.2s">OperateIN -
								Management Software for Salons & Spas</h3>
							<a href="signup" class="main-btn wow fadeInUp"
								data-wow-duration="1.3s" data-wow-delay="1.1s">Get Started</a>
						</div>
						<!-- header hero content -->
					</div>
				</div>
				<!-- row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="header-hero-image text-center wow fadeIn"
							data-wow-duration="1.3s" data-wow-delay="1.4s">
							<img src="assets/landing-page/images/PC.png" alt="hero">
						</div>
						<!-- header hero image -->
					</div>
				</div>
				<!-- row -->
			</div>
			<!-- container -->
			<!-- <div id="particles-1" class="particles"></div> -->
		</div>
		<!-- header hero -->
	</header>
	<!--====== HEADER PART ENDS ======-->
	<!--====== SERVICES PART START ======-->
	<section id="features" class="services-area pt-120">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="section-title text-center pb-40">
						<div class="line m-auto"></div>
						<h3 class="title">
							Our Awesome Product, <span> Comes with everything you need
								to get started!</span>
						</h3>
					</div>
					<!-- section title -->
				</div>
			</div>
			<!-- row -->
			<div class="row justify-content-center">
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.2s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-1.svg"
								alt="shape"> <i class="lni lni-calendar"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">Appointment Scheduler</a>
							</h4>
							<p class="text">Got Appointments? worry no more adding them
								just got easier, a button click away.</p>
						</div>
					</div>
					<!-- single services -->
				</div>
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.5s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-2.svg"
								alt="shape"> <i class="lni lni-envelope"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">SMS Marketing</a>
							</h4>
							<p class="text">Ability to send promotional messages there by
								reaching more and more customers.</p>
							<br />
						</div>
					</div>
					<!-- single services -->
				</div>
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.8s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-3.svg"
								alt="shape"> <i class="lni lni-users"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">Manage Staff</a>
							</h4>
							<p class="text">Managing staff allows you to identify which
								staff has generated the most revenue.</p>
							<br />
						</div>
					</div>
					<!-- single services -->
				</div>
			</div>
			<!-- row -->
			<div class="row justify-content-center">
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.2s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-3.svg"
								alt="shape"> <i class="lni lni-cog"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">Inventory Management</a>
							</h4>
							<p class="text">Tired of managing inventory ? we've got you
								covered with our industry level Inventory management module.</p>
						</div>
					</div>
					<!-- single services -->
				</div>
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.5s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-2.svg"
								alt="shape"> <i class="lni lni-home"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">Interactive Dashboard</a>
							</h4>
							<p class="text">Forget Bookkeeping Graphs are here to help,
								providing insights about your business growth.</p>
						</div>
					</div>
					<!-- single services -->
				</div>
				<div class="col-lg-4 col-md-7 col-sm-8">
					<div class="single-services text-center mt-30 wow fadeIn"
						data-wow-duration="1s" data-wow-delay="0.8s">
						<div class="services-icon">
							<img class="shape"
								src="assets/landing-page/images/services-shape.svg" alt="shape">
							<img class="shape-1"
								src="assets/landing-page/images/services-shape-1.svg"
								alt="shape"> <i class="lni lni-credit-cards"></i>
						</div>
						<div class="services-content mt-30">
							<h4 class="services-title">
								<a href="#">Invoicing</a>
							</h4>
							<p class="text">Client asking for an invoice ? Invoicing
								module is here to create hassle free invoices.</p>
							<br />
						</div>
					</div>
					<!-- single services -->
				</div>
			</div>
		</div>
		<!-- container -->
	</section>
	<!--====== SERVICES PART ENDS ======-->
	<!--====== ABOUT PART START ======-->
	<section id="about" class="about-area pt-70">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="about-content mt-50 wow fadeInLeftBig"
						data-wow-duration="1s" data-wow-delay="0.5s">
						<div class="section-title">
							<div class="line"></div>
							<h3 class="title">
								ABOUT US <span></span>
							</h3>
						</div>
						<!-- section title -->
						<p class="text">We aim to build an all in one software for
							businesses to help them smoothen their operations as well as
							provide them with the opportunity to build customer relationships
							as well as maintain client directory through our various features
							including CRM.</p>
					</div>
					<!-- about content -->
				</div>
				<div class="col-lg-6">
					<div class="about-image text-center mt-50 wow fadeInRightBig"
						data-wow-duration="1s" data-wow-delay="0.5s">
						<img src="assets/landing-page/images/about1.svg" alt="about">
					</div>
					<!-- about image -->
				</div>
			</div>
			<!-- row -->
		</div>
		<!-- container -->
		<div class="about-shape-1">
			<img src="assets/landing-page/images/about-shape-1.svg" alt="shape">
		</div>
	</section>
	<!--====== ABOUT PART ENDS ======-->
	<!--====== FOOTER PART START ======-->
	<footer id="footer" class="footer-area pt-120">
		<div class="container">
			<div class="footer-widget pb-100">
				<div class="row">
					<div class="col-lg-4 col-md-6 col-sm-8">
						<div class="footer-about mt-50 wow fadeIn" data-wow-duration="1s"
							data-wow-delay="0.2s">
							<ul class="social">
								<li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
								<li><a href="#"><i class="lni lni-twitter-filled"></i></a></li>
								<li><a href="#"><i class="lni lni-instagram-filled"></i></a></li>
								<li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
							</ul>
						</div>
						<!-- footer about -->
					</div>
					<div class="col-lg-5 col-md-7 col-sm-7">
						<div class="footer-link d-flex mt-50 justify-content-md-between">
							<!-- footer wrapper -->
							<div class="link-wrapper wow fadeIn" data-wow-duration="1s"
								data-wow-delay="0.6s">
								<div class="footer-title">
									<h4 class="title">Sales and support team</h4>
								</div>
								<ul class="link">
									<li>+91 8956613595</li>
									<li>+91 8956613596</li>
									<li><a href="mailto:support@operatein.com"
										class="py-2 d-block">support@operatein.com</a></li>
								</ul>
							</div>
							<!-- footer wrapper -->
						</div>
						<!-- footer link -->
					</div>
					<div class="col-lg-3 col-md-5 col-sm-5">
						<div class="footer-contact mt-50 wow fadeIn"
							data-wow-duration="1s" data-wow-delay="0.8s">
							<div class="footer-title">
								<h4 class="title">Address</h4>
							</div>
							<ul class="contact">
								<li>Mumbai</li>
							</ul>
						</div>
						<!-- footer contact -->
					</div>
				</div>
				<!-- row -->
				<div class="row">
					<div class="col-lg-4 col-md-6 col-sm-8"></div>
					<div class="col-lg-5 col-md-7 col-sm-7">
						<div style="position: absolute !important;" class="footer-link d-flex mt-50 justify-content-md-between">
							© Adnay. All Rights Reserved.Powered by &nbsp;<a style="color:white !important;" href="http://www.gabmor.com" target="_blank"> Gabmor</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- container -->
		<!-- <div id="particles-2"></div> -->
	</footer>
	<!--====== FOOTER PART ENDS ======-->
	<!--====== BACK TOP TOP PART START ======-->
	<a href="#" class="back-to-top"><i class="lni lni-chevron-up"></i></a>
	<!--====== BACK TOP TOP PART ENDS ======-->
	<!--====== PART START ======-->
	<!--
    <section class="">
        <div class="container">
            <div class="row">
                <div class="col-lg-"></div>
            </div>
        </div>
    </section>
-->
	<!--====== PART ENDS ======-->
	<!--====== Jquery js ======-->
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/jquery-3.5.1-min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/modernizr-3.7.1.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/popper.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/bootstrap-4.5.0.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/plugins.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/waypoints.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/jquery.counterup.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/jquery.easing.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/scrolling-nav.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/wow.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/particles.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/landing-page/js/main.js" />"></script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}";
		jQuery(document).ready(function() {
			if (localStorage.getItem('user') != null) {
				autoLoginUser(localStorage.getItem('user'));
			}
		});
	</script>
</body>
</html>