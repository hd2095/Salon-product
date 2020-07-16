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
						<h3 class="panel-title">Edit Staff</h3>
						<p class="panel-subtitle">Enter Staff details and submit</p>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="editStaffForm" class="form"
							action="staff/editStaff" method="post" id="editStaffForm"
							name="editStaffForm">
							<form:hidden id="edit_staffId" path="staffId" />
							<div class="row">
								<h4 class="font-weight-light">Basic Details:</h4>
								<div class="form-row">
									<div class="form-group col-md-6">
										<label class="col-form-label">Full Name:</label>
										<form:input type="text" path="fullName" id="edit_staff_fullName"
											class="form-control form-control-lg form-control-solid"
											placeholder="Enter full name" />
										<form:errors id="validation_error" path="fullName"></form:errors>
										<span class="form-text text-muted">Please enter staff's
											full name</span>
									</div>
									<div class="form-group col-md-6">
										<label class="col-form-label">Contact Number:</label>
										<form:input type="text" path="mobileNumber" id="edit_staff_mobileNumber"
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
										<form:input type="text" path="staff_address" id="edit_staff_address"
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
											id="edit_staff_pincode" path="staffPincode"
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
											<form:radiobutton path="gender" name="edit_gender"
												checked="checked" value="Male" />
											Male <span></span>
											<form:radiobutton style="margin-left:5%;" path="gender"
												name="edit_gender" value="Female" />
											Female <span></span>
										</div>
										<span class="form-text text-muted">Please select
											staff's gender</span>
									</div>
									<div class="form-group col-md-6">
										<label class="col-form-label">Email Id:</label>
										<form:input type="text" path="emailId" id="edit_staff_emailId"
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
												id="edit_staff_birthday" />
											<span class="input-group-addon"><i
												class="lnr lnr-calendar-full"></i></span>
										</div>
										<form:errors id="validation_error" path="birthday"></form:errors>
										<span class="form-text text-muted">Please select
											staff's birthday</span>
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
										id="edit_staff_designation" path="staffDesignation"
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
											id="edit_staff_start_date" />
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
											id="edit_staff_end_date" />
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
											id="edit_staff_in_time" readonly="readonly" path="staff_in_time"
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
											id="edit_staff_out_time" readonly="readonly" path="staff_out_time"
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
										<form:checkbox path="workdays" name="edit_workdays" value="SUN" />
										<span></span> Sun
										<form:checkbox path="workdays" 
											name="edit_workdays" value="MON" />
										<span></span> Mon
										<form:checkbox path="workdays" 
											name="edit_workdays" value="TUE" />
										<span></span> Tue
										<form:checkbox path="workdays" 
											name="edit_workdays" value="WED" />
										<span></span> Wed
										<form:checkbox path="workdays" 
											name="edit_workdays" value="THR" />
										<span></span> Thur
										<form:checkbox path="workdays" 
											name="edit_workdays" value="FRI" />
										<span></span> Fri
										<form:checkbox path="workdays" name="edit_workdays" value="SAT" />
										<span></span> Sat
									</div>
								</div>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-lg"
									style="float: right; margin: 3%; background-color: #252c35; color: white;"
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
			$('#edit_staff_start_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#edit_staff_end_date').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#edit_staff_birthday').datepicker({
				todayHighlight : true,
				autoclose : true,
				orientation : 'top auto',
				clearBtn : true
			});
			$('#edit_staff_in_time').timepicker({});
			$('#edit_staff_out_time').timepicker({});
		});
		function setCheckBox(array) {
			for (var i = 0; i < array.length; i++) {
				$('input[name="workdays"]').each(function() {
					if(this.value == array[i]){
						this.checked = true;
					}
				});
			}
		}		
		jQuery(document).ready(function() {
			var data = '${editStaffForm.workdays}';
			data = data.split(',');
			setCheckBox(data);
		});
	</script>
	<script>
		var HOST_URL = "${pageContext.request.contextPath}"
	</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/staff/edit-staff.js"></script>
</body>
</html>
<%-- <!DOCTYPE html>
<html lang="en">
<head><base href="../../">
<jsp:include page="../layout/side-nav.jsp" />
<jsp:include page="../layout/header.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--begin::Page Custom Styles(used by this page)-->
<link href="assets/css/pages/wizard/wizard-1.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
<!--end::Page Custom Styles-->
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
						Staff</h5>
					<!--end::Page Title-->
					<!--begin::Separator-->
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-5 bg-gray-200"></div>
					<!--end::Separator-->
					<!--begin::Search Form-->
					<div class="d-flex align-items-center" id="kt_subheader_search">
						<span class="text-dark-50 font-weight-bold"
							id="kt_subheader_total">Enter staff details and submit</span>
					</div>
					<!--end::Search Form-->
				</div>
				<!--begin::Toolbar-->
				<div class="d-flex align-items-center">
					<!--begin::Button-->
					<a href="staff" class="btn btn-default font-weight-bold">Back</a>
					<!--end::Button-->
					<div class="btn-group ml-2">
						<button type="button" class="btn btn-primary font-weight-bold">Submit</button>
					</div>
				</div>
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<!--begin::Card-->
				<div class="card card-custom">
					<div class="card-body p-0">
						<div class="wizard wizard-1" id="edit-staff-wizard"
							data-wizard-state="step-first" data-wizard-clickable="true">
							<div class="kt-grid__item">
								<!--begin::Wizard Nav-->
								<div class="wizard-nav border-bottom">
									<div class="wizard-steps p-8 p-lg-10">
										<div class="wizard-step" data-wizard-type="step"
											data-wizard-state="current">
											<div class="wizard-label">
												<span class="svg-icon svg-icon-4x wizard-icon"> <!--begin::Svg Icon | path:assets/media/svg/icons/Communication/Chat-check.svg-->
													<svg width="24px" height="24px" viewBox="0 0 24 24"
														version="1.1" xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink">
    														<g id="Stockholm-icons-/-General-/-User" stroke="none"
															stroke-width="1" fill="none" fill-rule="evenodd">
        															<polygon id="Shape" points="0 0 24 0 24 24 0 24"></polygon>
        																<path
															d="M12,11 C9.790861,11 8,9.209139 8,7 C8,4.790861 9.790861,3 12,3 C14.209139,3 16,4.790861 16,7 C16,9.209139 14.209139,11 12,11 Z"
															id="Mask" fill="#000000" fill-rule="nonzero"
															opacity="0.3"></path>
        																<path
															d="M3.00065168,20.1992055 C3.38825852,15.4265159 7.26191235,13 11.9833413,13 C16.7712164,13 20.7048837,15.2931929 20.9979143,20.2 C21.0095879,20.3954741 20.9979143,21 20.2466999,21 C16.541124,21 11.0347247,21 3.72750223,21 C3.47671215,21 2.97953825,20.45918 3.00065168,20.1992055 Z"
															id="Mask-Copy" fill="#000000" fill-rule="nonzero"></path>
				    														</g>																			
												</span>
												<h3 class="wizard-title">Basic Details</h3>
											</div>
											<span class="svg-icon svg-icon-xl wizard-arrow"> <!--begin::Svg Icon | path:assets/media/svg/icons/Navigation/Arrow-right.svg-->
												<svg xmlns="http://www.w3.org/2000/svg"
													xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
													height="24px" viewBox="0 0 24 24" version="1.1">
																	<g stroke="none" stroke-width="1" fill="none"
														fill-rule="evenodd">
																		<polygon points="0 0 24 0 24 24 0 24" />
																		<rect fill="#000000" opacity="0.3"
														transform="translate(12.000000, 12.000000) rotate(-90.000000) translate(-12.000000, -12.000000)"
														x="11" y="5" width="2" height="14" rx="1" />
																		<path
														d="M9.70710318,15.7071045 C9.31657888,16.0976288 8.68341391,16.0976288 8.29288961,15.7071045 C7.90236532,15.3165802 7.90236532,14.6834152 8.29288961,14.2928909 L14.2928896,8.29289093 C14.6714686,7.914312 15.281055,7.90106637 15.675721,8.26284357 L21.675721,13.7628436 C22.08284,14.136036 22.1103429,14.7686034 21.7371505,15.1757223 C21.3639581,15.5828413 20.7313908,15.6103443 20.3242718,15.2371519 L15.0300721,10.3841355 L9.70710318,15.7071045 Z"
														fill="#000000" fill-rule="nonzero"
														transform="translate(14.999999, 11.999997) scale(1, -1) rotate(90.000000) translate(-14.999999, -11.999997)" />
																	</g>
																</svg> <!--end::Svg Icon-->
											</span>
										</div>
										<div class="wizard-step" data-wizard-type="step">
											<div class="wizard-label">
												<span class="svg-icon svg-icon-4x wizard-icon"> <!--begin::Svg Icon | path:assets/media/svg/icons/Devices/Display1.svg-->
													<svg xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
														height="24px" viewBox="0 0 24 24" version="1.1">
																		<g stroke="none" stroke-width="1" fill="none"
															fill-rule="evenodd">
																			<rect x="0" y="0" width="24" height="24" />
																			<path
															d="M11,20 L11,17 C11,16.4477153 11.4477153,16 12,16 C12.5522847,16 13,16.4477153 13,17 L13,20 L15.5,20 C15.7761424,20 16,20.2238576 16,20.5 C16,20.7761424 15.7761424,21 15.5,21 L8.5,21 C8.22385763,21 8,20.7761424 8,20.5 C8,20.2238576 8.22385763,20 8.5,20 L11,20 Z"
															fill="#000000" opacity="0.3" />
																			<path
															d="M3,5 L21,5 C21.5522847,5 22,5.44771525 22,6 L22,16 C22,16.5522847 21.5522847,17 21,17 L3,17 C2.44771525,17 2,16.5522847 2,16 L2,6 C2,5.44771525 2.44771525,5 3,5 Z M4.5,8 C4.22385763,8 4,8.22385763 4,8.5 C4,8.77614237 4.22385763,9 4.5,9 L13.5,9 C13.7761424,9 14,8.77614237 14,8.5 C14,8.22385763 13.7761424,8 13.5,8 L4.5,8 Z M4.5,10 C4.22385763,10 4,10.2238576 4,10.5 C4,10.7761424 4.22385763,11 4.5,11 L7.5,11 C7.77614237,11 8,10.7761424 8,10.5 C8,10.2238576 7.77614237,10 7.5,10 L4.5,10 Z"
															fill="#000000" />
																		</g>
																	</svg> <!--end::Svg Icon-->
												</span>
												<h3 class="wizard-title">Work Details</h3>
											</div>
											<span class="svg-icon svg-icon-xl wizard-arrow"> <!--begin::Svg Icon | path:assets/media/svg/icons/Navigation/Arrow-right.svg-->
												<svg xmlns="http://www.w3.org/2000/svg"
													xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
													height="24px" viewBox="0 0 24 24" version="1.1">
																	<g stroke="none" stroke-width="1" fill="none"
														fill-rule="evenodd">
																		<polygon points="0 0 24 0 24 24 0 24" />
																		<rect fill="#000000" opacity="0.3"
														transform="translate(12.000000, 12.000000) rotate(-90.000000) translate(-12.000000, -12.000000)"
														x="11" y="5" width="2" height="14" rx="1" />
																		<path
														d="M9.70710318,15.7071045 C9.31657888,16.0976288 8.68341391,16.0976288 8.29288961,15.7071045 C7.90236532,15.3165802 7.90236532,14.6834152 8.29288961,14.2928909 L14.2928896,8.29289093 C14.6714686,7.914312 15.281055,7.90106637 15.675721,8.26284357 L21.675721,13.7628436 C22.08284,14.136036 22.1103429,14.7686034 21.7371505,15.1757223 C21.3639581,15.5828413 20.7313908,15.6103443 20.3242718,15.2371519 L15.0300721,10.3841355 L9.70710318,15.7071045 Z"
														fill="#000000" fill-rule="nonzero"
														transform="translate(14.999999, 11.999997) scale(1, -1) rotate(90.000000) translate(-14.999999, -11.999997)" />
																	</g>
																</svg> <!--end::Svg Icon-->
											</span>
										</div>
										<div class="wizard-step" data-wizard-type="step">
											<div class="wizard-label">
												<span class="svg-icon svg-icon-4x wizard-icon"> <!--begin::Svg Icon | path:assets/media/svg/icons/General/Notification2.svg-->
													<svg xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
														height="24px" viewBox="0 0 24 24" version="1.1">
																		<g stroke="none" stroke-width="1" fill="none"
															fill-rule="evenodd">
																			<rect x="0" y="0" width="24" height="24" />
																			<path
															d="M13.2070325,4 C13.0721672,4.47683179 13,4.97998812 13,5.5 C13,8.53756612 15.4624339,11 18.5,11 C19.0200119,11 19.5231682,10.9278328 20,10.7929675 L20,17 C20,18.6568542 18.6568542,20 17,20 L7,20 C5.34314575,20 4,18.6568542 4,17 L4,7 C4,5.34314575 5.34314575,4 7,4 L13.2070325,4 Z"
															fill="#000000" />
																			<circle fill="#000000" opacity="0.3" cx="18.5"
															cy="5.5" r="2.5" />
																		</g>
																	</svg> <!--end::Svg Icon-->
												</span>
												<h3 class="wizard-title">Review and Submit</h3>
											</div>
											<span class="svg-icon svg-icon-xl wizard-arrow last">
												<!--begin::Svg Icon | path:assets/media/svg/icons/Navigation/Arrow-right.svg-->
												<svg xmlns="http://www.w3.org/2000/svg"
													xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
													height="24px" viewBox="0 0 24 24" version="1.1">
																	<g stroke="none" stroke-width="1" fill="none"
														fill-rule="evenodd">
																		<polygon points="0 0 24 0 24 24 0 24" />
																		<rect fill="#000000" opacity="0.3"
														transform="translate(12.000000, 12.000000) rotate(-90.000000) translate(-12.000000, -12.000000)"
														x="11" y="5" width="2" height="14" rx="1" />
																		<path
														d="M9.70710318,15.7071045 C9.31657888,16.0976288 8.68341391,16.0976288 8.29288961,15.7071045 C7.90236532,15.3165802 7.90236532,14.6834152 8.29288961,14.2928909 L14.2928896,8.29289093 C14.6714686,7.914312 15.281055,7.90106637 15.675721,8.26284357 L21.675721,13.7628436 C22.08284,14.136036 22.1103429,14.7686034 21.7371505,15.1757223 C21.3639581,15.5828413 20.7313908,15.6103443 20.3242718,15.2371519 L15.0300721,10.3841355 L9.70710318,15.7071045 Z"
														fill="#000000" fill-rule="nonzero"
														transform="translate(14.999999, 11.999997) scale(1, -1) rotate(90.000000) translate(-14.999999, -11.999997)" />
																	</g>
																</svg> <!--end::Svg Icon-->
											</span>
										</div>
									</div>
								</div>
								<!--end::Wizard Nav-->
							</div>
							<div
								class="row justify-content-center my-10 px-8 my-lg-15 px-lg-10">
								<div class="col-xl-12 col-xxl-7">
									<!--begin::Form Wizard-->

										<!--begin::Step 1-->
										<div class="pb-5" data-wizard-type="step-content"
											data-wizard-state="current">
											<h3 class="mb-10 font-weight-bold text-dark">Basic
												Details:</h3>
											<div class="row">
												<div class="col-xl-12">
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Full
															Name:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text" path="fullName"
																id="edit_staff_fullName"
																class="form-control form-control-lg form-control-solid"
																placeholder="Enter full name" />
															<form:errors id="validation_error" path="fullName"></form:errors>
															<span class="form-text text-muted">Please enter
																staff's full name</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Contact
															Number:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text" path="mobileNumber"
																id="edit_staff_mobileNumber"
																class="form-control form-control-lg form-control-solid"
																placeholder="Enter contact number" />
															<form:errors id="validation_error" path="mobileNumber"></form:errors>
															<span class="form-text text-muted">Please enter
																staff's contact number</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Address:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text" path="staff_address"
																id="edit_staff_address"
																class="form-control form-control-lg form-control-solid"
																placeholder="Enter staff's address" />
															<form:errors id="validation_error" path="staff_address"></form:errors>
															<span class="form-text text-muted">Please enter
																staff's address</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Pincode:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text"
																class="form-control form-control-lg form-control-solid"
																id="edit_staff_pincode" path="staffPincode"
																placeholder="Enter staff's pincode" />
															<form:errors id="validation_error" path="staffPincode"></form:errors>
															<span class="form-text text-muted">Please enter
																staff's pincode</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Gender:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="radio-inline">
																<label class="radio radio-solid"> <form:radiobutton
																		path="gender" name="edit_gender" checked="checked"
																		value="Male" />Male <span></span>
																</label> <label class="radio radio-solid"> <form:radiobutton
																		path="gender" name="edit_gender" value="Female" />
																	Female <span></span>
																</label>
															</div>
															<span class="form-text text-muted">Please select
																staff's gender</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Email
															Id:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text" path="emailId"
																id="edit_staff_emailId"
																class="form-control form-control-lg form-control-solid"
																placeholder="Enter email id" />
															<form:errors id="validation_error" path="emailId"></form:errors>
															<span class="form-text text-muted span-info">Please
																enter staff's Email id</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Birthday:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="input-group date">
																<form:input type="text"
																	class="form-control form-control-lg form-control-solid"
																	readonly="readonly" path="birthday"
																	id="edit_staff_birthday" />
																<div class="input-group-append">
																	<span class="input-group-text"> <i
																		class="la la-calendar"></i>
																	</span>
																</div>
															</div>
															<form:errors id="validation_error" path="birthday"></form:errors>
															<span class="form-text text-muted">Please select
																staff's birthday</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="pb-5" data-wizard-type="step-content">
											<div class="row">
												<div class="col-xl-12">
													<div class="form-group row">
														<div class="col-lg-9 col-xl-6">
															<h3 class="kt-section__title kt-section__title-md">Work
																Details:</h3>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Staff
															Designation:</label>
														<div class="col-lg-9 col-xl-9">
															<form:input type="text"
																class="form-control form-control-lg form-control-solid"
																id="edit_staff_designation" path="staffDesignation"
																placeholder="e.g HairDresser" />
															<form:errors id="validation_error"
																path="staffDesignation"></form:errors>
															<span class="form-text text-muted">Please select
																staff's designation</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Employment
															Start Date:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="input-group date">
																<form:input type="text"
																	class="form-control form-control-lg form-control-solid"
																	readonly="readonly" path="staff_start_date"
																	id="edit_staff_start_date" />
																<div class="input-group-append">
																	<span class="input-group-text"> <i
																		class="la la-calendar"></i>
																	</span>
																</div>
															</div>
															<form:errors id="validation_error"
																path="staff_start_date"></form:errors>
															<span class="form-text text-muted">Please select
																staff's employment start date</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Employment
															End Date:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="input-group date">
																<form:input type="text"
																	class="form-control form-control-lg form-control-solid"
																	readonly="staff_end_date" path="staff_end_date"
																	id="edit_staff_end_date" />
																<div class="input-group-append">
																	<span class="input-group-text"> <i
																		class="la la-calendar"></i>
																	</span>
																</div>
															</div>
															<form:errors id="validation_error" path="staff_end_date"></form:errors>
															<span class="form-text text-muted">Please select
																staff's employment end date</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">In
															Time:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="input-group timepicker">
																<form:input
																	class="form-control form-control-lg form-control-solid"
																	id="edit_staff_in_time" readonly="readonly"
																	path="staff_in_time" placeholder="Start time"
																	type="text" />
																<div class="input-group-append">
																	<span class="input-group-text"> <i
																		class="la la-clock-o"></i>
																	</span>
																</div>
															</div>
															<form:errors id="validation_error" path="staff_in_time"></form:errors>
															<span class="form-text text-muted">Please enter
																Staff in time</span>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-xl-3 col-lg-3 col-form-label">Out
															Time:</label>
														<div class="col-lg-9 col-xl-9">
															<div class="input-group timepicker">
																<form:input
																	class="form-control form-control-lg form-control-solid"
																	id="edit_staff_out_time" readonly="readonly"
																	path="staff_out_time" placeholder="Start time"
																	type="text" />
																<div class="input-group-append">
																	<span class="input-group-text"> <i
																		class="la la-clock-o"></i>
																	</span>
																</div>
															</div>
															<form:errors id="validation_error" path="staff_out_time"></form:errors>
															<span class="form-text text-muted">Please enter
																Staff out time</span>
														</div>
													</div>
													<div class="form-group row align-items-center">
														<label class="col-xl-3 col-lg-3 col-form-label">Work
															Days:</label>
														<div class="col-lg-9 col-xl-6">
															<div class="checkbox-inline">
																<label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="SUN" /> <span></span>
																	Sun
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="MON" /> <span></span>
																	Mon
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="TUE" /> <span></span>
																	Tue
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="WED" /> <span></span>
																	Wed
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="THR" /> <span></span>
																	Thur
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="FRI" /> <span></span>
																	Fri
																</label> <label class="checkbox"> <form:checkbox
																		path="workdays" name="edit_workdays" value="SAT" /> <span></span>
																	Sat
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="pb-5" data-wizard-type="step-content">
											<h4 class="mb-10 font-weight-bold">Review your Details
												and Submit</h4>
											<h6 class="font-weight-bold mb-3">Basic Details:</h6>
											<table class="w-100">
												<tr>
													<td class="font-weight-bold text-muted">Full Name:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_full_name"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Contact
														Number:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_number"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Email id:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_email"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Address:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_address"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Pincode:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_pincode"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Gender:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_gender"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Birthday:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_birthday"></td>
												</tr>
											</table>
											<div class="separator separator-dashed my-5"></div>
											<h6 class="font-weight-bold mb-3">Work Details:</h6>
											<table class="w-100">
												<tr>
													<td class="font-weight-bold text-muted">Designation:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_designation"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Employment
														Start Date:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_start_date"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Employment End
														Date:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_end_date"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">In time:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_inTime"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Out time:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_outTime"></td>
												</tr>
												<tr>
													<td class="font-weight-bold text-muted">Work days:</td>
													<td class="font-weight-bold text-right"
														id="edit_selected_staff_workdays"></td>
												</tr>
											</table>
										</div>
										<!--begin::Actions-->
										<div
											class="d-flex justify-content-between border-top mt-5 pt-10">
											<div class="mr-2">
												<button type="button"
													class="btn btn-light-primary font-weight-bold text-uppercase px-9 py-4"
													data-wizard-type="action-prev">Previous</button>
											</div>
											<div>
												<button type="button"
													class="btn btn-success font-weight-bold text-uppercase px-9 py-4"
													data-wizard-type="action-submit" onclick="submitForm()">Submit</button>
												<button type="button"
													class="btn btn-primary font-weight-bold text-uppercase px-9 py-4"
													data-wizard-type="action-next">Next Step</button>
											</div>
										</div>
										<!--end::Actions-->
									</form:form>
									<!--end::Form Wizard-->
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script>var HOST_URL = "${pageContext.request.contextPath}"</script>
	<script src="assets/js/pages/my-script.js"></script>
	<script src="assets/js/pages/datePicker.js"></script>
	<script src="assets/js/pages/timePicker.js"></script>
	<script src="assets/js/pages/staff/edit-staff.js"></script>
</body>
<script type="text/javascript">

	function setCheckBox(array) {
		for (var i = 0; i < array.length; i++) {
			$('input[name="workdays"]').each(function() {
				if(this.value == array[i]){
					this.checked = true;
				}
			});
		}
	}
	
	jQuery(document).ready(function() {
		var data = '${editStaffForm.workdays}';
		data = data.split(',');
		setCheckBox(data);
	});
</script>
</html> --%>