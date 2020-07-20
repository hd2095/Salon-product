<!DOCTYPE html>
<html lang="en">
<head>
<base href="../../">
<jsp:include page="../layout/side-nav.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
</head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="panel panel-headline">
					<div class="panel-heading">
						<h3 class="panel-title">New Appointment</h3>
						<p class="panel-subtitle">Enter appointment details and submit</p>
						<div id="appointmentAlreadyExists"
							style="display: none; color: red; text-align: center;"></div>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="appointmentForm" class="form"
							name="appointmentForm" action="appointment/add" method="post"
							id="appointmentForm" autocomplete="off">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Date:</label>
									<div class="input-group date">
										<form:input type="text" path="appointmentDate"
											id="appointmentDate" class="form-control" />
										<span class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"> </span>
										</span>
									</div>
									<form:errors id="validation_error" path="appointmentDate"></form:errors>
									<span id="appointmentDate_span" class="form-text text-muted">Please
										enter appointment Date</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Client:</label>
									<form:select id="appointment_client" path="client"
										class="form-control select2" name="param">
									</form:select>
									<span class="form-text text-muted">Please select client</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Appointment Time:</label>
									<div class="input-group date">
										<form:input path="appointmentStartTime"
											class="form-control form-control-lg form-control-solid"
											id="appointmentStartTime" readonly="readonly"
											placeholder="Start time" type="text" />
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-time"></i></span>
									</div>
									<form:errors id="validation_error" path="appointmentStartTime"></form:errors>
									<span class="form-text text-muted">Please enter
										appointment time</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Service:</label>
									<form:select path="service"
										onchange="changeService(this.value);"
										class="form-control form-control-lg form-control-solid select2"
										id="appointment_service" name="param"></form:select>
									<span class="form-text text-muted">Please select
										appointment Service</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Duration:</label>
									<form:input type="text"
										class="form-control form-control-lg form-control-solid"
										id="appointment_duration" path="service.serviceDuration"
										disabled="true" />
									<span class="form-text text-muted">Please enter
										appointment duration</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Staff:</label>
									<form:select path="staff"
										class="form-control form-control-lg form-control-solid select2"
										id="appointment_staff" name="param"></form:select>
									<span class="form-text text-muted">Please select
										appointment staff</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Cost:</label>
									<form:input type="text" path="service.serviceCost"
										id="appointment_cost"
										class="form-control form-control-lg form-control-solid"
										disabled="true" />
									<span class="form-text text-muted span-info">Please
										enter appointment cost</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Status:</label>
									<form:select path="appointmentStatus"
										class="form-control form-control-lg form-control-solid select2"
										disabled="true" id="appointment_status" name="param">
										<option value="booked">Booked</option>
									</form:select>
									<span class="form-text text-muted">Please enter
										appointment status</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Appointment Notes:</label>
									<form:textarea type="text" path="appointmentNotes"
										id="appointment_notes"
										class="form-control form-control-lg form-control-solid" />
									<span class="form-text text-muted span-info">Please
										enter appointment notes</span>
								</div>
								<div class="form-group">
									<button type="button" class="btn btn-lg"
										style="float: right; margin: 3%; background-color: #252c35; color: white;"
										onclick="submitForm()">Submit</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
		$(function() {
			$('#appointmentDate').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#appointmentStartTime').timepicker({});
		});
		jQuery(document).ready(function() {
			var invalidAppointment = '${appointmentExists}';
			if (invalidAppointment.length > 2) {
				$('#appointmentAlreadyExists').html(invalidAppointment);
				$('#appointmentAlreadyExists').show();			
			}
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/appointment/add-appointment.js"></script>
	<script src="assets/js/pages/appointment/fetchDetails.js"></script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
</html>