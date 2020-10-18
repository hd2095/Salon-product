<html>
<head>
<jsp:include page="layout/nav-bar.jsp" />
<jsp:include page="layout/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<a href="profileCreation"
						class="btn btn-light-success font-weight-bolder btn-sm mr-2">Profile</a>
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<a href="membership"
						class="btn btn-light-primary font-weight-bolder btn-sm mr-2">Membership</a>
					<div
						class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
					<a href="organization"
						class="btn btn-light-info font-weight-bolder btn-sm mr-2">Organization</a>
				</div>
				<!--end::Info-->				
			</div>
		</div>
		<!--end::Subheader-->
		<div class="d-flex flex-column-fluid">
			<!--begin::Container-->
			<div class="container">
				<div class="row">
					<div class="col-xl-6 col-sm-12">
						<div class="card card-custom card-stretch gutter-b">
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bolder text-dark">Membership
									Plans</h3>
							</div>
							<div class="card-body pt-2">
								<c:choose>
									<c:when test="${!empty(clientPlanList)}">
										<c:forEach items="${clientPlanList}" var="item">
											<div class="d-flex align-items-center">
												<div class="d-flex flex-column flex-grow-1">
													<span
														class="text-dark-75 font-weight-bold font-size-lg mb-1">${item.planName}</span>
													<span class="text-muted font-weight-bold">${item.planDescription}</span>
												</div>
												<div class="dropdown dropdown-inline ml-2"
													data-toggle="tooltip" data-placement="left">
													<a href="#"
														class="btn btn-hover-light-primary btn-sm btn-icon"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false"> <i class="ki ki-bold-more-hor"></i>
													</a>
													<div
														class="dropdown-menu p-0 m-0 dropdown-menu-md dropdown-menu-right">
														<!--begin::Navigation-->
														<ul class="navi navi-hover">
															<li class="navi-item"><a
																href="javascript:editPlan('${item.planId}');"
																class="navi-link"> <span class="navi-text"> <span
																		class="label label-xl label-inline label-light-success">Edit
																			Plan</span>
																</span>
															</a></li>
															<li class="navi-item"><a
																href="javascript:deletePlan('${item.planId}','${item.planName}');"
																class="navi-link"> <span class="navi-text"> <span
																		class="label label-xl label-inline label-light-danger">Delete
																			Plan</span>
																</span>
															</a></li>
														</ul>
													</div>
												</div>
											</div>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="d-flex align-items-center">
											<div class="d-flex flex-column flex-grow-1">
												<span
													class="text-dark-75 font-weight-bold font-size-lg mb-1">
													No Membership plans created</span>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-sm-12">
						<div class="card card-custom gutter-b">
							<!--begin::Header-->
							<div class="card-header border-0">
								<h3 class="card-title font-weight-bold text-dark">New
									Membership Plan</h3>
							</div>
							<div class="card-body pt-2">
								<!--begin::Form-->
								<form:form modelAttribute="membershipForm" class="form"
									name="membershipForm" action="addMembership" method="post"
									id="membershipForm" autocomplete="off">
									<div class="form-group">
										<form:input type="text" path="planName" id="planName"
											class="form-control form-control-solid" name="name"
											placeholder="Plan Name" />
									</div>
									<div class="form-group">
										<form:textarea path="planDescription"
											class="form-control form-control-solid" name="memo" rows="4"
											placeholder="Plan Description" id="planDescription"></form:textarea>
									</div>
									<div class="mt-10">
										<button class="btn btn-primary font-weight-bold">Save</button>
									</div>
								</form:form>
								<!--end::Form-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal-->
	<div class="modal fade" id="editPlanModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="editPlanModalLabel">Edit Plan</h3>
					<a class="close" data-dismiss="modal" aria-label="Close"> </a>
				</div>
				<div class="modal-body">
					<form:form modelAttribute="editMembershipForm" class="form"
						name="editMembershipForm" action="editMembership" method="post"
						id="editMembershipForm" autocomplete="off">
						<form:hidden id="editPlanId" path="planId" />
						<div class="form-group">
							<form:input type="text" path="planName" id="editPlanName"
								class="form-control form-control-solid" name="name"
								placeholder="Plan Name" />
						</div>
						<div class="form-group">
							<form:textarea path="planDescription"
								class="form-control form-control-solid" name="memo" rows="4"
								placeholder="Plan Description" id="editPlanDescription"></form:textarea>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-light-primary font-weight-bold"
						data-dismiss="modal">Close</button>
					<button type="button" onclick="submitEditForm()"
						class="btn btn-primary mr-2">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--End Modal-->
	<script type='text/javascript'>
		var HOST_URL = "${pageContext.request.contextPath}";
		jQuery(document).ready(function() {
			$('#loading-spinner').hide();
			var elementToFind = $('li.menu-item-active');
			var element = $('ul.menu-nav').find(elementToFind);
			$(element).removeClass('menu-item-active');
			$('#profile-creation_nav').addClass('menu-item-active');
			$('#inventory_nav').removeClass('menu-item-active');
		});
		document.addEventListener('DOMContentLoaded', function(e) {
			const membershipForm = document.getElementById('membershipForm');
			const fv = FormValidation.formValidation(membershipForm, {
				fields : {
					planName : {
						validators : {
							notEmpty : {
								message : 'Please enter plan name'
							},
						}
					}
				},
				plugins : {
					trigger : new FormValidation.plugins.Trigger(),
					bootstrap : new FormValidation.plugins.Bootstrap(),
					submitButton : new FormValidation.plugins.SubmitButton(),
					defaultSubmit : new FormValidation.plugins.DefaultSubmit()
				}
			});
		});
	</script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/utilities/push-divs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/pages/profile/clientPlan.js" />"></script>
</body>
</html>