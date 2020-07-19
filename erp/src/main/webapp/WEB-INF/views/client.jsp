<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="layout/side-nav.jsp" />
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
					<h3 class="panel-title">Clients</h3>
					<a data-toggle="modal" data-target="#newClientModal"
						style="float: right; margin-top: -30px; background-color: #252c35; color: white;"
						onclick = "clearNewClientForm();" class="btn">Add New</a>
					<!--end::Actions-->
				</div>
				<!--end::Info-->
				<!--begin::Toolbar-->
				<div class="panel-body">
					<table class="table table-striped table-bordered dt-responsive"
						style="width: 100%; margin-top: 13px !important"
						id="client_dataTable">
						<thead>
							<tr>
								<th>Client Name</th>
								<th>Mobile Number</th>
								<th>Email ID</th>
								<th>Gender</th>
								<th>Revenue Generated</th>
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
<div class="modal fade" id="newClientModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3 id="newClientModalLabel">New Client</h3>
			</div>
			<div class="modal-body">
				<jsp:include page="../views/forms/new-client-form.jsp" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary"
					onclick="clearNewClientForm();" data-dismiss="modal">Close</button>
				<button type="button" onclick="submitForm()" class="btn"
					style="background-color: #252c35; color: white;">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<!-- Modal-->
<div class="modal fade" id="editClientModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3 id="editClientModalLabel">Edit Client</h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<jsp:include page="../views/forms/edit-client-form.jsp" />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="clearEditClientForm();" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" onclick="submitEditForm()" class="btn"
					style="background-color: #252c35; color: white;">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<style type="text/css">
span.error {
	color: red;
}
</style>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/client/client.js"></script>
<script src="assets/js/dataTable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTable/dataTables.bootstrap4.min.js"></script>
<script src="assets/js/dataTable/dataTables.responsive.min.js"></script>
<script src="assets/js/dataTable/responsive.bootstrap4.min.js"></script>