<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form" id="clientForm" name="clientForm"
	modelAttribute="clientForm" method="post" autocomplete="off">
	<ul class="nav nav-tabs nav-tabs-line mb-5">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#basicDetails"> <span class="nav-icon"><i
					class="flaticon2-user"></i></span> <span class="nav-text">Basic
					Details</span>
		</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#membershipDetails"> <span class="nav-icon"><i
					class="flaticon-user-settings"></i></span> <span class="nav-text">Membership
					Details</span>
		</a></li>
	</ul>
	<div class="tab-content mt-5" id="tabContent">
		<div class="tab-pane fade show active" id="basicDetails"
			role="tabpanel" aria-labelledby="basicDetails">
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Full Name:</label>
					<form:input type="text" id="fullName" path="fullName"
						class="form-control" placeholder="Enter full name" />
					<form:errors id="validation_error" path="fullName"></form:errors>
					<span id="fullName_span" class="form-text text-muted ">Please
						enter client's full name</span>
				</div>
				<div class="col-lg-6">
					<label>Contact Number:</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-phone"></i></span>
						</div>
						<form:input type="text" path="mobileNumber" class="form-control"
							id="mobileNumber" placeholder="client number" />
					</div>
					<form:errors id="validation_error" path="mobileNumber"></form:errors>
					<span id="mobileNumber_span" class="form-text text-muted ">Please
						enter client's contact number</span>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Address:</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-map-marker"></i></span>
						</div>
						<form:input type="text" class="form-control" path="client_address"
							id="client_address" placeholder="client address" />
					</div>
					<form:errors id="validation_error" path="client_address"></form:errors>
					<span id="client_address_span" class="form-text text-muted">Please
						enter client's address</span>
				</div>
				<div class="col-lg-6">
					<label>Pincode:</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-bookmark-o"></i></span>
						</div>
						<form:input type="text" id="clientPincode" class="form-control"
							path="clientPincode" placeholder="client pincode" />
					</div>
					<form:errors id="validation_error" path="clientPincode"></form:errors>
					<span id="clientPincode_span" class="form-text text-muted ">Please
						enter client's pincode</span>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Gender:</label>
					<div class="radio-inline">
						<label class="radio radio-solid"> <form:radiobutton
								path="gender" name="example_2" checked="checked" value="Male" />
							<span></span> Male
						</label> <label class="radio radio-solid"> <form:radiobutton
								path="gender" name="example_2" value="Female" /> <span></span>
							Female
						</label>
					</div>
					<span class="form-text text-muted">Please select client's
						gender</span>
				</div>
				<div class="col-lg-6">
					<label>Email Id:</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-inbox"></i></span>
						</div>
						<form:input type="text" id="emailId" path="emailId"
							class="form-control" placeholder="Enter email id" />
					</div>
					<form:errors id="validation_error" path="emailId"></form:errors>
					<span id="emailId_span" class="form-text text-muted ">Please
						enter client's Email id</span>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Birthday:</label>
					<div class="input-group date">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-calendar"></i></span>
						</div>
						<form:input type="text" class="form-control" readonly="readonly"
							path="birthday" id="client_birthday" />
					</div>
					<form:errors id="validation_error" path="birthday"></form:errors>
					<span class="form-text text-muted ">Please select client's
						birthday</span>
				</div>
				<div class="col-lg-6">
					<label>Loyalty Points:</label>
					<div class="input-group">
						<form:input path="clientLoyaltyPoints" id="loyaltyPoints"
							type="text" class="form-control" placeholder="e.g. 100" />
					</div>
					<form:errors id="validation_error" path="clientLoyaltyPoints"></form:errors>
					<span id="clientLoyaltyPoints_span" class="form-text text-muted">Please
						enter client loyalty points</span>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Client Created Date:</label>
					<div class="input-group date">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-calendar"></i></span>
						</div>
						<form:input type="text" class="form-control" readonly="readonly"
							path="clientCreatedDate" id="clientCreatedDate" />
					</div>
					<span class="form-text text-muted ">Please select client
						created date</span>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="membershipDetails" role="tabpanel"
			aria-labelledby="membershipDetails">
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Membership:</label>
					<div class="input-group">
						<form:select path="clientPlan" class="form-control select2"
							id="client_plan_dropdown" name="param">
						</form:select>
					</div>
					<span class="form-text text-muted">Please select client
						membership</span>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-6">
					<label>Client Start Date:</label>
					<div class="input-group date">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-calendar"></i></span>
						</div>
						<form:input type="text" class="form-control" readonly="readonly"
							path="client_start_date" id="client_start_date" />
					</div>
					<span class="form-text text-muted ">Please select client's
						start date</span>
				</div>
				<div class="col-lg-6">
					<label>Client End Date:</label>
					<div class="input-group date">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="la la-calendar"></i></span>
						</div>
						<form:input type="text" class="form-control" readonly="readonly"
							path="client_end_date" id="client_end_date" />
					</div>
					<span class="form-text text-muted ">Please select client's
						end date</span>
				</div>
			</div>
		</div>
	</div>
</form:form>