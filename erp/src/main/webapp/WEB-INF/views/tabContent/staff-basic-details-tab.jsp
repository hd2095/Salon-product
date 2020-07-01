<jsp:include page="../global-imports/all-global-imports.jsp" />
	<div class="card-body">
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Full Name:</label> <input type="email" class="form-control"
					placeholder="Enter full name" /> <span
					class="form-text text-muted">Please enter client's full name</span>
			</div>
			<div class="col-lg-6">
				<label>Contact Number:</label> <input type="email"
					class="form-control" placeholder="Enter contact number" /> <span
					class="form-text text-muted">Please enter client's contact
					number</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Address:</label>
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="Enter client's address" />
					<div class="input-group-append">
						<span class="input-group-text"><i class="la la-map-marker"></i></span>
					</div>
				</div>
				<span class="form-text text-muted">Please enter client's
					address</span>
			</div>
			<div class="col-lg-6">
				<label>Pincode:</label>
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="Enter client's pincode" />
					<div class="input-group-append">
						<span class="input-group-text"><i class="la la-bookmark-o"></i></span>
					</div>
				</div>
				<span class="form-text text-muted">Please enter client's
					pincode</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Gender:</label>
				<div class="radio-inline">
					<label class="radio radio-solid"> <input type="radio"
						name="example_2" checked="checked" value="2" />Male <span></span>
					</label> <label class="radio radio-solid"> <input type="radio"
						name="example_2" value="2" /> Female <span></span>
					</label> <label class="radio radio-solid"> <input type="radio"
						name="example_2" value="2" /> Other <span></span>
					</label>
				</div>
				<span class="form-text text-muted">Please select client's
					gender</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg-6">
				<label>Birthday:</label>
				<div class="input-group date">
					<input type="text" class="form-control" readonly
						id="kt_datepicker_3" />
					<div class="input-group-append">
						<span class="input-group-text"> <i class="la la-calendar"></i>
						</span>
					</div>
				</div>
				<span class="form-text text-muted">Please select client's
					birthday</span>
			</div>
		</div>
	</div>
<script src="assets/js/pages/datePicker.js"></script>