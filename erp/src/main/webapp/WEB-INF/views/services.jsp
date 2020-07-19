<jsp:include page="layout/side-nav.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="assets/css/dataTable/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/css/dataTable/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<div class="panel panel-headline">
				<div class="panel-heading">
					<h3 class="panel-title">Services</h3>
					<div class="btn-group" style="float: right; margin-top: -30px;">
						<a data-toggle="dropdown" id="dropdownMenuButton"
							style="background-color: #252c35; color: white;" class="btn">Add
							New</a>
						<ul class="dropdown-menu" role="menu"
							style="cursor: pointer; min-width: fit-content;">
							<li><a data-toggle="modal" data-target="#newServiceModal">Add
									Service</a></li>
							<li class="divider"></li>
							<li><a data-toggle="modal" data-target="#newCategoryModal">Add
									Category</a></li>
						</ul>
					</div>
				</div>
				<div class="panel-body">
					<table id="service_dataTable"
						class="table table-striped table-bordered dt-responsive"
						style="width: 100%; margin-top: 13px !important">
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
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal-->
<div class="modal fade" id="newServiceModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="newServiceModalLabel">New Service</h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" modelAttribute="serviceForm"
					name="serviceForm" id="serviceForm" autocomplete="off">
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Service Name:</label>
							<form:input type="text" path="serviceName" class="form-control"
								id="serviceName" placeholder="e.g. Blow Dry" />
							<form:errors id="validation_error" path="serviceName"></form:errors>
							<span id="serviceName_span" class="form-text text-muted">Please
								enter service category</span>
						</div>
						<div class="col-lg-6">
							<label>Category:</label>
							<form:select path="category" class="form-control"
								id="category_dropdown" name="param">
							</form:select>
							<span class="form-text text-muted">Please enter service
								category</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Cost:</label>
							<form:input path="serviceCost" id="serviceCost" type="text"
								class="form-control" placeholder="e.g. 100" />
							<form:errors id="validation_error" path="serviceCost"></form:errors>
							<span id="serviceCost_span" class="form-text text-muted">Please
								enter service cost</span>
						</div>
						<div class="col-lg-6">
							<label>Duration:</label>
							<div class="input-group timepicker">
								<form:input class="form-control" path="serviceDuration"
									id="service_duration" readonly="readonly"
									placeholder="Duration" type="text" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
							<form:errors id="validation_error" path="serviceDuration"></form:errors>
							<span id="service_duration_span" class="form-text text-muted">Please
								enter service duration</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Service Description: </label>
							<form:textarea class="form-control" path="serviceDescription"></form:textarea>
							<span class="form-text text-muted">Please enter service
								description</span>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					onclick="clearNewServiceForm();" data-dismiss="modal">Close</button>
				<button type="button" onclick="submitServiceForm();"
					class="btn btn-black">Save changes</button>
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
				<h3 class="modal-title" id="editServiceModalLabel">Edit Service</h3>
				<button type="button" onclick="deleteService();"
					style="float: right; margin-top: -6%;"
					class="btn btn-danger font-weight-bold" data-dismiss="modal">Delete
					Service</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" id="editServiceForm"
					modelAttribute="editServiceForm" name="editServiceForm">
					<form:hidden id="edit_serviceId" path="serviceId" />
					<input type="hidden" name="edit_service_categoryId"
						id="edit_service_categoryId" />
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Service Name:</label>
							<form:input type="text" id="edit_serviceName"
								class="form-control" path="serviceName"
								placeholder="e.g. Blow Dry" />
							<span id="edit_serviceName_span" class="form-text text-muted">Please
								enter service category</span>
						</div>
						<div class="col-lg-6">
							<label>Category:</label>
							<form:select path="category" class="form-control select2"
								id="edit_service_category" disabled="true">
							</form:select>
							<span class="form-text text-muted">Please enter service
								category</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Cost:</label>
							<form:input path="serviceCost" id="edit_serviceCost" type="text"
								class="form-control" placeholder="e.g. 100" />
							<span id="edit_serviceCost_span" class="form-text text-muted">Please
								enter service cost</span>
						</div>
						<div class="col-lg-6">
							<label>Duration:</label>
							<div class="input-group timepicker">
								<form:input class="form-control" path="serviceDuration"
									id="edit_service_duration" readonly="readonly"
									placeholder="Duration" type="text" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
							<span id="edit_service_duration_span"
								class="form-text text-muted">Please enter service
								duration</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-6">
							<label>Service Description: </label>
							<form:textarea class="form-control" id="editServiceDescription"
								path="serviceDescription"></form:textarea>
							<span class="form-text text-muted">Please enter service
								description</span>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					onclick="clearEditServiceForm();" data-dismiss="modal">Close</button>
				<button type="button" onclick="submitEditServiceForm();"
					class="btn btn-black">Save changes</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="newCategoryModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document" style="width: 30%;">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="newCategoryModal">New Category</h3>
				<div id="categoryAlreadyExists"
					style="display: none; color: red; text-align: center;"></div>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" action="category"
					id="categoryForm" name="categoryForm" modelAttribute="categoryForm">
					<div class="form-group row">
						<div class="col-lg-12">
							<label>Category Name:</label>
							<form:input type="text" class="form-control" id="categoryName"
								path="categoryName" placeholder="e.g. Hair" />
							<form:errors id="validation_error" path="categoryName"></form:errors>
							<span id="categoryName_span" class="form-text text-muted">Please
								enter Category name</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-lg-12">
							<label>Category Description:</label>
							<form:textarea class="form-control" id="categoryDescription"
								path="categoryDescription"></form:textarea>
							<form:errors id="validation_error" path="categoryDescription"></form:errors>
							<span id="categoryDescription_span" class="form-text text-muted">Please
								enter Category description</span>
						</div>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					onclick="clearNewCategoryForm();" data-dismiss="modal">Close</button>
				<button type="button" onclick="submitCategory();"
					class="btn btn-black">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<div class="modal fade" id="editCategoryModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document" style="width: 30%;">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="editCategoryModal">Edit Category</h3>
				<div id="editCategoryAlreadyExists"
					style="display: none; color: red; text-align: center;"></div>
				<button type="button" onclick="deleteCategory();"
					style="float: right; margin-top: -6%;"
					class="btn btn-danger font-weight-bold" data-dismiss="modal">Delete
					Category</button>
			</div>
			<div class="modal-body">
				<form:form class="form" method="post" action="category/edit"
					modelAttribute="editCategoryForm" name="editCategoryForm"
					id="editCategoryForm">
					<form:hidden path="categoryId" id="edit_categoryId"></form:hidden>
					<div class="card-body">
						<div class="form-group row">
							<div class="col-lg-12">
								<label>Category Name:</label>
								<form:input type="text" class="form-control" path="categoryName"
									id="editCategoryName" placeholder="e.g. Hair" />
								<form:errors id="validation_error" path="categoryName"></form:errors>
								<span id="editcategoryName_span" class="form-text text-muted">Please
									enter Category name</span>
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
					onclick="clearEditCategoryForm();" data-dismiss="modal">Close</button>
				<button type="button" onclick="submitEditCategory();"
					class="btn btn-black">Save changes</button>
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
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type='text/javascript'>
	$(function() {
		$('#edit_service_duration').timepicker({
			showMeridian : false,
			minuteStep : 5,
			defaultTime : false
		});
		$('#service_duration').timepicker({
			showMeridian : false,
			minuteStep : 5,
			defaultTime : false
		});
	});
	jQuery(document).ready(function() {
		var invalidCategory = '${categoryExists}';
		if (invalidCategory.length > 2) {
			$('#categoryAlreadyExists').html(invalidCategory);
			$('#categoryAlreadyExists').show();
			$('#newCategoryModal').modal();
		}
	});
</script>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/services/services.js"></script>
<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>