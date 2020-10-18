<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--begin::Wrapper-->
<div class="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
	<!--begin::Header-->
	<div id="kt_header" class="header header-fixed">
		<!--begin::Container-->
		<div
			class="container-fluid d-flex align-items-stretch justify-content-between">
			<!-- pushes content to right -->
			<div></div>
			<!--begin::Topbar-->
			<div class="topbar" style="cursor: pointer;">
				<div class="topbar-item">
					<div class="d-flex align-items-center">
						<a href="pricing"
							class="btn btn-clean btn-hover-light-primary- active btn-sm font-weight-bold font-size-base mr-1">Upgrade
							To Pro!</a>
					</div>
				</div>
				<div class="topbar-item" style="padding-left: 5px;">
					<div class="dropdown">
						<div id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<span
								class="text-muted font-weight-bold font-size-base d-none d-md-inline mr-1">Hi,</span>
							<span
								class="text-dark-50 font-weight-bolder font-size-base d-none d-md-inline mr-3">${sessionScope.session_firstname}</span>
							<span class="symbol symbol-35 symbol-light-success"> <span
								class="symbol-label font-size-h5 font-weight-bold">${sessionScope.session_firstname.substring(0,1)}</span>
							</span>
						</div>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<ul class="navi navi-hover">
								<li class="navi-item"><a class="dropdown-item navi-link"
									href="invalidate"> <span class="navi-icon"> <i
											class="flaticon-logout"></i>
									</span> <span class="navi-text">Sign Out</span>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!--end::User-->
			</div>
			<!--end::Topbar-->
		</div>
		<!--end::Container-->
	</div>
	<!--end::Header-->
</div>