<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<base href="../../../">
<meta charset="utf-8" />
<meta name="description" content="Updates and statistics" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!--begin::Fonts-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Global Theme Styles(used by all pages)-->
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/plugins/global/plugins.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/plugins/custom/prismjs/prismjs.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/style.bundle.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<!--end::Global Theme Styles-->
<!--begin::Layout Themes(used by all pages)-->
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/header/base/light.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/header/menu/light.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/brand/dark.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}<c:url value="/assets/css/themes/layout/aside/dark.css?v=7.0.5"/>"
	rel="stylesheet" type="text/css" />
<!--end::Layout Themes-->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/media/logos/favicon.jpeg" />
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_body"
	class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed aside-minimize-hoverable page-loading">
	<!--begin::Main-->
	<div class="spinner spinner-primary spinner-lg mr-15"
		id="loading-spinner"
		style="position: absolute; margin-left: 50%; top: 50%; z-index: 1000;"></div>
	<!--begin::Header Mobile-->
	<div id="kt_header_mobile"
		class="header-mobile align-items-center header-mobile-fixed">
		<!--begin::Logo-->
		<span style = "color: white;font-size: 23px;" class="brand-logo">Grokar</span>
		<!--end::Logo-->
		<!--begin::Toolbar-->
		<div class="d-flex align-items-center">
			<!--begin::Aside Mobile Toggle-->
			<button class="btn p-0 burger-icon burger-icon-left"
				id="kt_aside_mobile_toggle">
				<span></span>
			</button>
			<!--end::Aside Mobile Toggle-->
			<!--begin::Header Menu Mobile Toggle-->
			<!-- <button class="btn p-0 burger-icon ml-4" id="kt_header_mobile_toggle">
				<span></span>
			</button> -->
			<!--end::Header Menu Mobile Toggle-->
			<!--begin::Topbar Mobile Toggle-->
			<button class="btn btn-hover-text-primary p-0 ml-2"
				id="kt_header_mobile_topbar_toggle">
				<span class="svg-icon svg-icon-xl"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/General/User.svg-->
					<svg xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
						height="24px" viewBox="0 0 24 24" version="1.1">
							<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
								<polygon points="0 0 24 0 24 24 0 24" />
								<path
							d="M12,11 C9.790861,11 8,9.209139 8,7 C8,4.790861 9.790861,3 12,3 C14.209139,3 16,4.790861 16,7 C16,9.209139 14.209139,11 12,11 Z"
							fill="#000000" fill-rule="nonzero" opacity="0.3" />
								<path
							d="M3.00065168,20.1992055 C3.38825852,15.4265159 7.26191235,13 11.9833413,13 C16.7712164,13 20.7048837,15.2931929 20.9979143,20.2 C21.0095879,20.3954741 20.9979143,21 20.2466999,21 C16.541124,21 11.0347247,21 3.72750223,21 C3.47671215,21 2.97953825,20.45918 3.00065168,20.1992055 Z"
							fill="#000000" fill-rule="nonzero" />
							</g>
						</svg> <!--end::Svg Icon-->
				</span>
			</button>
			<!--end::Topbar Mobile Toggle-->
		</div>
		<!--end::Toolbar-->
	</div>
	<!--end::Header Mobile-->
	<div class="d-flex flex-column flex-root">
		<!--begin::Page-->
		<div class="d-flex flex-row flex-column-fluid page">
			<!--begin::Aside-->
			<div
				class="aside aside-left aside-fixed d-flex flex-column flex-row-auto"
				id="kt_aside">
				<!--begin::Brand-->
				<div class="brand flex-column-auto" id="kt_brand">
					<!--begin::Logo-->
					<a href="dashboard" style = "color: white;font-size: 23px;" class="brand-logo">Grokar</a>
					<!--end::Logo-->
					<!--begin::Toggle-->
					<button class="brand-toggle btn btn-sm px-0" id="kt_aside_toggle">
						<span class="svg-icon svg-icon svg-icon-xl"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Navigation/Angle-double-left.svg-->
							<svg xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
								height="24px" viewBox="0 0 24 24" version="1.1">
									<g stroke="none" stroke-width="1" fill="none"
									fill-rule="evenodd">
										<polygon points="0 0 24 0 24 24 0 24" />
										<path
									d="M5.29288961,6.70710318 C4.90236532,6.31657888 4.90236532,5.68341391 5.29288961,5.29288961 C5.68341391,4.90236532 6.31657888,4.90236532 6.70710318,5.29288961 L12.7071032,11.2928896 C13.0856821,11.6714686 13.0989277,12.281055 12.7371505,12.675721 L7.23715054,18.675721 C6.86395813,19.08284 6.23139076,19.1103429 5.82427177,18.7371505 C5.41715278,18.3639581 5.38964985,17.7313908 5.76284226,17.3242718 L10.6158586,12.0300721 L5.29288961,6.70710318 Z"
									fill="#000000" fill-rule="nonzero"
									transform="translate(8.999997, 11.999999) scale(-1, 1) translate(-8.999997, -11.999999)" />
										<path
									d="M10.7071009,15.7071068 C10.3165766,16.0976311 9.68341162,16.0976311 9.29288733,15.7071068 C8.90236304,15.3165825 8.90236304,14.6834175 9.29288733,14.2928932 L15.2928873,8.29289322 C15.6714663,7.91431428 16.2810527,7.90106866 16.6757187,8.26284586 L22.6757187,13.7628459 C23.0828377,14.1360383 23.1103407,14.7686056 22.7371482,15.1757246 C22.3639558,15.5828436 21.7313885,15.6103465 21.3242695,15.2371541 L16.0300699,10.3841378 L10.7071009,15.7071068 Z"
									fill="#000000" fill-rule="nonzero" opacity="0.3"
									transform="translate(15.999997, 11.999999) scale(-1, 1) rotate(-270.000000) translate(-15.999997, -11.999999)" />
									</g>
								</svg> <!--end::Svg Icon-->
						</span>
					</button>
					<!--end::Toolbar-->
				</div>
				<!--end::Brand-->
				<!--begin::Aside Menu-->
				<div class="aside-menu-wrapper flex-column-fluid"
					id="kt_aside_menu_wrapper">
					<!--begin::Menu Container-->
					<div id="kt_aside_menu" class="aside-menu my-4"
						data-menu-vertical="1" data-menu-scroll="1"
						data-menu-dropdown-timeout="500">
						<!--begin::Menu Nav-->
						<ul class="menu-nav" id="side-menu">
							<sec:authorize access="hasAnyAuthority('Admin')">
								<li class="menu-item menu-item-submenu" id="register_nav"
									aria-haspopup="true" data-menu-toggle="hover"><a
									href="javascript:;" class="menu-link menu-toggle"> <span
										class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Files/Folder-solid.svg-->
											<svg xmlns="http://www.w3.org/2000/svg"
												xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
												height="24px" viewBox="0 0 24 24" version="1.1">
												<g stroke="none" stroke-width="1" fill="none"
													fill-rule="evenodd">
													<polygon points="0 0 24 0 24 24 0 24" />
													<path
													d="M12.9336061,16.072447 L19.36,10.9564761 L19.5181585,10.8312381 C20.1676248,10.3169571 20.2772143,9.3735535 19.7629333,8.72408713 C19.6917232,8.63415859 19.6104327,8.55269514 19.5206557,8.48129411 L12.9336854,3.24257445 C12.3871201,2.80788259 11.6128799,2.80788259 11.0663146,3.24257445 L4.47482784,8.48488609 C3.82645598,9.00054628 3.71887192,9.94418071 4.23453211,10.5925526 C4.30500305,10.6811601 4.38527899,10.7615046 4.47382636,10.8320511 L4.63,10.9564761 L11.0659024,16.0730648 C11.6126744,16.5077525 12.3871218,16.5074963 12.9336061,16.072447 Z"
													fill="#000000" fill-rule="nonzero" />
													<path
													d="M11.0563554,18.6706981 L5.33593024,14.122919 C4.94553994,13.8125559 4.37746707,13.8774308 4.06710397,14.2678211 C4.06471678,14.2708238 4.06234874,14.2738418 4.06,14.2768747 L4.06,14.2768747 C3.75257288,14.6738539 3.82516916,15.244888 4.22214834,15.5523151 C4.22358765,15.5534297 4.2250303,15.55454 4.22647627,15.555646 L11.0872776,20.8031356 C11.6250734,21.2144692 12.371757,21.2145375 12.909628,20.8033023 L19.7677785,15.559828 C20.1693192,15.2528257 20.2459576,14.6784381 19.9389553,14.2768974 C19.9376429,14.2751809 19.9363245,14.2734691 19.935,14.2717619 L19.935,14.2717619 C19.6266937,13.8743807 19.0546209,13.8021712 18.6572397,14.1104775 C18.654352,14.112718 18.6514778,14.1149757 18.6486172,14.1172508 L12.9235044,18.6705218 C12.377022,19.1051477 11.6029199,19.1052208 11.0563554,18.6706981 Z"
													fill="#000000" opacity="0.3" />
												</g>
											</svg>
									</span> <span class="menu-text">Register</span> <i class="menu-arrow"></i>
								</a>
									<div class="menu-submenu">
										<i class="menu-arrow"></i>
										<ul class="menu-subnav">
											<li class="menu-item menu-item-parent" aria-haspopup="true">
												<span class="menu-link"> <span class="menu-text">Register</span>
											</span>
											</li>
											<li class="menu-item menu-item-submenu" id="register_member_nav"
												aria-haspopup="true" data-menu-toggle="hover"><a
												href="register/member" class="menu-link menu-toggle"> <i
													class="menu-bullet menu-bullet-dot"> <span></span>
												</i> <span class="menu-text">Member</span>
											</a></li>
											<li class="menu-item menu-item-submenu" id="register_organization_nav"
												aria-haspopup="true" data-menu-toggle="hover"><a
												href="register/organization" class="menu-link menu-toggle">
													<i class="menu-bullet menu-bullet-dot"> <span></span>
												</i> <span class="menu-text">Organization</span>
											</a></li>
										</ul>
									</div></li>
							</sec:authorize>
							<li id="dashboard" class="menu-item menu-item-active"
								aria-haspopup="true"><a href="dashboard" class="menu-link">
									<span class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Home/Home.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M3.95709826,8.41510662 L11.47855,3.81866389 C11.7986624,3.62303967 12.2013376,3.62303967 12.52145,3.81866389 L20.0429,8.41510557 C20.6374094,8.77841684 21,9.42493654 21,10.1216692 L21,19.0000642 C21,20.1046337 20.1045695,21.0000642 19,21.0000642 L4.99998155,21.0000673 C3.89541205,21.0000673 2.99998155,20.1046368 2.99998155,19.0000673 L2.99999828,10.1216672 C2.99999935,9.42493561 3.36258984,8.77841732 3.95709826,8.41510662 Z M10,13 C9.44771525,13 9,13.4477153 9,14 L9,17 C9,17.5522847 9.44771525,18 10,18 L14,18 C14.5522847,18 15,17.5522847 15,17 L15,14 C15,13.4477153 14.5522847,13 14,13 L10,13 Z"
												fill="#000000" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Dashboard</span>
							</a></li>
							<li class="menu-item" id="appointment_nav" aria-haspopup="true"><a
								href="appointment" class="menu-link"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Layout/Layout-left-panel-2.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M10,4 L21,4 C21.5522847,4 22,4.44771525 22,5 L22,7 C22,7.55228475 21.5522847,8 21,8 L10,8 C9.44771525,8 9,7.55228475 9,7 L9,5 C9,4.44771525 9.44771525,4 10,4 Z M10,10 L21,10 C21.5522847,10 22,10.4477153 22,11 L22,13 C22,13.5522847 21.5522847,14 21,14 L10,14 C9.44771525,14 9,13.5522847 9,13 L9,11 C9,10.4477153 9.44771525,10 10,10 Z M10,16 L21,16 C21.5522847,16 22,16.4477153 22,17 L22,19 C22,19.5522847 21.5522847,20 21,20 L10,20 C9.44771525,20 9,19.5522847 9,19 L9,17 C9,16.4477153 9.44771525,16 10,16 Z"
												fill="#000000" />
        <rect fill="#000000" opacity="0.3" x="2" y="4" width="5"
												height="16" rx="1" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Appointments</span>
							</a></li>
							<li class="menu-item" id="schedule_nav" aria-haspopup="true"><a
								href="schedule" class="menu-link"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Home/Mailbox.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M8,6 L20.5,6 C21.3284271,6 22,6.67157288 22,7.5 C22,8.32842712 21.3284271,9 20.5,9 L8,9 L8,19.5 C8,20.3284271 7.32842712,21 6.5,21 C5.67157288,21 5,20.3284271 5,19.5 L5,9 L3.5,9 C2.67157288,9 2,8.32842712 2,7.5 C2,6.67157288 2.67157288,6 3.5,6 L5,6 L5,4.5 C5,3.67157288 5.67157288,3 6.5,3 C7.32842712,3 8,3.67157288 8,4.5 L8,6 Z"
												fill="#000000" />
        <path
												d="M10,11 L20.5,11 C21.3284271,11 22,11.6715729 22,12.5 L22,15 C22,17.209139 20.209139,19 18,19 L11.5,19 C10.6715729,19 10,18.3284271 10,17.5 L10,11 Z M20,12 C19.4477153,12 19,12.4477153 19,13 L19,16 C19,16.5522847 19.4477153,17 20,17 C20.5522847,17 21,16.5522847 21,16 L21,13 C21,12.4477153 20.5522847,12 20,12 Z"
												fill="#000000" opacity="0.3" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Schedule</span>
							</a></li>
							<li class="menu-item" id="client_nav" aria-haspopup="true"><a
								href="client" class="menu-link"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Communication/Contact1.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <circle fill="#000000" opacity="0.3" cx="12" cy="12" r="10" />
        <path
												d="M12,11 C10.8954305,11 10,10.1045695 10,9 C10,7.8954305 10.8954305,7 12,7 C13.1045695,7 14,7.8954305 14,9 C14,10.1045695 13.1045695,11 12,11 Z M7.00036205,16.4995035 C7.21569918,13.5165724 9.36772908,12 11.9907452,12 C14.6506758,12 16.8360465,13.4332455 16.9988413,16.5 C17.0053266,16.6221713 16.9988413,17 16.5815,17 L7.4041679,17 C7.26484009,17 6.98863236,16.6619875 7.00036205,16.4995035 Z"
												fill="#000000" opacity="0.3" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Clients</span>
							</a></li>
							<li class="menu-item" id="staff_nav" aria-haspopup="true"><a
								href="staff" class="menu-link"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Communication/Adress-book2.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M18,2 L20,2 C21.6568542,2 23,3.34314575 23,5 L23,19 C23,20.6568542 21.6568542,22 20,22 L18,22 L18,2 Z"
												fill="#000000" opacity="0.3" />
        <path
												d="M5,2 L17,2 C18.6568542,2 20,3.34314575 20,5 L20,19 C20,20.6568542 18.6568542,22 17,22 L5,22 C4.44771525,22 4,21.5522847 4,21 L4,3 C4,2.44771525 4.44771525,2 5,2 Z M12,11 C13.1045695,11 14,10.1045695 14,9 C14,7.8954305 13.1045695,7 12,7 C10.8954305,7 10,7.8954305 10,9 C10,10.1045695 10.8954305,11 12,11 Z M7.00036205,16.4995035 C6.98863236,16.6619875 7.26484009,17 7.4041679,17 C11.463736,17 14.5228466,17 16.5815,17 C16.9988413,17 17.0053266,16.6221713 16.9988413,16.5 C16.8360465,13.4332455 14.6506758,12 11.9907452,12 C9.36772908,12 7.21569918,13.5165724 7.00036205,16.4995035 Z"
												fill="#000000" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Staff</span>
							</a></li>
							<li class="menu-item" aria-haspopup="true" id="services_nav"><a
								href="services" class="menu-link"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Devices/Server.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M5,2 L19,2 C20.1045695,2 21,2.8954305 21,4 L21,6 C21,7.1045695 20.1045695,8 19,8 L5,8 C3.8954305,8 3,7.1045695 3,6 L3,4 C3,2.8954305 3.8954305,2 5,2 Z M11,4 C10.4477153,4 10,4.44771525 10,5 C10,5.55228475 10.4477153,6 11,6 L16,6 C16.5522847,6 17,5.55228475 17,5 C17,4.44771525 16.5522847,4 16,4 L11,4 Z M7,6 C7.55228475,6 8,5.55228475 8,5 C8,4.44771525 7.55228475,4 7,4 C6.44771525,4 6,4.44771525 6,5 C6,5.55228475 6.44771525,6 7,6 Z"
												fill="#000000" opacity="0.3" />
        <path
												d="M5,9 L19,9 C20.1045695,9 21,9.8954305 21,11 L21,13 C21,14.1045695 20.1045695,15 19,15 L5,15 C3.8954305,15 3,14.1045695 3,13 L3,11 C3,9.8954305 3.8954305,9 5,9 Z M11,11 C10.4477153,11 10,11.4477153 10,12 C10,12.5522847 10.4477153,13 11,13 L16,13 C16.5522847,13 17,12.5522847 17,12 C17,11.4477153 16.5522847,11 16,11 L11,11 Z M7,13 C7.55228475,13 8,12.5522847 8,12 C8,11.4477153 7.55228475,11 7,11 C6.44771525,11 6,11.4477153 6,12 C6,12.5522847 6.44771525,13 7,13 Z"
												fill="#000000" />
        <path
												d="M5,16 L19,16 C20.1045695,16 21,16.8954305 21,18 L21,20 C21,21.1045695 20.1045695,22 19,22 L5,22 C3.8954305,22 3,21.1045695 3,20 L3,18 C3,16.8954305 3.8954305,16 5,16 Z M11,18 C10.4477153,18 10,18.4477153 10,19 C10,19.5522847 10.4477153,20 11,20 L16,20 C16.5522847,20 17,19.5522847 17,19 C17,18.4477153 16.5522847,18 16,18 L11,18 Z M7,20 C7.55228475,20 8,19.5522847 8,19 C8,18.4477153 7.55228475,18 7,18 C6.44771525,18 6,18.4477153 6,19 C6,19.5522847 6.44771525,20 7,20 Z"
												fill="#000000" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Services</span>
							</a></li>
							<li class="menu-item menu-item-submenu" id="inventory_nav"
								aria-haspopup="true" data-menu-toggle="hover"><a
								href="javascript:;" class="menu-link menu-toggle"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:C:\wamp64\www\keenthemes\themes\metronic\theme\html\demo1\dist/../src/media/svg/icons\Shopping\Safe.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M6.5,16 L7.5,16 C8.32842712,16 9,16.6715729 9,17.5 L9,19.5 C9,20.3284271 8.32842712,21 7.5,21 L6.5,21 C5.67157288,21 5,20.3284271 5,19.5 L5,17.5 C5,16.6715729 5.67157288,16 6.5,16 Z M16.5,16 L17.5,16 C18.3284271,16 19,16.6715729 19,17.5 L19,19.5 C19,20.3284271 18.3284271,21 17.5,21 L16.5,21 C15.6715729,21 15,20.3284271 15,19.5 L15,17.5 C15,16.6715729 15.6715729,16 16.5,16 Z"
												fill="#000000" opacity="0.3" />
        <path
												d="M5,4 L19,4 C20.1045695,4 21,4.8954305 21,6 L21,17 C21,18.1045695 20.1045695,19 19,19 L5,19 C3.8954305,19 3,18.1045695 3,17 L3,6 C3,4.8954305 3.8954305,4 5,4 Z M15.5,15 C17.4329966,15 19,13.4329966 19,11.5 C19,9.56700338 17.4329966,8 15.5,8 C13.5670034,8 12,9.56700338 12,11.5 C12,13.4329966 13.5670034,15 15.5,15 Z M15.5,13 C16.3284271,13 17,12.3284271 17,11.5 C17,10.6715729 16.3284271,10 15.5,10 C14.6715729,10 14,10.6715729 14,11.5 C14,12.3284271 14.6715729,13 15.5,13 Z M7,8 L7,8 C7.55228475,8 8,8.44771525 8,9 L8,11 C8,11.5522847 7.55228475,12 7,12 L7,12 C6.44771525,12 6,11.5522847 6,11 L6,9 C6,8.44771525 6.44771525,8 7,8 Z"
												fill="#000000" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Inventory</span> <i class="menu-arrow"></i>
							</a>
								<div class="menu-submenu">
									<i class="menu-arrow"></i>
									<ul class="menu-subnav">
										<li class="menu-item menu-item-parent" aria-haspopup="true">
											<span class="menu-link"> <span class="menu-text">Inventory</span>
										</span>
										</li>
										<li class="menu-item menu-item-submenu" id="products_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="inventory/products" class="menu-link menu-toggle">
												<i class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">Products</span>
										</a></li>
										<li class="menu-item menu-item-submenu" id="stock_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="inventory/stock" class="menu-link menu-toggle"> <i
												class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">Stock</span>
										</a></li>
									</ul>
								</div></li>
							<li class="menu-item menu-item-submenu" id="buy_nav"
								aria-haspopup="true" data-menu-toggle="hover"><a
								href="javascript:;" class="menu-link menu-toggle"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:C:\wamp64\www\keenthemes\themes\metronic\theme\html\demo1\dist/../src/media/svg/icons\Shopping\Wallet2.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <rect fill="#000000" opacity="0.3" x="2" y="2" width="10"
												height="12" rx="2" />
        <path
												d="M4,6 L20,6 C21.1045695,6 22,6.8954305 22,8 L22,20 C22,21.1045695 21.1045695,22 20,22 L4,22 C2.8954305,22 2,21.1045695 2,20 L2,8 C2,6.8954305 2.8954305,6 4,6 Z M18,16 C19.1045695,16 20,15.1045695 20,14 C20,12.8954305 19.1045695,12 18,12 C16.8954305,12 16,12.8954305 16,14 C16,15.1045695 16.8954305,16 18,16 Z"
												fill="#000000" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Buy</span> <i class="menu-arrow"></i>
							</a>
								<div class="menu-submenu">
									<i class="menu-arrow"></i>
									<ul class="menu-subnav">
										<li id="buy_nav" class="menu-item menu-item-parent"
											aria-haspopup="true"><span class="menu-link"> <span
												class="menu-text">Buy</span>
										</span></li>
										<li class="menu-item menu-item-submenu" id="supplier_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="buy/addSupplier" class="menu-link menu-toggle"> <i
												class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">Supplier</span>
										</a></li>
										<li class="menu-item menu-item-submenu" id="newOrder_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="buy/newOrder" class="menu-link menu-toggle"> <i
												class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">New Order</span>
										</a></li>
									</ul>
								</div></li>
							<li class="menu-item menu-item-submenu" id="sell_nav"
								aria-haspopup="true" data-menu-toggle="hover"><a
								href="sell/sales" class="menu-link menu-toggle"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:C:\wamp64\www\keenthemes\themes\metronic\theme\html\demo1\dist/../src/media/svg/icons\Shopping\Sale2.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <polygon fill="#000000" opacity="0.3"
												points="12 20.0218549 8.47346039 21.7286168 6.86905972 18.1543453 3.07048824 17.1949849 4.13894342 13.4256452 1.84573388 10.2490577 5.08710286 8.04836581 5.3722735 4.14091196 9.2698837 4.53859595 12 1.72861679 14.7301163 4.53859595 18.6277265 4.14091196 18.9128971 8.04836581 22.1542661 10.2490577 19.8610566 13.4256452 20.9295118 17.1949849 17.1309403 18.1543453 15.5265396 21.7286168" />
        <polygon fill="#000000"
												points="14.0890818 8.60255815 8.36079737 14.7014391 9.70868621 16.049328 15.4369707 9.950447" />
        <path
												d="M10.8543431,9.1753866 C10.8543431,10.1252593 10.085524,10.8938719 9.13585777,10.8938719 C8.18793881,10.8938719 7.41737243,10.1252593 7.41737243,9.1753866 C7.41737243,8.22551387 8.18793881,7.45690126 9.13585777,7.45690126 C10.085524,7.45690126 10.8543431,8.22551387 10.8543431,9.1753866"
												fill="#000000" opacity="0.3" />
        <path
												d="M14.8641422,16.6221564 C13.9162233,16.6221564 13.1456569,15.8535438 13.1456569,14.9036711 C13.1456569,13.9520555 13.9162233,13.1851857 14.8641422,13.1851857 C15.8138085,13.1851857 16.5826276,13.9520555 16.5826276,14.9036711 C16.5826276,15.8535438 15.8138085,16.6221564 14.8641422,16.6221564 Z"
												fill="#000000" opacity="0.3" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Sale</span>
							</a></li>
							<li class="menu-item" id="profile-creation_nav"
								aria-haspopup="true"><a href="profileCreation"
								class="menu-link"> <span class="svg-icon menu-icon">
										<!--begin::Svg Icon | path:/home/keenthemes/www/metronic/themes/metronic/theme/html/demo1/dist/../src/media/svg/icons/Communication/Group.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <polygon points="0 0 24 0 24 24 0 24" />
        <path
												d="M18,14 C16.3431458,14 15,12.6568542 15,11 C15,9.34314575 16.3431458,8 18,8 C19.6568542,8 21,9.34314575 21,11 C21,12.6568542 19.6568542,14 18,14 Z M9,11 C6.790861,11 5,9.209139 5,7 C5,4.790861 6.790861,3 9,3 C11.209139,3 13,4.790861 13,7 C13,9.209139 11.209139,11 9,11 Z"
												fill="#000000" fill-rule="nonzero" opacity="0.3" />
        <path
												d="M17.6011961,15.0006174 C21.0077043,15.0378534 23.7891749,16.7601418 23.9984937,20.4 C24.0069246,20.5466056 23.9984937,21 23.4559499,21 L19.6,21 C19.6,18.7490654 18.8562935,16.6718327 17.6011961,15.0006174 Z M0.00065168429,20.1992055 C0.388258525,15.4265159 4.26191235,13 8.98334134,13 C13.7712164,13 17.7048837,15.2931929 17.9979143,20.2 C18.0095879,20.3954741 17.9979143,21 17.2466999,21 C13.541124,21 8.03472472,21 0.727502227,21 C0.476712155,21 -0.0204617505,20.45918 0.00065168429,20.1992055 Z"
												fill="#000000" fill-rule="nonzero" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Profile</span>
							</a></li>
							<li class="menu-item menu-item-submenu" id="bill_nav"
								aria-haspopup="true" data-menu-toggle="hover"><a
								href="javascript:;" class="menu-link menu-toggle"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:C:\wamp64\www\keenthemes\themes\metronic\theme\html\demo1\dist/../src/media/svg/icons\Communication\Clipboard-list.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M8,3 L8,3.5 C8,4.32842712 8.67157288,5 9.5,5 L14.5,5 C15.3284271,5 16,4.32842712 16,3.5 L16,3 L18,3 C19.1045695,3 20,3.8954305 20,5 L20,21 C20,22.1045695 19.1045695,23 18,23 L6,23 C4.8954305,23 4,22.1045695 4,21 L4,5 C4,3.8954305 4.8954305,3 6,3 L8,3 Z"
												fill="#000000" opacity="0.3" />
        <path
												d="M11,2 C11,1.44771525 11.4477153,1 12,1 C12.5522847,1 13,1.44771525 13,2 L14.5,2 C14.7761424,2 15,2.22385763 15,2.5 L15,3.5 C15,3.77614237 14.7761424,4 14.5,4 L9.5,4 C9.22385763,4 9,3.77614237 9,3.5 L9,2.5 C9,2.22385763 9.22385763,2 9.5,2 L11,2 Z"
												fill="#000000" />
        <rect fill="#000000" opacity="0.3" x="10" y="9" width="7"
												height="2" rx="1" />
        <rect fill="#000000" opacity="0.3" x="7" y="9" width="2"
												height="2" rx="1" />
        <rect fill="#000000" opacity="0.3" x="7" y="13" width="2"
												height="2" rx="1" />
        <rect fill="#000000" opacity="0.3" x="10" y="13" width="7"
												height="2" rx="1" />
        <rect fill="#000000" opacity="0.3" x="7" y="17" width="2"
												height="2" rx="1" />
        <rect fill="#000000" opacity="0.3" x="10" y="17" width="7"
												height="2" rx="1" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Billing</span> <i class="menu-arrow"></i>
							</a>
								<div class="menu-submenu">
									<i class="menu-arrow"></i>
									<ul class="menu-subnav">
										<li id="bill_nav" class="menu-item menu-item-parent"
											aria-haspopup="true"><span class="menu-link"> <span
												class="menu-text">Billing</span>
										</span></li>
										<li class="menu-item menu-item-submenu"
											id="bill_appointment_nav" aria-haspopup="true"
											data-menu-toggle="hover"><a
											href="appointment/showAppointmentInvoices"
											class="menu-link menu-toggle"> <i
												class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">Appointments</span>
										</a></li>
										<li class="menu-item menu-item-submenu" id="bill_sale_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="sell/showSaleInvoices" class="menu-link menu-toggle">
												<i class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">Sales</span>
										</a></li>
									</ul>
								</div></li>
							<li class="menu-item menu-item-submenu" id="crm_nav"
								aria-haspopup="true" data-menu-toggle="hover"><a
								href="javascript:;" class="menu-link menu-toggle"> <span
									class="svg-icon menu-icon"> <!--begin::Svg Icon | path:C:\wamp64\www\keenthemes\themes\metronic\theme\html\demo1\dist/../src/media/svg/icons\Design\Target.svg-->
										<svg xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
											height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <path
												d="M19,11 L21,11 C21.5522847,11 22,11.4477153 22,12 C22,12.5522847 21.5522847,13 21,13 L19,13 C18.4477153,13 18,12.5522847 18,12 C18,11.4477153 18.4477153,11 19,11 Z M3,11 L5,11 C5.55228475,11 6,11.4477153 6,12 C6,12.5522847 5.55228475,13 5,13 L3,13 C2.44771525,13 2,12.5522847 2,12 C2,11.4477153 2.44771525,11 3,11 Z M12,2 C12.5522847,2 13,2.44771525 13,3 L13,5 C13,5.55228475 12.5522847,6 12,6 C11.4477153,6 11,5.55228475 11,5 L11,3 C11,2.44771525 11.4477153,2 12,2 Z M12,18 C12.5522847,18 13,18.4477153 13,19 L13,21 C13,21.5522847 12.5522847,22 12,22 C11.4477153,22 11,21.5522847 11,21 L11,19 C11,18.4477153 11.4477153,18 12,18 Z"
												fill="#000000" fill-rule="nonzero" opacity="0.3" />
        <circle fill="#000000" cx="12" cy="12" r="3" />
    </g>
</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">CRM</span> <i class="menu-arrow"></i>
							</a>
								<div class="menu-submenu">
									<i class="menu-arrow"></i>
									<ul class="menu-subnav">
										<li id="crm_nav" class="menu-item menu-item-parent"
											aria-haspopup="true"><span class="menu-link"> <span
												class="menu-text">CRM</span>
										</span></li>
										<li class="menu-item menu-item-submenu" id="sms_nav"
											aria-haspopup="true" data-menu-toggle="hover"><a
											href="crm/sms" class="menu-link menu-toggle"> <i
												class="menu-bullet menu-bullet-dot"> <span></span>
											</i> <span class="menu-text">SMS</span>
										</a></li>
									</ul>
								</div></li>
						</ul>
						<!--end::Menu Nav-->
					</div>
					<!--end::Menu Container-->
				</div>
				<!--end::Aside Menu-->
			</div>
			<!--end::Aside-->
		</div>
		<!--end::Page-->
	</div>
	<!--end::Main-->
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<!--begin::Global Config(global config for global JS scripts)-->
	<script>
		var KTAppSettings = {
			"breakpoints" : {
				"sm" : 576,
				"md" : 768,
				"lg" : 992,
				"xl" : 1200,
				"xxl" : 1200
			},
			"colors" : {
				"theme" : {
					"base" : {
						"white" : "#ffffff",
						"primary" : "#3699FF",
						"secondary" : "#E5EAEE",
						"success" : "#1BC5BD",
						"info" : "#8950FC",
						"warning" : "#FFA800",
						"danger" : "#F64E60",
						"light" : "#F3F6F9",
						"dark" : "#212121"
					},
					"light" : {
						"white" : "#ffffff",
						"primary" : "#E1F0FF",
						"secondary" : "#ECF0F3",
						"success" : "#C9F7F5",
						"info" : "#EEE5FF",
						"warning" : "#FFF4DE",
						"danger" : "#FFE2E5",
						"light" : "#F3F6F9",
						"dark" : "#D6D6E0"
					},
					"inverse" : {
						"white" : "#ffffff",
						"primary" : "#ffffff",
						"secondary" : "#212121",
						"success" : "#ffffff",
						"info" : "#ffffff",
						"warning" : "#ffffff",
						"danger" : "#ffffff",
						"light" : "#464E5F",
						"dark" : "#ffffff"
					}
				},
				"gray" : {
					"gray-100" : "#F3F6F9",
					"gray-200" : "#ECF0F3",
					"gray-300" : "#E5EAEE",
					"gray-400" : "#D6D6E0",
					"gray-500" : "#B5B5C3",
					"gray-600" : "#80808F",
					"gray-700" : "#464E5F",
					"gray-800" : "#1B283F",
					"gray-900" : "#212121"
				}
			},
			"font-family" : "Poppins"
		};
	</script>
	<!--end::Global Config-->
	<!--begin::Global Theme Bundle(used by all pages)-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}<c:url value="/assets/plugins/global/plugins.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}<c:url value="/assets/plugins/custom/prismjs/prismjs.bundle.js?v=7.0.5" />"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}<c:url value="/assets/js/scripts.bundle.js?v=7.0.5" />"></script>
	<!--end::Global Theme Bundle-->
</body>
<!--end::Body-->
</html>