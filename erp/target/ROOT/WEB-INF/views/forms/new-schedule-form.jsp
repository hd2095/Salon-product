<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIN | New Meeting</title>
<jsp:include page="../layout/nav-bar.jsp" />
<jsp:include page="../layout/header.jsp" />
</head>
<body>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">New
						Meeting</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter meeting details and submit</span>
					</div>
				</div>
				<!--end::Info-->
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="card card-custom">
					<!--begin::Form-->
					<form:form modelAttribute="scheduleForm" class="form"
						autocomplete="off" action="schedule/add" method="post"
						id="scheduleForm">
						<div class="card-body">
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Meeting Date:</label>
									<div class="input-group date">
										<form:input type="text" path="scheduleDate"
											class="form-control form-control-lg" readonly="readonly"
											id="schedule_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="scheduleDate"></form:errors>
									<span class="form-text text-muted">Please enter meeting
										date</span>
								</div>
								<div class="col-lg-6">
									<label>Meeting with:</label>
									<form:input type="text" path="scheduleWith"
										class="form-control" id="scheduleWith"
										placeholder="e.g. John Doe" />
									<form:errors id="validation_error" path="scheduleWith"></form:errors>
									<span id="scheduleWith_span" class="form-text text-muted">Please
										enter meeting with</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Meeting Start Time</label>
									<div class="input-group timepicker">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i
												class="la la-clock-o"></i>
											</span>
										</div>
										<form:input class="form-control" name="scheduleFrom"
											path="scheduleFrom" id="scheduleFrom" readonly="readonly"
											placeholder="Duration" type="text" />
									</div>
									<span id="scheduleFrom_span" class="form-text text-muted">Please
										enter meeting start time</span>
								</div>
								<div class="col-lg-6">
									<label>Meeting End Time</label>
									<div class="input-group timepicker">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i
												class="la la-clock-o"></i>
											</span>
										</div>
										<form:input class="form-control" name="scheduleTo"
											path="scheduleTo" id="scheduleTo" readonly="readonly"
											placeholder="Duration" type="text" />
									</div>
									<span id="scheduleTo_span" class="form-text text-muted">Please
										enter meeting end time</span>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-6">
									<label>Meeting Topic:</label>
									<form:input type="text" path="scheduleTopic"
										class="form-control" id="scheduleTopic" />
									<form:errors id="validation_error" path="scheduleTopic"></form:errors>
									<span id="scheduleWith_span" class="form-text text-muted">Please
										enter meeting topic</span>
								</div>
								<div class="col-lg-6">
									<label>Meeting Notes:</label>
									<form:textarea id="schedule_notes" path="scheduleNotes"
										class="form-control form-control-lg form-control-solid"></form:textarea>
									<span class="form-text text-muted span-info">Please
										enter meeting notes</span>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<button type="reset" id="createScheduleBtn"
										class="btn font-weight-bold btn-primary btn-shadow mr-2">Create Meeting</button>
									<button type="reset" onclick="window.history.back()"
										class="btn font-weight-bold btn-secondary btn-shadow">Cancel</button>
								</div>
							</div>
						</div>
					</form:form>
					<!--end::Form-->
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/timePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/schedule/schedule-handler.js" />"></script>
</html>