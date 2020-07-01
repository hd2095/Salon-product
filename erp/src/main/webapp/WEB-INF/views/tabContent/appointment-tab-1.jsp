<div class="card-body">
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Date:</label>
			<div class="input-group date">
				<input type="text" class="form-control" readonly
					id="kt_datepicker_4" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-calendar"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please enter appointment
				Date</span>
		</div>
		<div class="col-lg-6">
			<label>Appointment Time:</label>
			<div class="input-group timepicker">
				<input class="form-control" id="kt_timepicker_1" readonly
					placeholder="Start time" type="text" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-clock-o"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please enter appointment
				time</span>
		</div>
	</div>
	<div class="form-group row">
		<div class=" col-lg-6">
			<label>Service:</label> <select class="form-control select2"
				id="kt_select2_10" name="param">
				<optgroup label="Hair">
					<option value="AK">Hair Cut</option>
					<option value="HI">Hair Massage</option>
				</optgroup>
				<optgroup label="Bdoy">
					<option value="CA">Massage</option>
				</optgroup>
			</select><span class="form-text text-muted">Please enter appointment
				Service</span>
		</div>
		<div class="col-lg-6">
			<label>Duration:</label>
			<div class="input-group timepicker">
				<input class="form-control" id="kt_timepicker_2" readonly
					placeholder="Duration" type="text" />
				<div class="input-group-append">
					<span class="input-group-text"> <i class="la la-clock-o"></i>
					</span>
				</div>
			</div>
			<span class="form-text text-muted">Please enter appointment
				duration</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Staff:</label> <select class="form-control select2"
				id="kt_select2_13" name="param">
				<option value="AK">Hardik Desai</option>
				<option value="HI">Vinay Chavhan</option>
				<option value="CA">Kaivalya Ajgaonkar</option>
			</select><span class="form-text text-muted">Please enter appointment
				staff</span>
		</div>
				<div class="col-lg-6">
			<label>Cost:</label><input type="text" class="form-control"
				placeholder="e.g. 200" /> <span class="form-text text-muted">Please
				enter appointment cost</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Status:</label> <select class="form-control select2"
				id="kt_select2_14" name="param">
				<option value="AK">Booked</option>
				<option value="HI">No Show</option>
				<option value="CA">Complete</option>
			</select><span class="form-text text-muted">Please enter appointment
				status</span>
		</div>
	</div>
</div>
<script src="assets/js/pages/datePicker.js"></script>
<script src="assets/js/pages/timePicker.js"></script>
<script src="assets/js/pages/select.js"></script>