<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
<!--begin::Content-->
<div class="content d-flex flex-column flex-column-fluid"
	id="kt_content">
	<!--begin::Subheader-->
	<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
		<div
			class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
			<!--begin::Info-->
			<div class="d-flex align-items-center flex-wrap mr-2">
				<!--begin::Page Title-->
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Dashboard</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				<!--end::Actions-->
			</div>
			<!--end::Info-->
			<!--begin::Toolbar-->
			<div class="d-flex align-items-center">
				<!--begin::Actions-->
				<a href="#"
					class="btn btn-clean btn-hover-light-primary- active btn-sm font-weight-bold font-size-base mr-1">Today</a>
				<a href="#"
					class="btn btn-clean btn-hover-light-primary- btn-sm font-weight-bold font-size-base mr-1">Month</a>
				<a href="#"
					class="btn btn-clean btn-hover-light-primary- btn-sm font-weight-bold font-size-base mr-1">Year</a>
				<!--end::Actions-->
			</div>
			<!--end::Toolbar-->
		</div>
	</div>
	<!--end::Subheader-->
	<div class="d-flex flex-column-fluid">
		<!--begin::Container-->
		<div class=" container ">
			<!--begin::Dashboard-->
			<!--begin::Row-->
			<div class="row">
				<div class="col-lg-6 col-xxl-4">
					<div class="card card-custom card-stretch gutter-b">
						<div class="card-header border-0 py-5">
							<h3 class="card-label">Sales</h3>
						</div>
						<div class="card-body p-0 position-relative overflow-hidden">
							<div id="chart_1"></div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
					<!--begin::List Widget 3-->
					<div class="card card-custom card-stretch gutter-b">
						<!--begin::Header-->
						<div class="card-header border-0">
							<h3 class="card-title font-weight-bolder text-dark">Top
								Clients</h3>
							<div class="card-toolbar">
								<div class="dropdown dropdown-inline">
									<a href="#"
										class="btn btn-light-primary btn-sm font-weight-bolder dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Create</a>
									<div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header pb-1"><span
												class="text-primary text-uppercase font-weight-bold font-size-sm">Add
													new:</span></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-shopping-cart-1"></i>
												</span> <span class="navi-text">Order</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-calendar-8"></i>
												</span> <span class="navi-text">Event</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-graph-1"></i>
												</span> <span class="navi-text">Report</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-rocket-1"></i>
												</span> <span class="navi-text">Post</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-writing"></i>
												</span> <span class="navi-text">File</span>
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
							</div>
						</div>
						<!--end::Header-->
						<!--begin::Body-->
						<div class="card-body pt-2">
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/009-boy-4.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">ricky.hunt@gmail.com</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/006-girl-3.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Anne
										Clarc</a> <span class="text-muted">anne.clarc@gmail.com</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/011-boy-5.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">John
										Doe</a> <span class="text-muted">john.doe@ymail.com</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end:Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/015-boy-6.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Steven Gerard</a> <span class="text-muted">steven.gerard@outlook.com</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-2">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/016-boy-7.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Carles
										Puyol</a> <span class="text-muted">carles.puyol@gmail.com</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
						</div>
						<!--end::Body-->
					</div>
					<!--end::List Widget 3-->
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
					<!--begin::List Widget 4-->
					<div class="card card-custom card-stretch gutter-b">
						<!--begin::Header-->
						<div class="card-header border-0">
							<h3 class="card-title font-weight-bolder text-dark">Top
								Services</h3>
							<div class="card-toolbar">
								<div class="dropdown dropdown-inline">
									<a href="#"
										class="btn btn-light btn-sm font-size-sm font-weight-bolder dropdown-toggle text-dark-75"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Create</a>
									<div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header pb-1"><span
												class="text-primary text-uppercase font-weight-bold font-size-sm">Add
													new:</span></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-shopping-cart-1"></i>
												</span> <span class="navi-text">Order</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-calendar-8"></i>
												</span> <span class="navi-text">Event</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-graph-1"></i>
												</span> <span class="navi-text">Report</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-rocket-1"></i>
												</span> <span class="navi-text">Post</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-writing"></i>
												</span> <span class="navi-text">File</span>
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
							</div>
						</div>
						<!--end::Header-->
						<!--begin::Body-->
						<div class="card-body pt-2">
							<!--begin::Item-->
							<div class="d-flex align-items-center">
								<!--begin::Bullet-->
								<span style="margin-right: 15px;"
									class="bullet bullet-bar bg-success align-self-stretch"></span>
								<!--end::Bullet-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1">
									<a href="#"
										class="text-dark-75 text-hover-primary font-weight-bold font-size-lg mb-1">Blow
										Dry</a> <span class="text-muted font-weight-bold">Duration : 2 hrs</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end:Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mt-10">
								<!--begin::Bullet-->
								<span style="margin-right: 15px;"
									class="bullet bullet-bar bg-primary align-self-stretch"></span>
								<!--end::Bullet-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1">
									<a href="#"
										class="text-dark-75 text-hover-primary font-weight-bold font-size-lg mb-1">Body
										Massage </a> <span class="text-muted font-weight-bold">Duration : 1 hr</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mt-10">
								<!--begin::Bullet-->
								<span style="margin-right: 15px;"
									class="bullet bullet-bar bg-warning align-self-stretch"></span>
								<!--end::Bullet-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1">
									<a href="#"
										class="text-dark-75 text-hover-primary font-size-sm font-weight-bold font-size-lg mb-1">Head
										Massage</a> <span class="text-muted font-weight-bold">Duration : 1 hr</span>
								</div>
								<!--end::Text-->
								<!--begin: Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
						</div>
						<!--end::Body-->
					</div>
					<!--end:List Widget 4-->
				</div>
				<div class="col-lg-6 col-xxl-4 order-1 order-xxl-2">
					<!--begin::List Widget 3-->
					<div class="card card-custom card-stretch gutter-b">
						<!--begin::Header-->
						<div class="card-header border-0">
							<h3 class="card-title font-weight-bolder text-dark">Top
								Staff</h3>
							<div class="card-toolbar">
								<div class="dropdown dropdown-inline">
									<a href="#"
										class="btn btn-light-primary btn-sm font-weight-bolder dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Create</a>
									<div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header pb-1"><span
												class="text-primary text-uppercase font-weight-bold font-size-sm">Add
													new:</span></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-shopping-cart-1"></i>
												</span> <span class="navi-text">Order</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-calendar-8"></i>
												</span> <span class="navi-text">Event</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-graph-1"></i>
												</span> <span class="navi-text">Report</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-rocket-1"></i>
												</span> <span class="navi-text">Post</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-icon"> <i
														class="flaticon2-writing"></i>
												</span> <span class="navi-text">File</span>
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
							</div>
						</div>
						<!--end::Header-->
						<!--begin::Body-->
						<div class="card-body pt-2">
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/009-boy-4.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">Designation : Hair Dresser</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/006-girl-3.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Anne
										Clarc</a> <span class="text-muted">Designation : Receptionist</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/011-boy-5.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Kristaps
										Zumman</a> <span class="text-muted">Designation : Gym Trainer</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end:Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-10">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/015-boy-6.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Ricky
										Hunt</a> <span class="text-muted">Designation : Stock boy</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
							<!--begin::Item-->
							<div class="d-flex align-items-center mb-2">
								<!--begin::Symbol-->
								<div class="symbol symbol-40 symbol-light-success mr-5">
									<span class="symbol-label"> <img
										src="assets/media/svg/avatars/016-boy-7.svg"
										class="h-75 align-self-end" alt="" />
									</span>
								</div>
								<!--end::Symbol-->
								<!--begin::Text-->
								<div class="d-flex flex-column flex-grow-1 font-weight-bold">
									<a href="#"
										class="text-dark text-hover-primary mb-1 font-size-lg">Carles
										Puyol</a> <span class="text-muted">Designation : Manager</span>
								</div>
								<!--end::Text-->
								<!--begin::Dropdown-->
								<div class="dropdown dropdown-inline ml-2" data-toggle="tooltip"
									title="Quick actions" data-placement="left">
									<a href="#" class="btn btn-hover-light-primary btn-sm btn-icon"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
									</a>
									<div
										class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
										<!--begin::Navigation-->
										<ul class="navi navi-hover">
											<li class="navi-header font-weight-bold py-4"><span
												class="font-size-lg">Choose Label:</span> <i
												class="flaticon2-information icon-md text-muted"
												data-toggle="tooltip" data-placement="right"
												title="Click to learn more..."></i></li>
											<li class="navi-separator mb-3 opacity-70"></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-success">Customer</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-danger">Partner</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-warning">Suplier</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-primary">Member</span>
												</span>
											</a></li>
											<li class="navi-item"><a href="#" class="navi-link">
													<span class="navi-text"> <span
														class="label label-xl label-inline label-light-dark">Staff</span>
												</span>
											</a></li>
											<li class="navi-separator mt-3 opacity-70"></li>
											<li class="navi-footer py-4"><a
												class="btn btn-clean font-weight-bold btn-sm" href="#">
													<i class="ki ki-plus icon-sm"></i>Add new
											</a></li>
										</ul>
										<!--end::Navigation-->
									</div>
								</div>
								<!--end::Dropdown-->
							</div>
							<!--end::Item-->
						</div>
						<!--end::Body-->
					</div>
					<!--end::List Widget 3-->
				</div>
			</div>
		</div>
	</div>
</div>
<!--end::Content-->
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/features/charts/apexcharts.js?v=7.0.5"></script>