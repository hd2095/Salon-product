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
						<h3 class="panel-title">Edit Appointment</h3>
						<p class="panel-subtitle">Enter appointment details and submit</p>
							<div id="edit_appointmentAlreadyExists"
							style="display: none; color: red; text-align: center;"></div>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="editAppointmentForm" class="form"
							name="editAppointmentForm" action="appointment/edit"
							method="post" id="editAppointmentForm">
							<form:hidden id="edit_appointmentId" path="appointmentId" />
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Date:</label>
									<div class="input-group date">
										<form:input type="text" id="edit_appointment_date" path="appointmentDate"
											class="form-control" />
										<span class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"> </span>
										</span>
									</div>
									<form:errors id="validation_error" path="appointmentDate"></form:errors>
									<span id="edit_appointmentDate_span"
										class="form-text text-muted">Please enter appointment
										Date</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Client:</label>
									<form:select id="edit_appointment_client" path="client"
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
											id="edit_appointment_start_time" readonly="readonly"
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
										id="edit_appointment_service" name="param"></form:select>
									<span class="form-text text-muted">Please select
										appointment Service</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Duration:</label>
									<form:input type="text"
										class="form-control form-control-lg form-control-solid"
										id="edit_appointment_duration" path="service.serviceDuration"
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
										id="edit_appointment_staff" name="param"></form:select>
									<span class="form-text text-muted">Please select
										appointment staff</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Cost:</label>
									<form:input type="text" path="service.serviceCost"
										id="edit_appointment_cost"
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
										id="edit_appointment_status" name="param">
										<option value="booked">Booked</option>
										<option value="No Show">No Show</option>
										<option value="Completed">Completed</option>
									</form:select>
									<span class="form-text text-muted">Please enter
										appointment status</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Appointment Notes:</label>
									<form:textarea type="text" path="appointmentNotes"
										id="edit_appointment_notes"
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
			$('#edit_appointment_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#edit_appointment_start_time').timepicker({});
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/appointment/edit-appointment.js"></script>
</body>
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
<script type="text/javascript">
	var serviceArray;
	var staffArray;

	function fetchServices(serviceId) {
		$.ajax({
			url : HOST_URL + '/services/getAllServices',
			type : 'get',
			dataType : 'json',
			success : function(response) {
				serviceArray = response.data;
				for (var i = 0; i < response.data.length; i++) {
					var service_cost = response.data[i]['serviceCost'];
					var service_duration = response.data[i]['serviceDuration']
					var service_id = response.data[i]['serviceId'];
					var service_name = response.data[i]['serviceName'];
					if (service_id == serviceId) {
						$("#edit_appointment_service").append(
								"<option value='"+service_id+"' selected>"
										+ service_name + "</option>");
						$('#edit_appointment_cost').val(service_cost);
						$('#edit_appointment_duration').val(service_duration);
					} else {
						$("#edit_appointment_service").append(
								"<option value='"+service_id+"'>"
										+ service_name + "</option>");
					}
				}
			}
		});
	}

	function fetchStaff(selectedStaffId) {
		$.ajax({
			url : HOST_URL + '/staff/getAllStaff',
			type : 'get',
			dataType : 'json',
			success : function(response) {
				staffArray = response.data;
				for (var i = 0; i < response.data.length; i++) {
					var staff_id = response.data[i]['staffId'];
					var staff_name = response.data[i]['fullName'];
					if (staff_id == selectedStaffId) {
						$("#edit_appointment_staff").append(
								"<option value='"+staff_id+"' selected>"
										+ staff_name + "</option>");
					} else {
						$("#edit_appointment_staff").append(
								"<option value='"+staff_id+"'>" + staff_name
										+ "</option>");
					}
				}
			}
		});
	}

	function changeService(value) {
		for (var i = 0; i < serviceArray.length; i++) {
			var service_cost = serviceArray[i]['serviceCost'];
			var service_duration = serviceArray[i]['serviceDuration'];
			var serviceId = serviceArray[i]['serviceId'];
			if (serviceId == value) {
				$('#edit_appointment_cost').val(service_cost);
				$('#edit_appointment_duration').val(service_duration);
			}
		}
	}

	function populateClient(client_id) {
		$.ajax({
			url : HOST_URL + '/client/getAllClients',
			type : 'get',
			dataType : 'json',
			success : function(response) {
				for (var i = 0; i < response.data.length; i++) {
					var clientId = response.data[i]['clientId'];
					var clientName = response.data[i]['fullName'];
					if (clientId == client_id) {
						$("#edit_appointment_client").append(
								"<option value='"+clientId+"' selected>"
										+ clientName + "</option>");
					} else {
						$("#edit_appointment_client").append(
								"<option value='"+clientId+"'>" + clientName
										+ "</option>");
					}
				}
			}
		});

	}

	jQuery(document).ready(function() {
		var data = '${editAppointmentForm.staff.staffId}';
		fetchStaff(data);
		data = '${editAppointmentForm.service.serviceId}';
		fetchServices(data);
		data = '${editAppointmentForm.client.clientId}';
		populateClient(data);
		var invalidAppointment = '${appointmentExists}';
		if (invalidAppointment.length > 2) {
			$('#edit_appointmentAlreadyExists').html(invalidAppointment);
			$('#edit_appointmentAlreadyExists').show();
		}
	});
</script>
</html>