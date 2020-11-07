<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>OperateIN | Sign Up</title>
</head>
<body class="login-bg">
	<nav class="navbar navbar-default" style="background-color: white;">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<img id="mobile_logon" style="height: 140%; display: none"
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
					<li><a href="login" style="font-weight: bold;"><span
							class="lni lni-user"></span> Login </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="loader" id="spinner" style="display: none;"></div>
	<div class="login-form">
		<form method="post" name="singUpForm" class="form text-left"
			action="signup" id="singUpForm">
			<!-- <div class="avatar">
				<i class="material-icons"></i>
			</div> -->
			<h4 class="modal-title">Sign Up to OperateIN</h4>
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
			<div>
				<p>
					By clicking Create Account, you agree to our <a
						style="cursor: hand;" data-toggle="modal"
						data-target="#termsAndConditionsModal">Terms of Service and
						Privacy Policy</a>.
				</p>
			</div>
			<input type="submit" id="signupBtn"
				class="btn btn-primary btn-block btn-lg" value="Create Account">
		</form>
		<div class="text-center small">
			have an account? <a href="login">Sign in</a>
		</div>
	</div>
	<div class="modal fade" id="termsAndConditionsModal"
		data-backdrop="static" tabindex="-1" role="dialog"
		aria-labelledby="staticBackdrop" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="termsAndConditionsModal">Terms &
						Conditions.</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<i aria-hidden="true" class="ki ki-close"></i>
					</button>
				</div>
				<div class="modal-body" style="overflow-y: scroll; height: 70%;">
					<p>
						<b>Use of Service</b><br />User Account In order to use the
						Service, each Member must create a User Account and shall agree to
						provide any information requested by OperateIn. <br />
						<b>Disclaimer of Liability</b><br /> All appointments are subject
						to availability of operators and service slots. In case of any
						issue, every attempt will be made to inform the Customer and our
						liability will be limited to rescheduling the appointment to the
						next convenient available time slot. By choosing to do a service
						at our Salon, you understand and agree that while our aim is to
						deliver a great service experience every time, in the rare event
						to the contrary, our liability will be limited to the value
						equivalent to the cost of the service. <br />
						<b>User Provided Information</b><br /> When you register with us
						and use the Application, you generally provide your;<br /> a)
						name, email, and other social media login information.<br /> b)
						Information you provide us when you contact us for help.<br /> c)
						transaction-related information, such as when you make
						appointments, respond to make any offers, or download or use
						application from us. <br /> d) Credit card information for
						purchase and use of the Application <br /> e) information you
						enter into our system when using the Application, such as contact
						information and project management information. The more
						information you provide, the better benefits you may derive from
						the services provided through this Application as it helps in
						defining your professional identity. It enables us to serve you
						relevant content, our suggested/ recommended services or offers as
						we deem fit. We may also use the information you provided us to
						contact you from time to time and to provide you with important
						information, required notices and marketing promotions or offers.
						We may also use your information to understand trends and
						statistics, conduct research surveys and analyses to evaluate the
						services or products you use or purchase through us; make any
						improvisations; develop new features and tools, for correcting
						technical glitches, or for audit purposes. When you provide us
						with your location information, we may use it to help you find a
						nearest salons around the area you have selected or are present. <br />
						<b>License grant</b><br /> By posting communications on or through
						this website or mobile application, the user shall be deemed to
						have granted to Operatein, a royalty-free, perpetual, irrevocable
						& non-exclusive license to use, reproduce, modify, publish, edit,
						translate, distribute, perform, and display the communication
						alone or as part of other works in any form, media, or technology
						whether now known or hereafter developed, and to sublicense such
						rights through multiple tiers of sublicenses. <br />
						<b>Privacy</b><br /> The user acknowledges that all discussion for
						ratings, comments, bulletin board service, chat rooms and/or other
						message or communication facilities (collectively "Communities")
						are public and not private communications, and that, therefore,
						others may read the user’s communications without the user’s
						knowledge. Operaetin does not control or endorse the content,
						messages or information found in any communities, and, therefore,
						Operaetin specifically disclaims any liability concerning the
						communities and any actions resulting from the user’s
						participation in any communities, including any objectionable
						content. Generally, any communication which the user posts on the
						website (whether in chat rooms, discussion groups, message boards
						or otherwise) is considered to be non-confidential. If particular
						web pages permit the submission of communications that will be
						treated by Operaetin as confidential, that fact will be stated on
						those pages. By posting comments, messages or other information on
						the website or mobile application, the user grants Operaetin.in
						the right to use such comments, messages or information for
						promotions, advertising, market research or any other lawful
						purpose. For more information see Operaetin privacy policy. <br />
						<b>Membership Card Policy</b><br /> Amount received against any
						service will not be refunded and cannot be redeemed against any
						other service. Deals once sold cannot be exchanged or returned. Be
						U Salons is not liable for any loss or damage of any personal
						belonging and reaction to skin or hair. Offers/ Packages need to
						be redeemed within stipulated duration of time or they hold expire
						by default. You will receive transaction related and promotional
						SMS. To unsubscribe or to file a complaint, please email us at
						Operaetin@gmail.com Rights of admission reserved. All disputes are
						subject to Mumbai Jurisdiction. <br />
						<b>Bill Policy</b><br /> Amount received against any services will
						not be refunded and cannot be redeemed against other service. Deal
						once sold cannot be exchanged or returned. Be U Salons is not
						liable for any loss or damage to any personal belonging and
						reaction to skin and hair. Offers/ Packages need to be redeemed
						within stipulated duration of time or they hold expire by default.
						You will receive transaction related and promotional SMS’es. To
						unsubscribe or to file a complaint, please email us at
						Operaetin@gmail.com. Rights of admission reserved. All disputes
						are subject to Mumbai Jurisdiction. <br />
						<b>Automatically Collected Information</b><br /> In addition, the
						Application may collect certain information automatically,
						including, but not limited to, the type of mobile device you use,
						your mobile devices unique device ID, your location and
						information about the way you use the Application. <br />
						<b>Advertisement</b><br /> Operaetin may place advertisements in
						different locations on the website and at different points during
						the use of the service. These locations and points may change from
						time to time - but we will always clearly mark which goods and
						services are advertisements (i.e. From persons other than us) so
						that it is clear to you which goods and services are provided on
						an objective basis and which are not (i.e. the advertisements).
						You are free to select or click on advertised goods and services
						or not as you see fit. Any advertisements may be delivered on our
						behalf by a third party advertiser. No personal data (for example
						your name, address, email address or telephone number) will be
						used during the course of serving our advertising, but, on our
						behalf, our third-party advertiser or affiliate may place or
						recognize a unique "cookie" on your browser (see our privacy
						policy about this). This cookie will not collect personal data
						about you nor is it linked to any personal data about you. If you
						would like more information about this practice and to know your
						choices about not having this information used by any company, see
						our privacy policy for more information. Disclosure by Third
						Parties From time to time, we may make certain third party offers
						available through our Application. If you choose to accept such an
						offer, we will disclose your contact and billing information to
						the third party who made the offer available to you. We may also
						disclose your personal data:- as requested by law, such as to
						comply with any legal, judicial, regulatory process; - when we
						believe in good faith that disclosure is necessary to protect our
						rights, protect our rights, protect your safety, or the safety of
						others, investigate fraud, or respond to a government. -if (Co
						Name) is involved in a merger, acquisition or sale of all or a
						portion of its assets, be notified via email and/ or a prominent
						notice on our Website and Application of any change in ownership
						or uses of this information, as well as any choices you may have
						regarding this information. Only aggregated, anonymized data is
						periodically transmitted to external services to help us improve
						the Application and our service. Opt Out Rights you can stop all
						collection of information by the Application easily by
						uninstalling the Application. You may use the standard uninstall
						process as may be available as part of your mobile device or via
						the mobile application marketplace or network. You can also
						request to opt-out via email by writing to us at
						support@Operaetin.com <br />
						<b>Variation of the Conditions, Site, Application and Service</b><br />
						Operaetin reserves the right to change, modify or amend any or all
						of the Conditions at any time without any prior notice. In
						addition, Operaetin may vary or amend the Services provided
						through the Site/ and or Application, functionality of the site
						and / or Application and/ or the "look and feel" of the Site and /
						or the Application at any time without notice and without any
						liability towards the registered Members on the Application. Any
						modification to the Site, Application, Services or Conditions
						shall come into effect as soon as such changes changes are
						published on the Site and / or the Application. Members shall be
						deemed to have accepted any varied Conditions in the event that
						they use any Services offered through the Site or the Application
						following publication of the varied Conditions. However, changes
						will not apply to any bookings which have been made prior to
						publication of the varied Conditions <br />
						<b>Security</b><br /> we are concerned about safeguarding
						confidentiality of certain personal information of yours, such as
						your payment instrument and bank details. We provide physical,
						electronic and procedural safeguards to protect information we
						process and maintain. Please be aware that, although we endeavor
						to provide reasonable security for information we process and
						maintain, no security system can prevent all potential security
						breached. <br />
						<b>Products/services takedown policy: reporting a violation of
							infringement</b><br /> We want to list the products and services of
						genuine sellers on our website or mobile application in order to
						ensure that infringing products are removed from the site, as they
						affect your and genuine sellers valuable trust. We do not and
						cannot verify if the sellers have the right or ability to sell or
						distribute their listed products or services. However, we are
						committed to removing any infringing or unlicensed product or
						service once an authorized representative of the rights owner
						properly reports them to us. We sincerely want to ensure that item
						listings do not infringe upon the copyright, trademark or other
						intellectual property rights of third parties. We have the ability
						to identify and request the removal of allegedly infringing
						products and materials. Any person or company who holds
						intellectual property rights (such as a copyright, trademark or
						patent) which may be infringed upon by-products listed on
						Operaetin is encouraged to report the same to the Grievance
						Officer. <br />
						<b>Amendments</b><br /> This Privacy Policy may be updated from
						time to time for any reason. We will notify you of any changes to
						our Privacy Policy by posting the new Privacy Policy here and
						informing you via the Application, email or text message. You are
						advised to consult this Privacy Policy regularly for any changes,
						as continued use is deemed approval of all changes. <br />
						<b>Your Consent</b><br /> By using this Application, you are
						consenting to our processing of your information as set forth in
						this Privacy Policy now and as amended by us. "Processing" means
						using cookies on a computer/hand held device or using touching
						information in any way, including, but not limited to, collecting,
						storing, deleting, using, combining and disclosing information. <br />
						<b>CONTACT US</b><br /> In accordance with Information Technology
						Act 2000 and rules made thereunder, if you have any questions
						regarding our Privacy Policy or our Terms of Sale or Terms of Use
						of the site, you may contact our Grievance Officer at the details
						mentioned below: Email: support@Operaetin.com
					</p>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
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
		$('#signupBtn').click(function() {
			//$('#spinner').show();
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