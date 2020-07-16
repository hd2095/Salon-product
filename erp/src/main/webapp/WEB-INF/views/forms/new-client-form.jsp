<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="clientForm" name="clientForm"
	modelAttribute="clientForm" method="post" autocomplete="off">
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Full Name:</label>
				<form:input type="text" path="fullName" class="form-control"
					placeholder="Enter full name" />
				<form:errors id="validation_error" path="fullName"></form:errors>
				<span class="form-text text-muted span-info">Please enter
					client's full name</span>
			</div>
			<div class="col-lg-6">
				<label>Contact Number:</label>
				<div class="input-group">
					<form:input type="text" path="mobileNumber" class="form-control"
						placeholder="Enter contact number" />
					<span class="input-group-addon"><i
						class="lnr lnr-phone-handset"></i></span>
				</div>
				<form:errors id="validation_error" path="mobileNumber"></form:errors>
				<span class="form-text text-muted span-info">Please enter
					client's contact number</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Address:</label>
				<div class="input-group">
					<form:input type="text" class="form-control" path="client_address"
						placeholder="Enter client's address" />
					<span class="input-group-addon"><i class="lnr lnr-home"></i></span>
				</div>
				<form:errors id="validation_error" path="client_address"></form:errors>
				<span class="form-text text-muted span-info">Please enter
					client's address</span>
			</div>
			<div class="col-lg-6">
				<label>Pincode:</label>
				<div class="input-group">
					<form:input type="text" class="form-control" path="clientPincode"
						placeholder="Enter client's pincode" />
					<span class="input-group-addon"><i
						class="lnr lnr-map-marker"></i></span>
				</div>
				<form:errors id="validation_error" path="clientPincode"></form:errors>
				<span class="form-text text-muted span-info">Please enter
					client's pincode</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Gender:</label>
				<div>
					<form:radiobutton path="gender" name="example_2" checked="checked"
						value="Male" />
					Male <span></span>
					<form:radiobutton style="margin-left:5%;" path="gender" name="example_2" value="Female" />
					Female <span></span>
				</div>
				<span class="form-text text-muted">Please select client's
					gender</span>
			</div>
			<div class="col-lg-6">
				<label>Email Id:</label>
				<div class="input-group">
					<form:input type="text" path="emailId" class="form-control"
						placeholder="Enter email id" />
					<span class="input-group-addon"><i class="lnr lnr-inbox"></i></span>
				</div>
				<form:errors id="validation_error" path="emailId"></form:errors>
				<span class="form-text text-muted span-info">Please enter
					client's Email id</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Birthday:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="birthday" id="client_birthday" />
					<span class="input-group-addon"><i
						class="lnr lnr-calendar-full"></i></span>
				</div>
				<form:errors id="validation_error" path="birthday"></form:errors>
				<span class="form-text text-muted span-info">Please select
					client's birthday</span>
			</div>
			<div class="col-lg-6">
				<label>Is member:</label>
				<div class="input-group">
					<label> <form:checkbox onclick="isMemberValue()"
							path="isMember" id="isMember" name="isMember" value="1" /> <span></span>
					</label>
				</div>
			</div>
		</div>
		<div class="form-group row client-duration-details"
			style="display: none;">
			<div class="col-lg-6">
				<label>Client Start Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="client_start_date" id="client_start_date" />
					<span class="input-group-addon"><i
						class="lnr lnr-calendar-full"></i></span>
				</div>
				<span class="form-text text-muted span-info">Please select
					client's start date</span>
			</div>
			<div class="col-lg-6">
				<label>Client End Date:</label>
				<div class="input-group date">
					<form:input type="text" class="form-control" readonly="readonly"
						path="client_end_date" id="client_end_date" />
					<span class="input-group-addon"><i
						class="lnr lnr-calendar-full"></i></span>
				</div>
				<span class="form-text text-muted span-info">Please select
					client's end date</span>
			</div>
		</div>
	</div>
</form:form>
<script type='text/javascript'>
		$(function() {
			$('#client_end_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'bottom auto',
				clearBtn : true
			});
			$('#client_start_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'bottom auto',
				clearBtn : true
			});
			$('#client_birthday').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'bottom auto',
				clearBtn : true
			});
		});
	function isMemberValue() {
		if ($('input[name="isMember"]:checked').val()) {
			$('.client-duration-details').show();
		} else {
			$('.client-duration-details').hide();
		}
	}
</script>