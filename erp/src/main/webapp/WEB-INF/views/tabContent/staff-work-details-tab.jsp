<jsp:include page="../global-imports/all-global-imports.jsp" />
<div class="card-body">
	<div class="form-group row">
		<div class="col-lg-12">
			<label>Staff Designation:</label>
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="e.g HairDresser" />
			</div>
			<span class="form-text text-muted">Please select staff's
				designation</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Employment Start Date:</label>
			<div class="input-group date">
				<input type="text" class="form-control" readonly
					id="kt_datepicker_3" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-calendar"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please select staff's
				employment start date</span>
		</div>
		<div class="col-lg-6">
			<label>Employment End Date:</label>
			<div class="input-group date">
				<input type="text" class="form-control" readonly
					id="kt_datepicker_employment_end_date" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-calendar"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please select staff's
				employment end date</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-6 col-md-9 col-sm-12">
			<label>In Time:</label>
			<div class="input-group timepicker">
				<input class="form-control" id="kt_timepicker_1" readonly
					placeholder="Start time" type="text" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-clock-o"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please enter Staff in time</span>
		</div>
		<div class="col-lg-6 col-md-9 col-sm-12">
			<label>Out Time:</label>
			<div class="input-group timepicker">
				<input class="form-control" id="kt_timepicker_2" readonly
					placeholder="Duration" type="text" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-clock-o"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please enter Staff out time</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-12">
			<label>Work days</label>
			<div class="checkbox-inline">
				<label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Mon
				</label> <label class="checkbox"> <input type="checkbox"
					name="Checkboxes3" /> <span></span> Tue
				</label> <label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Wed
				</label> <label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Thur
				</label> <label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Fri
				</label> <label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Sat
				</label> <label class="checkbox"> <input type="checkbox"
					checked="checked" name="Checkboxes3" /> <span></span> Sun
				</label>
			</div>
		</div>
	</div>
</div>
<script src="assets/js/pages/datePicker.js"></script>
<script src="assets/js/pages/timePicker.js"></script>