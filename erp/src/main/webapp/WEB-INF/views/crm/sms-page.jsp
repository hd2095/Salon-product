<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<base href="../">
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
<title>OperateIN | SMS</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Sms</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				</div>
				<div class="d-flex align-items-center">
					<!--end::Actions-->
				</div>
				<!--end::Info-->
			</div>
		</div>
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="row">
					<div class="col-xl-4">
						<div id="template-one" style="cursor: hand; height: 175px;"
							onclick="populateMsgArea(this.id)"
							class="card card-custom gutter-b">
							<!--begin::Body-->
							<div class="card-body d-flex flex-column">
								<!--begin::Stats-->
								<div class="flex-grow-1">
									<div class="text-dark-50 font-weight-bold">Birthday
										Message</div>
									<div id="template-one-para" class="font-weight-bolder">
										Dear Customer, We wish you a awesome year ahead on your birthday & hope for your happiness and success. <br />Regards,<br />${organizationName}
									</div>
								</div>
								<!--end::Stats-->
							</div>
							<!--end::Body-->
						</div>
					</div>
					<div class="col-xl-4">
						<div id="template-two" style="cursor: hand; height: 175px;"
							onclick="populateMsgArea(this.id)"
							class="card card-custom gutter-b">
							<!--begin::Body-->
							<div class="card-body d-flex flex-column">
								<!--begin::Stats-->
								<div class="flex-grow-1">
									<div class="text-dark-50 font-weight-bold">Visiting
										Message</div>
									<div id="template-two-para" class="font-weight-bolder">
										Dear Customer, Thank you for visiting us. Hope you enjoyed our service. See you soon. <br /> Regards, <br />${organizationName}
									</div>
								</div>
								<!--end::Stats-->
							</div>
							<!--end::Body-->
						</div>
					</div>
					<div class="col-xl-4">
						<div id="template-three" style="cursor: hand; height: 175px;"
							onclick="populateMsgArea(this.id)"
							class="card card-custom gutter-b">
							<!--begin::Body-->
							<div class="card-body d-flex flex-column">
								<!--begin::Stats-->
								<div class="flex-grow-1">
									<div class="text-dark-50 font-weight-bold">Anniversary
										Message</div>
									<div id="template-three-para" class="font-weight-bolder">Dear Customer, ${organizationName} is celebrating its anniversary week & hence offering discounts on selected services. visit us to explore more</div>
								</div>
								<!--end::Stats-->
							</div>
							<!--end::Body-->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-6 col-sm-12">
						<div class="card card-custom bg-light gutter-b">
							<!--begin::Header-->
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bold text-dark">Select
									Client(s) and send Message(s)</h3>
								<div class="card-toolbar">
									<div class="dropdown dropdown-inline" data-toggle="tooltip"
										title="" data-placement="left"
										data-original-title="Filter Client By">
										<a href="#"
											class="btn btn-clean btn-hover-light-primary btn-sm btn-icon"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-md dropdown-menu-right"
											style="">
											<ul class="navi navi-hover py-5">
												<li class="navi-item"><a
													href="javascript:filterClient('birthday');"
													class="navi-link"> <span class="navi-icon"> <i
															class="flaticon2-calendar-8"></i>
													</span> <span class="navi-text">Upcoming Birthday(s)</span>
												</a></li>
												<li class="navi-item"><a
													href="javascript:filterClient('revenue');"
													class="navi-link"> <span class="navi-icon"> <i
															class="flaticon-customer"></i>
													</span> <span class="navi-text">Revenue Generated</span>
												</a></li>
												<li class="navi-item"><a
													href="javascript:fetchClients();" class="navi-link">
														<span class="navi-icon"> <i
															class="flaticon-users-1"></i>
													</span> <span class="navi-text">All Client(s)</span>
												</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body pt-2">
								<!--begin::Form-->
								<form class="form" name="clientMessageForm"
									id="clientMessageForm" method="post">
									<div class="form-group">
										<select name="crm_sms_clients" id="crm_sms_clients"
											class="form-control selectpicker" multiple
											data-actions-box="true">
										</select>
									</div>
									<div class="form-group">
										<textarea class="form-control border-0" name="message-to-send" readonly
											rows="4" placeholder="Message" id="message-to-send"
											style="overflow: hidden; overflow-wrap: break-word; resize: none; height: 93px;"></textarea>
									</div>
								</form>
								<div class="mt-10">
								<c:if test="${showSendBtn}">
									<button onclick="sendMessage();"
										class="btn btn-primary font-weight-bold">Send Message</button>
								</c:if>
								</div>
								<!--end::Form-->
							</div>
						</div>
					</div>
					<div class="col-xl-6">
						<div class="card card-custom gutter-b" style="height: 175px">
							<!--begin::Body-->
							<div class="card-body d-flex flex-column">
								<!--begin::Stats-->
								<div class="flex-grow-1">
									<div class="text-dark-50 font-weight-bold">Message(s)
										Total/Sent</div>
									<div class="font-weight-bolder font-size-h3">${totalMessages}/${totalMessagesSent}</div>
								</div>
								<!--end::Stats-->
								<!--begin::Progress-->
								<div class="progress progress-xs">
									<div class="progress-bar bg-primary" role="progressbar"
										style="width: ${percentUsed}%;" aria-valuenow="${percentUsed}"
										aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<!--end::Progress-->
							</div>
							<!--end::Body-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script>
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
		var data = '${messageSent}';
		if (data.length > 1) {
			$.notify({
				// options
				message : data
			}, {
				// settings
				type : 'success',
				delay : 5000
			});
		}
		data = '${messageNotSent}';
		if (data.length > 1) {
			$.notify({
				// options
				message : data
			}, {
				// settings
				type : 'danger',
				delay : 5000
			});
		}
	});
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/crm/crm-sms.js" />"></script>
</html>