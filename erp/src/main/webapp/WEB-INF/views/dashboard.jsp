<html>
<head>
<jsp:include page="layout/side-nav.jsp" />
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">
</head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Sales</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>									
								</div>
							</div>
							<div class="panel-body">
								<div id="sales-chart" class="ct-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Top Clients</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>									
								</div>
							</div>
							<div class="panel-body no-padding">
								<table id="topClientTable" class="table table-striped">
									<thead>
										<tr>
											<th>Client Name</th>
											<th>Client Number</th>
											<th>Client Revenue Generated</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Top Services</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>
								</div>
							</div>
							<div class="panel-body no-padding">
								<table id="topServicesTable" class="table table-striped">
									<thead>
										<tr>
											<th>Service Name</th>
											<th>Service Cost</th>
											<th>Service Duration</th>										
										</tr>
									</thead>
									<tbody>										
									</tbody>
								</table>
							</div>
						</div>						
					</div>
					<div class="col-md-6">						
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Top Staff</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>
								</div>
							</div>
							<div class="panel-body no-padding">
								<table id="topStaffTable" class="table table-striped">
									<thead>
										<tr>
											<th>Staff Name</th>
											<th>Staff Number</th>
											<th>Staff Revenue Generated</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="assets/js/pages/my-script.js"></script>
<script src="assets/js/pages/dashboard/dashboard.js"></script>
<script src="assets/vendor/chartist/js/chartist.min.js"></script>
</html>