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
						<h3 class="panel-title">New Staff</h3>
						<p class="panel-subtitle">Enter Staff details and submit</p>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="staffForm" class="form" autocomplete="off"
							action="staff/add/" method="post" id="staffForm">
							<div class="row">
							<h4 class="font-weight-light">Basic Details:</h4>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Full Name:</label>
									<form:input type="text" path="fullName" id="fullName"
										class="form-control form-control-lg form-control-solid"
										placeholder="Enter full name" />
									<form:errors id="validation_error" path="fullName"></form:errors>
									<span class="form-text text-muted">Please enter staff's
										full name</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Contact Number:</label>
									<form:input type="text" path="mobileNumber" id="mobileNumber"
										class="form-control form-control-lg form-control-solid"
										placeholder="Enter contact number" />
									<form:errors id="validation_error" path="mobileNumber"></form:errors>
									<span class="form-text text-muted">Please enter staff's
										contact number</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Address:</label>
									<form:input type="text" path="staff_address" id="staffAddress"
										class="form-control form-control-lg form-control-solid"
										placeholder="Enter staff's address" />
									<form:errors id="validation_error" path="staff_address"></form:errors>
									<span class="form-text text-muted">Please enter staff's
										address</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Pincode:</label>
									<form:input type="text"
										class="form-control form-control-lg form-control-solid"
										id="staffPincode" path="staffPincode"
										placeholder="Enter staff's pincode" />
									<form:errors id="validation_error" path="staffPincode"></form:errors>
									<span class="form-text text-muted">Please enter staff's
										pincode</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Gender:</label>
									<div>
										<form:radiobutton path="gender" name="staffGender"
											checked="checked" value="Male" />
										Male <span></span>
										<form:radiobutton style="margin-left:5%;" path="gender"
											name="staffGender" value="Female" />
										Female <span></span>
									</div>
									<span class="form-text text-muted">Please select staff's
										gender</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Email Id:</label>
									<form:input type="text" path="emailId" id="staffEmail"
										class="form-control form-control-lg form-control-solid"
										placeholder="Enter email id" />
									<form:errors id="validation_error" path="emailId"></form:errors>
									<span class="form-text text-muted span-info">Please
										enter staff's Email id</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Birthday:</label>
									<div class="input-group date">
										<form:input type="text"
											class="form-control form-control-lg form-control-solid"
											readonly="readonly" path="birthday" autocomplete="off"
											id="staff_birthday" />
										<span class="input-group-addon"><i
											class="lnr lnr-calendar-full"></i></span>
									</div>
									<form:errors id="validation_error" path="birthday"></form:errors>
									<span class="form-text text-muted">Please select staff's
										birthday</span>
								</div>
							</div>
							</div>
							<div class="row">
								<h4 class="font-weight-light">Work Details:</h4>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Staff Designation:</label>
									<form:input type="text"
										class="form-control form-control-lg form-control-solid"
										id="staffDesignation" path="staffDesignation"
										placeholder="e.g HairDresser" />
									<form:errors id="validation_error" path="staffDesignation"></form:errors>
									<span class="form-text text-muted">Please select staff's
										designation</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Employment Start Date:</label>
									<div class="input-group date">
										<form:input type="text"
											class="form-control form-control-lg form-control-solid"
											readonly="readonly" path="staff_start_date"
											id="staff_start_date" />
										<span class="input-group-addon"><i
											class="lnr lnr-calendar-full"></i></span>
									</div>
									<form:errors id="validation_error" path="staff_start_date"></form:errors>
									<span class="form-text text-muted">Please select staff's
										employment start date</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Employment End Date:</label>
									<div class="input-group date">
										<form:input type="text"
											class="form-control form-control-lg form-control-solid"
											readonly="readonly" path="staff_end_date"
											id="staff_end_date" />
										<span class="input-group-addon"><i
											class="lnr lnr-calendar-full"></i></span>
									</div>
									<form:errors id="validation_error" path="staff_end_date"></form:errors>
									<span class="form-text text-muted">Please select staff's
										employment end date</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">In Time:</label>
									<div class="input-group timepicker">
										<form:input
											class="form-control form-control-lg form-control-solid"
											id="staff_in_time" readonly="readonly" path="staff_in_time"
											placeholder="Start time" type="text" />
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-time"></i></span>
									</div>
									<form:errors id="validation_error" path="staff_in_time"></form:errors>
									<span class="form-text text-muted">Please enter Staff in
										time</span>
								</div>
								<div class="form-group col-md-6">
									<label class="col-form-label">Out Time:</label>
									<div class="input-group timepicker">
										<form:input
											class="form-control form-control-lg form-control-solid"
											id="staff_out_time" readonly="readonly" path="staff_out_time"
											placeholder="Start time" type="text" />
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-time"></i></span>
									</div>
									<form:errors id="validation_error" path="staff_out_time"></form:errors>
									<span class="form-text text-muted">Please enter Staff
										out time</span>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label class="col-form-label">Work Days:</label>
									<div>
										<form:checkbox
												path="workdays" name="Checkboxes3" value="SUN" /> <span></span>
											Sun
										<form:checkbox
												path="workdays" checked="checked" name="Checkboxes3"
												value="MON" /> <span></span> Mon
										 <form:checkbox
												path="workdays" checked="checked" name="Checkboxes3"
												value="TUE" /> <span></span> Tue
										 <form:checkbox
												path="workdays" checked="checked" name="Checkboxes3"
												value="WED" /> <span></span> Wed
										 <form:checkbox
												path="workdays" checked="checked" name="Checkboxes3"
												value="THR" /> <span></span> Thur
										 <form:checkbox
												path="workdays" checked="checked" name="Checkboxes3"
												value="FRI" /> <span></span> Fri
										 <form:checkbox
												path="workdays" name="Checkboxes3" value="SAT" /> <span></span>
											Sat									
									</div>
								</div>
							</div>
							<div class="form-group">
								<button type="button"  class="btn btn-lg"
										style="float: right; margin: 3%;background-color: #252c35;color: white;"
								 	onclick="submitForm()">Submit</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
		$(function() {
			$('#staff_start_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#staff_end_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#staff_birthday').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#staff_in_time').timepicker({});
			$('#staff_out_time').timepicker({});
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/staff/add-staff.js"></script>
</body>
</html>