<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OperateIN | Edit Appointment</title>
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
					<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Edit
						Appointment</h5>
					<!--end::Page Title-->
					<!--begin::Actions-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter appointment details and
							submit</span>
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
					<form:form modelAttribute="editAppointmentForm" class="form" autocomplete="off"
						name="editAppointmentForm" action="appointment/edit" method="post"
						id="editAppointmentForm">
						<form:hidden id="edit_appointmentId" path="appointmentId" />
						<div class="card-body">
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Appointment
									Date:</label>
								<div class="col-lg-4 col-xl-4">
									<div class="input-group date">
										<form:input type="text" path="appointmentDate"
											class="form-control form-control-lg" readonly="readonly"
											id="edit_appointment_date" />
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="la la-calendar"></i>
											</span>
										</div>
									</div>
									<form:errors id="validation_error" path="appointmentDate"></form:errors>
									<span class="form-text text-muted">Please enter
										appointment Date</span>
								</div>
							</div>
							<input type="hidden" name="edit_total_elements" />
							<div class="separator separator-dashed my-8"></div>
							<div id="edit_service_repeater">
								<div data-repeater-list="" class="col-lg-10">
									<div data-repeater-item class="form-group">
										<input type="hidden" name="edit_appointment_record_id" />
										<div class="card">
											<div class="card-body">
												<input type="hidden" name="edit_appointment_service_cost" />
												<input type="hidden" name="edit_appointment_duration_hidden" />
												<div class="form-group row">
													<div class="col-lg-4">
														<label>Start Time</label> <input type="text"
															class="form-control form-control-lg form-control-solid"
															name="edit_appointment_start_time"></input>
													</div>
													<div class="col-lg-6">
														<label>Service</label> <select
															onchange="changeService(this.name);"
															class="form-control form-control-lg form-control-solid dropdown"
															name="edit_appointment_service"></select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg-4">
														<label>Duration</label> <input
															class="form-control form-control-lg form-control-solid"
															name="edit_appointment_duration" readonly></input>
													</div>
													<div class="col-lg-6">
														<label>Staff</label> <select
															class="form-control form-control-lg form-control-solid dropdown"
															name="edit_appointment_staff"></select>
													</div>
												</div>
											</div>
											<div class="card-footer d-flex justify-content-between">
												<a href="javascript:;"> </a> <a href="javascript:;"
													data-repeater-delete=""
													class="btn btn-sm btn-danger font-weight-bold"> <i
													class="la la-trash-o"></i>Delete
												</a>
											</div>
										</div>
									</div>
								</div>
								<div data-repeater-create="" id="addServiceBtn"
									class="btn btn-sm font-weight-bolder btn-light-primary">
									<i class="la la-plus"></i> Add Service
								</div>
							</div>
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Client:</label>
								<div class="col-lg-4 col-xl-4">
									<form:select path="client" id="edit_appointment_client"
										class="form-control select2" name="param">
									</form:select>
									<span class="form-text text-muted">Please select client</span>
								</div>
								<div class="col-lg-4 col-xl-4" id="edit_clientDetails"
									style="display: none;">
									<div class="card-header border-0 py-5">
										<h3 class="card-title font-weight-bolder">Client Details:</h3>
										<div class="card-body p-0 d-flex flex-column"
											style="position: relative;">
											<!--begin::Stats-->
											<div class="card-spacer pt-5 bg-white flex-grow-1">
												<!--begin::Row-->
												<div class="row row-paddingless">
													<div class="col mr-8">
														<div class="font-size-sm text-muted font-weight-bold">Loyalty
															Points</div>
														<div class="font-size-h4 font-weight-bolder"
															id="edit_loyaltyPoints"></div>
													</div>
													<div class="col">
														<div class="font-size-sm text-muted font-weight-bold">Total
															Visits</div>
														<div class="font-size-h4 font-weight-bolder"
															id="edit_totalVisits"></div>
													</div>
												</div>
												<!--end::Row-->
												<!--begin::Row-->
												<div class="row row-paddingless mt-8">
													<div class="col mr-8">
														<div class="font-size-sm text-muted font-weight-bold">Last
															Visited Date</div>
														<div class="font-size-h4 font-weight-bolder"
															id="edit_lastVisitedDate"></div>
													</div>
													<div class="col">
														<div class="font-size-sm text-muted font-weight-bold">Client
															Active Plan</div>
														<div class="font-size-h4 font-weight-bolder"
															id="edit_clientPlan"></div>
													</div>
												</div>
												<!--end::Row-->
											</div>
											<!--end::Stats-->
										</div>
									</div>
								</div>
							</div>
							<div class="separator separator-dashed my-8"></div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Cost:</label>
								<div class="col-lg-4 col-xl-4">
									<form:input type="text" id="edit_appointment_cost"
										name="edit_appointment_cost" path="appointmentExpectedTotal"
										class="form-control form-control-lg form-control-solid"
										readonly="readonly" /> <span
										class="form-text text-muted span-info">Please enter
										appointment cost</span>
								</div>
								<label class="col-xl-2 col-lg-2 col-form-label">Duration:</label>
								<div class="col-lg-4 col-xl-4">
									<input type="text" name="edit_total_appointment_duration"
										class="form-control form-control-lg form-control-solid"
										id="edit_total_appointment_duration" readonly="readonly" /> <span
										class="form-text text-muted">Please enter appointment
										duration</span>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-xl-2 col-lg-2 col-form-label">Appointment
									Notes:</label>
								<div class="col-lg-4 col-xl-4">
									<form:textarea id="edit_appointment_notes"
										path="appointmentNotes"
										class="form-control form-control-lg form-control-solid"></form:textarea>
									<span class="form-text text-muted span-info">Please
										enter appointment notes</span>
								</div>
								<label class="col-xl-2 col-lg-2 col-form-label" id="edit_status">Status:</label>
								<div class="col-lg-4 col-xl-4" id="edit_status_div">
									<form:select path="appointmentStatus"
										class="form-control form-control-lg form-control-solid select2"
										id="edit_appointment_status" name="param">
										<option value="booked">Booked</option>
										<option value="NoShow">No Show</option>
										<option value="Completed">Completed</option>
									</form:select>
									<span class="form-text text-muted">Please enter
										appointment status</span>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<button type="reset" id="editAppointmentBtn"
										class="btn font-weight-bold btn-primary btn-shadow mr-2">Edit Appointment</button>
									<a href = "appointment"
										class="btn font-weight-bold btn-secondary btn-shadow">Cancel</a>
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
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/form-repeater.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/datePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/timePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/appointment/edit-appointment.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/select2.js" />"></script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
		var isCompleted = '${editAppointmentForm.appointmentStatus}';
		if(isCompleted == 'Completed'){
			$('#edit_status').remove();
			$('#edit_status_div').remove();
		}
		var selectedClientId = '${editAppointmentForm.client.clientId}';
		populateClient(selectedClientId);
		showClientOverview(selectedClientId);
		var appointmentId = '${editAppointmentForm.appointmentId}';
		fetchAppointmentDetails(appointmentId);
	});
</script>
</html>