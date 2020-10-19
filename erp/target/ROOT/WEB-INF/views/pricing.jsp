<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>OperateIn | Pricing</title>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
</head>
<body>
	<div class="content d-flex flex-column flex-column-fluid"
		id="kt_content">
		<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
			<div
				class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
				<!--begin::Info-->
				<div class="d-flex align-items-center flex-wrap mr-2">
					<!--begin::Page Title-->
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Pricing</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="tab-pane show active row text-center"
					id="kt-pricing-2_content1" role="tabpanel"
					aria-labelledby="pills-tab-1">
					<div class="card-body bg-white col-11 col-lg-12 col-xxl-10 mx-auto">
						<div class="row">
							<!-- begin: Pricing-->
							<div
								class="col-md-6 border-x-0 border-x-md border-y border-y-md-0">
								<div class="pt-30 pt-md-25 pb-15 px-5 text-center">
									<!--begin::Icon-->
									<div class="d-flex flex-center position-relative mb-25">
										<span class="svg svg-fill-primary opacity-4 position-absolute">
											<svg width="175" height="200">
																			<polyline
													points="87,0 174,50 174,150 87,200 0,150 0,50 87,0"></polyline>
																		</svg>
										</span> <span class="svg-icon svg-icon-5x svg-icon-primary"> <!--begin::Svg Icon | path:assets/media/svg/icons/Tools/Compass.svg-->
											<svg xmlns="http://www.w3.org/2000/svg"
												xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
												height="24px" viewBox="0 0 24 24" version="1.1">
    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <rect x="0" y="0" width="24" height="24" />
        <rect fill="#000000" x="4" y="16" width="4" height="4" rx="1" />
        <rect fill="#000000" x="4" y="10" width="4" height="4" rx="1" />
        <rect fill="#000000" x="10" y="16" width="4" height="4" rx="1" />
        <rect fill="#000000" opacity="0.3" x="10" y="10" width="4"
													height="4" rx="1" />
        <rect fill="#000000" x="4" y="4" width="4" height="4" rx="1" />
        <rect fill="#000000" x="16" y="16" width="4" height="4" rx="1" />
    </g>
																		</svg> <!--end::Svg Icon-->
										</span>
									</div>
									<!--end::Icon-->
									<!--begin::Content-->
									<h4 class="font-size-h3 mb-10">Standard</h4>
									<div class="d-flex flex-column line-height-xl mb-10">
										<span>2000 Appointment Entries</span> <span>500 Client
											Entries</span> <span>15 Staff Entries</span> <span>50
											Category Entries</span> <span>100 Services Entries</span> <span>100
											Product Entries</span> <span>200 New Order Entries</span> <span>500
											Sale Entries</span> <span>50 Supplier Entries</span>
									</div>
									<span
										class="font-size-h1 d-block font-weight-boldest text-dark">4999
										<sup class="font-size-h3 font-weight-normal pl-1"><i
											class="fas fa-rupee-sign"></i></sup>
									</span>
									<div class="mt-7">
										<button type="button" onclick = "purchasePlan('Standard')"
											class="btn btn-primary text-uppercase font-weight-bolder px-15 py-3">Purchase</button>
									</div>
									<!--end::Content-->
								</div>
							</div>
							<!-- end: Pricing-->
							<!-- begin: Pricing-->
							<div class="col-md-6">
								<div class="pt-30 pt-md-25 pb-15 px-5 text-center">
									<!--begin::Icon-->
									<div class="d-flex flex-center position-relative mb-25">
										<span class="svg svg-fill-primary opacity-4 position-absolute">
											<svg width="175" height="200">
																			<polyline
													points="87,0 174,50 174,150 87,200 0,150 0,50 87,0"></polyline>
																		</svg>
										</span> <span class="svg-icon svg-icon-5x svg-icon-primary"> <!--begin::Svg Icon | path:assets/media/svg/icons/Shopping/Cart2.svg-->
											<svg xmlns="http://www.w3.org/2000/svg"
												xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
												height="24px" viewBox="0 0 24 24" version="1.1">
<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
        <polygon points="0 0 24 0 24 24 0 24" />
        <path
													d="M11.2600599,5.81393408 L2,16 L22,16 L12.7399401,5.81393408 C12.3684331,5.40527646 11.7359848,5.37515988 11.3273272,5.7466668 C11.3038503,5.7680094 11.2814025,5.79045722 11.2600599,5.81393408 Z"
													fill="#000000" opacity="0.3" />
        <path
													d="M12.0056789,15.7116802 L20.2805786,6.85290308 C20.6575758,6.44930487 21.2903735,6.42774054 21.6939717,6.8047378 C21.8964274,6.9938498 22.0113578,7.25847607 22.0113578,7.535517 L22.0113578,20 L16.0113578,20 L2,20 L2,7.535517 C2,7.25847607 2.11493033,6.9938498 2.31738608,6.8047378 C2.72098429,6.42774054 3.35378194,6.44930487 3.7307792,6.85290308 L12.0056789,15.7116802 Z"
													fill="#000000" />
    </g>
																		</svg> <!--end::Svg Icon-->
										</span>
									</div>
									<!--end::Icon-->
									<!--begin::Content-->
									<h4 class="font-size-h3 mb-10">Premium</h4>
									<div class="d-flex flex-column line-height-xl mb-10">
										<span>Unlimited Appointment Entries</span> <span>Unlimited
											Client Entries</span> <span>Unlimited Staff Entries</span> <span>Unlimited
											Category Entries</span> <span>Unlimited Services Entries</span> <span>Unlimited
											Product Entries</span> <span>Unlimited New Order Entries</span> <span>Unlimited
											Sale Entries</span> <span>Unlimited Supplier Entries</span>
									</div>
									<span
										class="font-size-h1 d-block font-weight-boldest text-dark">9999
										<sup class="font-size-h3 font-weight-normal pl-1"><i
											class="fas fa-rupee-sign"></i></sup>
									</span>
									<div class="mt-7">
										<button type="button" onclick = "purchasePlan('Premium')"
											class="btn btn-primary text-uppercase font-weight-bolder px-15 py-3">Purchase</button>
									</div>
									<!--end::Content-->
								</div>
							</div>
							<!-- end: Pricing-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/pricing/pricing.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script>
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
	});
</script>
</html>