<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<title>Grokar | Services</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					class="btn btn-dark font-weight-bolder btn-sm"
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
					<c:choose>
						<c:when test="${servicesMap.size() > 0}">
							<c:forEach items="${servicesMap}" var="item">
								<div class="accordion" id="accordian${item.key.categoryName}">
									<div class="card">
										<div class="card-header">
											<div class="card-title" data-toggle="collapse"
												data-target="#collapse${item.key.categoryName}">${item.key.categoryName}
											</div>
											<div onclick="editOrDeleteCategory(${item.key.categoryId});"
												style="float: right; margin-top: -4%; margin-right: 2%;">
												<i class="flaticon2-pen text-danger"></i>
											</div>
										</div>
										<c:forEach items="${item.value}" var="item1">
											<c:set var="duration" value="${item1.serviceDuration}" />
											<c:set var="hours" value="${fn:split(duration,':')[0]}" />
											<c:set var="mins" value="${fn:split(duration,':')[1]}" />
											<div id="collapse${item.key.categoryName}"
												class="collapse show"
												data-parent="#accordian${item.key.categoryName}">
												<div class="card-body services" style="cursor: pointer;"
													onclick="editOrDeleteService(${item1.serviceId});">${item1.serviceName}<div
														style="left: 50%; position: absolute;">&#8377;&nbsp;${item1.serviceCost}</div>
													<div style="float: right;">
														<c:choose>
															<c:when test="${fn:contains(hours, '00')}">
																<c:out value="${mins} min" />
															</c:when>
															<c:when test="${fn:contains(mins, '00')}">
																<c:out value="${fn:substringAfter(hours, '0')}  h" />
															</c:when>
															<c:otherwise>
																<c:out
																	value="${fn:substringAfter(hours, '0')} h ${mins} min" />
															</c:otherwise>
														</c:choose>

													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="accordion" id="emptyCategory">
								<div class="card">
									<div class="card-header">
										<div class="card-title" data-toggle="collapse"
											data-target="#collapseOne1">Category</div>
									</div>
									<div id="collapseOne1" class="collapse show"
										data-parent="#emptyCategory">
										<div class="card-body" style="text-align: center;">Kindly
											add services and categories to avail features of this module.</div>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
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
					onclick="clearNewServiceForm();" aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
				<div id="serviceAlreadyExists"
					style="display: none; color: red; text-align: center;"></div>
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
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">&#8377;</span>
								</div>
								<form:input path="serviceCost" id="serviceCost" type="text"
									class="form-control" placeholder="e.g. 100" />
							</div>
							<form:errors id="validation_error" path="serviceCost"></form:errors>
							<span id="serviceCost_span" class="form-text text-muted">Please
								enter service cost</span>
						</div>
						<div class="col-lg-6">
							<label>Duration:</label>
							<div class="input-group timepicker">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i class="la la-clock-o"></i>
									</span>
								</div>
								<form:input class="form-control" name="serviceDuration"
									path="serviceDuration" id="service_duration"
									readonly="readonly" placeholder="Duration" type="text" />
							</div>
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
				<button type="button" class="btn btn-secondary"
					onclick="clearNewServiceForm();" data-dismiss="modal">Close</button>
				<button type="button" id="createServiceBtn"
					class="btn btn-primary mr-2">Create Service</button>
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
				<button type="button" class="close" data-dismiss="modal"
					onclick="clearEditServiceForm();" aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
				<div id="editServiceAlreadyExists"
					style="display: none; color: red;"></div>
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
				<button type="button" onclick="deleteService();"
					 class="btn btn-danger font-weight-bold"
					data-dismiss="modal">Delete Service</button>
				<!-- <button type="button" class="btn btn-secondary"
					onclick="clearEditServiceForm();" data-dismiss="modal">Close</button> -->
				<button type="button" id="editServiceBtn"
					class="btn btn-primary mr-2">Edit service</button>
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
				<h3 class="modal-title" id="newCategoryModal">New Category</h3>
				<button type="button" class="close" data-dismiss="modal"
					onclick="clearNewCategoryForm();" aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
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
				<button type="button" class="btn btn-secondary"
					onclick="clearNewCategoryForm();" data-dismiss="modal">Close</button>
				<button type="button" id = "createCategoryBtn"
					class="btn btn-primary mr-2">Create category</button>
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
				<h3 class="modal-title" id="editCategoryModal">Edit Category</h3>
				<button type="button" class="close" data-dismiss="modal"
					onclick="clearEditCategoryForm();" aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
				<div id="editCategoryAlreadyExists"
					style="display: none; color: red;"></div>
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
				<button type="button" onclick="deleteCategory();"
					class="btn btn-danger font-weight-bold" data-dismiss="modal">Delete
					Category</button>
				<!-- <button type="button" class="btn btn-secondary"
					onclick="clearEditCategoryForm();" data-dismiss="modal">Close</button> -->
				<button type="button" id = "editCategoryBtn"
					class="btn btn-primary mr-2">Edit category</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<style type="text/css">
span.select2 {
	width: 100% !important;
}

.services>* {
	display: inline-block;
}
</style>
<script>
	var HOST_URL = "${pageContext.request.contextPath}"
</script>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		$('#loading-spinner').hide();
		var invalidCategory = '${categoryExists}';
		if (invalidCategory.length > 2) {
			$('#categoryAlreadyExists').html(invalidCategory);
			$('#categoryAlreadyExists').show();
			$('#newCategoryModal').modal();
		}
		var invalidEditCategory = '${editCategoryExists}';
		if (invalidEditCategory.length > 2) {
			$('#editCategoryAlreadyExists').html(invalidEditCategory);
			$('#editCategoryAlreadyExists').show();
			$('#editCategoryModal').modal();
		}
		var invalidServiceCategory = '${serviceExists}';
		if (invalidServiceCategory.length > 2) {
			$('#serviceAlreadyExists').html(invalidServiceCategory);
			$('#serviceAlreadyExists').show();
			$('#newServiceModal').modal();
		}
		var invalidEditServiceCategory = '${editServiceExists}';
		if (invalidEditServiceCategory.length > 2) {
			$('#editServiceAlreadyExists').html(invalidEditServiceCategory);
			$('#editServiceAlreadyExists').show();
			$('#editServiceModal').modal();
		}
		var showServiceDetails = '${showServiceDetails}';
		if (showServiceDetails.length > 0) {
			editOrDeleteService(showServiceDetails);
		}
	});
</script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/select2.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/utilities/timePicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/pages/services/services.js" />"></script>