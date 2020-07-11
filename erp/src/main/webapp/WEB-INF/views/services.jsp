<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link
	href="assets/plugins/custom/datatables/datatables.bundle.css?v=7.0.5"
	rel="stylesheet" type="text/css" />
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
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Services</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
			</div>
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="dropdown" id="dropdownMenuButton">Add New</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" data-toggle="modal"
						data-target="#newServiceModal">Add Service</a> <a
						class="dropdown-item" data-toggle="modal"
						data-target="#newCategoryModal">Add Category</a>
				</div>
				<!--end::Actions-->
			</div>
			<!--end::Info-->
		</div>
	</div>
	<!--end::Subheader-->
	<div class="d-flex flex-column-fluid">
		<!--begin::Container-->
		<div class="container">
			<div class="card card-custom">
				<div class="card-body">
					<!--begin: Datatable-->
					<table class="table table-bordered table-hover table-checkable"
						id="service_dataTable" style="margin-top: 13px !important">
						<thead>
							<tr>
								<th>Service Name</th>
								<th>Category</th>
								<th>Service Cost</th>
								<th>Service Duration</th>
								<th>Service Description</th>
								<th>Actions</th>
							</tr>
						</thead>
					</table>
					<!--end: Datatable-->
				</div>
			</div>
		</div>
	</div>
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newServiceModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newServiceModalLabel">New Service</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" modelAttribute="serviceForm" name="serviceForm" id="serviceForm">
					<div class="card-body">
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Service Name:</label><form:input type="text" path="serviceName" 
									class="form-control" placeholder="e.g. Blow Dry" /> <span
									class="form-text text-muted">Please enter service
									category</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category:</label>
								<form:select path="category" class="form-control select2"
									id="category_dropdown" name="param">
								</form:select>
								<span class="form-text text-muted">Please enter service
									category</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-6">
								<label>Cost:</label>
								<form:input path="serviceCost" id="editServiceCost" type="text" class="form-control"
									placeholder="e.g. 100" />
								<span class="form-text text-muted">Please enter service
									cost</span>
							</div>
							<div class="col-lg-6">
								<label>Duration:</label>
								<div class="input-group timepicker">
									<form:input class="form-control" path="serviceDuration"
										id="service_duration" readonly="readonly"
										placeholder="Duration" type="text" />
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="la la-clock-o"></i>
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
								<form:textarea class="form-control" path="serviceDescription"></form:textarea>
								<span class="form-text text-muted">Please enter service
									description</span>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" onclick="submitServiceForm();"
					class="btn btn-primary font-weight-bold">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal-->
<div class="modal fade" id="editServiceModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="editServiceModalLabel">Edit Service</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post"
					modelAttribute="editServiceForm">
					<div class="card-body">
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Service Name:</label><input type="text"
									class="form-control" placeholder="e.g. Blow Dry" /> <span
									class="form-text text-muted">Please enter service
									category</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category:</label>
								<form:select path="category" class="form-control select2"
									id="kt_select2_10" name="param">
								</form:select>
								<span class="form-text text-muted">Please enter service
									category</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-6">
								<label>Cost:</label>
								<form:input path="serviceCost" type="text" class="form-control"
									placeholder="e.g. 100" />
								<span class="form-text text-muted">Please enter service
									cost</span>
							</div>
							<div class="col-lg-6">
								<label>Duration:</label>
								<div class="input-group timepicker">
									<form:input class="form-control" path="serviceDuration"
										id="kt_timepicker_2" readonly="readonly"
										placeholder="Duration" type="text" />
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="la la-clock-o"></i>
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
								<form:textarea class="form-control" id="editServiceDescription" path="serviceDescription"></form:textarea>
								<span class="form-text text-muted">Please enter service
									description</span>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" onclick="editServiceForm()"
					class="btn btn-primary font-weight-bold">Save changes</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="newCategoryModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newCategoryModal">New Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" action="category" id="categoryForm" name="categoryForm"
					modelAttribute="categoryForm">
					<div class="card-body">
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category Name:</label>
								<form:input type="text" class="form-control" path="categoryName"
									placeholder="e.g. Hair Services" />
								<form:errors id="validation_error" path="categoryName"></form:errors>
								<span class="form-text text-muted">Please enter Category
									name</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category Description:</label>
								<form:textarea class="form-control" path="categoryDescription"></form:textarea>
								<form:errors id="validation_error" path="categoryDescription"></form:errors>
								<span class="form-text text-muted">Please enter Category
									description</span>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" onclick="submitCategory();"
					class="btn btn-primary font-weight-bold">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<div class="modal fade" id="editCategoryModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="editCategoryModal">Edit Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" action="category/edit"
					modelAttribute="editCategoryForm">
					<div class="card-body">
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category Name:</label>
								<form:input type="text" class="form-control" path="categoryName"
									id="editServiceName" placeholder="e.g. Hair Services" />
								<form:errors id="validation_error" path="categoryName"></form:errors>
								<span class="form-text text-muted">Please enter Category
									name</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category Description:</label>
								<form:textarea class="form-control" path="categoryDescription"
									id="editCategoryDescription"></form:textarea>
								<form:errors id="validation_error" path="categoryDescription"></form:errors>
								<span class="form-text text-muted">Please enter Category
									description</span>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" onclick="submitEditCategory();"
					class="btn btn-primary font-weight-bold">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<style type="text/css">
span.select2 {
	width: 100% !important;
}
</style>
<script src="/assets/js/pages/my-script.js"></script>
<script src="/assets/js/pages/select.js"></script>
<script src="/assets/js/pages/services/services.js"></script>
<script src="assets/js/pages/timePicker.js"></script>
<script
	src="assets/plugins/custom/datatables/datatables.bundle.js?v=7.0.5"></script>