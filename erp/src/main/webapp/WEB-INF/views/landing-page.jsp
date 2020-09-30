<!DOCTYPE html>
<html lang="en">
<head>
<title>Grokar</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.googleapis.com/css?family=Nunito:300,400,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="assets/landing-page/fonts/icomoon/style.css">

<link rel="stylesheet" href="assets/landing-page/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/landing-page/css/jquery-ui.css">
<link rel="stylesheet"
	href="assets/landing-page/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="assets/landing-page/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="assets/landing-page/css/owl.theme.default.min.css">

<link rel="stylesheet"
	href="assets/landing-page/css/jquery.fancybox.min.css">

<link rel="stylesheet"
	href="assets/landing-page/css/bootstrap-datepicker.css">

<link rel="stylesheet"
	href="assets/landing-page/fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="assets/landing-page/css/aos.css">

<link rel="stylesheet" href="assets/landing-page/css/style.css">

<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favico.png">
</head>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">


	<div id="overlayer"></div>
	<div class="loader">
		<div class="spinner-border text-primary" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>

	<div class="site-wrap" id="home-section">

		<div class="site-mobile-menu site-navbar-target">
			<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close mt-3">
					<span class="icon-close2 js-menu-toggle"></span>
				</div>
			</div>
			<div class="site-mobile-menu-body"></div>
		</div>


		<header class="site-navbar py-4 js-sticky-header site-navbar-target"
			role="banner">

			<div class="container">
				<div class="row align-items-center">

					<div class="col-6 col-md-3 col-xl-4  d-block">
						<h1 class="mb-0 site-logo">
							<a href="#" class="text-black h2 mb-0">Grokar<span
								class="text-primary">.</span>
							</a>
						</h1>
					</div>

					<div class="col-12 col-md-9 col-xl-8 main-menu">
						<nav class="site-navigation position-relative text-right"
							role="navigation">

							<ul
								class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block ml-0 pl-0">
								<li><a href="#home-section" class="nav-link">Home</a></li>
								<li><a href="#features-section" class="nav-link">Features</a></li>
								<!--  <li class="has-children"> -->
								<li><a href="#about-section" class="nav-link">About Us</a>
									<!-- <ul class="dropdown arrow-top">
                    <li><a href="#" target="_blank" class="nav-link"><span class="text-primary">More Free Templates</span></a></li>
                    <li><a href="#our-team-section" class="nav-link">Our Team</a></li>
                    <li class="has-children">
                      <a href="#">More Links</a>
                      <ul class="dropdown">
                        <li><a href="#">Menu One</a></li>
                        <li><a href="#">Menu Two</a></li>
                        <li><a href="#">Menu Three</a></li>
                      </ul>
                    </li>
                  </ul> --></li>
								<li><a href="login" class="nav-link">Login</a></li>
								<li><a href="signup" class="nav-link">Sign Up</a></li>
							</ul>
						</nav>
					</div>


					<div class="col-6 col-md-9 d-inline-block d-lg-none ml-md-0">
						<a href="#"
							class="site-menu-toggle js-menu-toggle text-black float-right"><span
							class="icon-menu h3"></span></a>
					</div>

				</div>
			</div>
			<div id="successfullySignedUp" class="alert alert-success"
				style="width: 25%; position: fixed; top: 15%; left: 70%; display: none;">
				<strong id="signUpSuccessMessage"></strong>
			</div>
		</header>


		<div class="site-blocks-cover" style="overflow: hidden;">
			<div class="container">
				<div class="row align-items-center justify-content-center">

					<div class="col-md-12" style="position: relative;"
						data-aos="fade-up" data-aos-delay="200">

						<img src="assets/landing-page/images/undraw_investing_7u74.svg"
							alt="Image" class="img-fluid img-absolute">

						<div class="row mb-4" data-aos="fade-up" data-aos-delay="200">
							<div class="col-lg-6 mr-auto">
								<h1>
									A Holistic Management Software <br> & it's free
								</h1>
								<p class="mb-5">Let's get better at Managing your Business</p>
								<div>
									<a href="signup" class="btn btn-primary mr-2 mb-2">Get
										Started</a>
								</div>
							</div>


						</div>

					</div>
				</div>
			</div>
		</div>


		<div class="site-section" id="features-section">
			<div class="container">
				<div class="row mb-5 justify-content-center text-center"
					data-aos="fade-up">
					<div class="col-7 text-center  mb-5">
						<h2 class="section-title">Grokar Features</h2>
						<p class="lead">Below are some features that will help your
							business reach its true potential.</p>
					</div>
				</div>
				<div class="row align-items-stretch">
					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up">

						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-autorenew"></span></span>
							</div>
							<div>
								<h3>Marketing</h3>
								<p>Ability to send promotional messages there by reaching
									more and more customers.</p>
							</div>
						</div>

					</div>
					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
						data-aos-delay="100">

						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-store_mall_directory"></span></span>
							</div>
							<div>
								<h3>Interactive Dashboard</h3>
								<p>Forget Bookkeeping Graphs are here to help, providing
									insights about your business growth.</p>								
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
						data-aos-delay="200">
						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-shopping_basket"></span></span>
							</div>
							<div>
								<h3>Appointment Scheduler</h3>
								<p>Got Appointments? worry no more adding them just got
									easier, a button click away.</p>
							</div>
						</div>
					</div>


					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up">
						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-settings_backup_restore"></span></span>
							</div>
							<div>
								<h3>Calendar View </h3>
								<p>Calendar view available making it easier to check completed and upcoming appointments.</p>
							</div>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
						data-aos-delay="100">
						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-sentiment_satisfied"></span></span>
							</div>
							<div>
								<h3>24x7 support</h3>
								<p>All technical and functional queries addressed within 20
									hours by our experts.</p>
							</div>
						</div>


					</div>

					<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
						data-aos-delay="200">
						<div class="unit-4 d-block">
							<div class="unit-4-icon mb-3">
								<span class="icon-wrap"><span
									class="text-primary icon-power"></span></span>
							</div>
							<div>
								<h3>Inventory Management</h3>
								<p>Tired of managing inventory ? we've got you covered with
									our industry level Inventory management module</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>

		
		<div class="site-section bg-light" id="about-section">
			<div class="container">
				<div class="row mb-5">
					<div class="col-12 text-center">
						<h2 class="section-title mb-3">About Us</h2>
					</div>
				</div>
				<div class="row mb-5">
					<div class="col-lg-6" data-aos="fade-right">
						<img src="assets/landing-page/images/undraw_bookmarks_r6up.svg"
							alt="Image" class="img-fluid">
					</div>
					<div class="col-lg-5 ml-auto pl-lg-5">
						<h2 class="text-black mb-4 h3 font-weight-bold">Our Mission</h2>
						<p class="mb-4">We aim to build an all in one software for
							businesses to help them smoothen their operations as well as provide
							them with the opportunity to build customer relationships as well
							as maintain client directory through our various features
							including CRM.</p>
						<ul class="ul-check mb-5 list-unstyled success">
							<li>Manage Staff, Client, Inventory</li>
							<li>Send Promotional Messages</li>
							<li>End-to-end Solutions for your business needs</li>
						</ul>
						<p>
							<a href="signup" class="btn btn-primary">Learn More</a>
						</p>
					</div>
				</div>


			</div>
		</div>
		<div class="footer py-5 text-center">
			<div class="container">
				<div class="row mb-5">
					<div class="col-12">
						<p class="mb-0">
							<a href="#" class="p-3"><span class="icon-facebook"></span></a> <a
								href="#" class="p-3"><span class="icon-instagram"></span></a> <a
								href="#" class="p-3"><span class="icon-linkedin"></span></a>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<p class="mb-0">
							17 K.D.G.B.Niwas,J.S.S Road,Girgaon, Mumbai, 400004.
							support@operatein.com <br> +91 9321788145
						</p>
					</div>
					<div class="col-md-6">
						<p class="mb-0">
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							<!--   Copyright &copy;
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved | Grokar powered by -->
							Grokar powered by <a href="http://www.gabmor.com/"
								target="_blank">GABMOR</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- .site-wrap -->
	<script src="assets/landing-page/js/jquery-3.3.1.min.js"></script>
	<script src="assets/landing-page/js/jquery-ui.js"></script>
	<script src="assets/landing-page/js/popper.min.js"></script>
	<script src="assets/landing-page/js/bootstrap.min.js"></script>
	<script src="assets/landing-page/js/owl.carousel.min.js"></script>
	<script src="assets/landing-page/js/jquery.countdown.min.js"></script>	
	<script src="assets/landing-page/js/jquery.easing.1.3.js"></script>
	<script src="assets/landing-page/js/aos.js"></script>
	<script src="assets/landing-page/js/jquery.fancybox.min.js"></script>
	<script src="assets/landing-page/js/jquery.sticky.js"></script>
	<script type="text/javascript"
		src="<c:url value="assets/landing-page/js/main.js" />"></script>
</body>
</html>