<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
		<link href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.4" rel="stylesheet" type="text/css" />
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Appointments</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newAppointmentModal">Add
					New</button>
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
		<div class="container">
			<!--begin::Card-->
			<div class="card card-custom">
				<div class="card-body">
					<!--begin: Datatable-->
					<table class="table table-bordered table-hover table-checkable"
						id="kt_datatable" style="margin-top: 13px !important">
						<thead>
							<tr>
								<th>Appointment ID</th>
								<th>Appointment Date</th>
								<th>Appointment Time</th>
								<th>Service</th>
								<th>Duration</th>
								<th>Staff</th>
								<th>Cost</th>
								<th>Client</th>
								<th>Status</th>			
								<th>Actions</th>					
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>22/05/2020</td>
								<td>9.15</td>
								<td>Blow Dry</td>
								<td>2 hrs</td>								
								<td>John Doe</td>
								<td>500</td>
								<td>Sushant Singh</td>
								<td>Booked</td>								
								<td nowrap="nowrap"></td>
							</tr>							
							<tr>
								<td>2</td>
								<td>20/04/2020</td>
								<td>12.15</td>
								<td>Head Massage</td>
								<td>1 hr</td>								
								<td>Ricky Hunt</td>
								<td>200</td>
								<td>Michael Scofield</td>
								<td>Booked</td>								
								<td nowrap="nowrap"></td>
							</tr>							
							<tr>
								<td>3</td>
								<td>14/05/2020</td>
								<td>10.00</td>
								<td>Hair Cut</td>
								<td>1 hr</td>								
								<td>John Doe</td>
								<td>300</td>
								<td>M.S.Dhoni</td>
								<td>Booked</td>								
								<td nowrap="nowrap"></td>
							</tr>
							<tr>
								<td>4</td>
								<td>19/03/2020</td>
								<td>16.00</td>
								<td>Blow Dry</td>
								<td>2 hrs</td>								
								<td>John Doe</td>
								<td>500</td>
								<td>Sachin Tendulkar</td>
								<td>Booked</td>								
								<td nowrap="nowrap"></td>
							</tr>											
						</tbody>
					</table>
					<!--end: Datatable-->
				</div>
			</div>
			<!--end::Card-->
		</div>
	</div>
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newAppointmentModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newAppointmentModalLabel">New
					Appointment</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="forms/new-appointment-form.jsp" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary font-weight-bold">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<script src="http://localhost:8081/assets/js/pages/my-script.js"></script>
<script
	src="assets/js/pages/crud/datatables/data-sources/html.js?v=7.0.4"></script>
<script
	src="assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.4"></script>