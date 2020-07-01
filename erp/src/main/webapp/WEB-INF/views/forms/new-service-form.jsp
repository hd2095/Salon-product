<form class="form">
<div class="card-body">
	<div class="form-group row">
		<div class="col-lg-12">
			<label>Service Name:</label><input type="text" class="form-control"
				placeholder="e.g. Blow Dry" /> <span class="form-text text-muted">Please
				enter service category</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-12">
			<label>Category:</label> <select class="form-control select2"
				id="kt_select2_10" name="param">
				<option value="1">Hair</option>
				<option value="2">Body</option>
			</select> <span class="form-text text-muted">Please enter service
				category</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Cost:</label> <input type="text" class="form-control"
				placeholder="e.g. 100" /> <span class="form-text text-muted">Please
				enter service cost</span>
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
			<span class="form-text text-muted">Please enter service
				duration</span>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-lg-12">
			<label>Service Description: </label>
			<textarea class="form-control"></textarea>
			<span class="form-text text-muted">Please enter service
				description</span>
		</div>
	</div>
</div>
</form>
<script src="http://localhost:8081/assets/js/pages/select.js"></script>