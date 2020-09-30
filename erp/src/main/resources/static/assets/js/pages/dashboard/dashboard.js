
const primary = '#6993FF';

function fetchLastWeekSales(){
	var responseLabels = [];
	var responseData = [];
	var options;
	const apexChart = "#chart_1";
	$.ajax({
		url: HOST_URL + '/sell/lastWeekSales',
		success:function(response){	
			$.each(JSON.parse(response), function(key, value) {
				if(key == 'data'){
					$.each(value, function(k,v){
						responseLabels.push(v.sellingDate.substring(0,v.sellingDate.indexOf(',')));
						responseData.push(v.saleTotal);
					});
				}
			});			
			options = {
					series: [{
						name: "Sale",
						data: responseData
					}],
					chart: {
						height: 350,
						type: 'line',
						zoom: {
							enabled: false
						}
					},
					dataLabels: { 	
						enabled: false
					},
					stroke: {
						curve: 'straight'
					},
					grid: {
						row: {
							colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
							opacity: 0.5
						},
					},
					xaxis: {
						categories: responseLabels,
					},
					colors: [primary]
			};
			var chart = new ApexCharts(document.querySelector(apexChart), options);
			chart.render();
		}
	});
}	

function fetchStaffByRevenue(){
	var htmlArray = '';
	var boyCounter = 0;
	var girlCounter = 0;
	var avatar = ''
		$.ajax({
			url: HOST_URL + '/staff/getStaffByRevenue',
			success:function(response){	
				$.each(JSON.parse(response),function (key,value){
					if(key == 'data'){
						$.each(value,function(k,v){
							var staffId = v.staffId;
							var staffName = v.fullName;
							var staffNumber = v.mobileNumber;
							var revenueGenerated = v.revenue_generated;
							var gender = v.gender;
							if(gender == 'Male'){
								boyCounter++;
								avatar = 'boy-'+boyCounter;
							}else{
								girlCounter++;
								avatar = 'girl-'+girlCounter;
							}
							htmlArray += '\
								<div class="d-flex align-items-center mb-10">\
								<div class="symbol symbol-40 symbol-light-white mr-5">\
								<div class="symbol-label">\
								<img src="assets/media/svg/avatars/'+ avatar +'.svg" class="h-75 align-self-end" alt="">\
								</div>\
								</div>\
								<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
								<a title="Edit Staff" href="staff/editStaff/' + staffId +'" class="text-dark text-hover-primary mb-1 font-size-lg">' + staffName + '</a> <span class="text-muted">' + staffNumber + '</span>\
								</div>\
								<div class="d-flex align-items-center py-lg-0 py-2">\
								<div class="d-flex flex-column text-right">\
								<span class="text-dark-75 font-weight-bolder font-size-h4"> &#8377; ' + revenueGenerated + '</span>\
								</div>\
								</div>\
								</div>\
								';
						});
						$('#top_staff_box').html(htmlArray);
					}else if(key == 'meta'){
						if(value.total == 0){
							htmlArray = '\
								<div class="d-flex align-items-center mb-10">\
								<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
								<a href="#" class="text-dark text-hover-primary mb-1 font-size-lg">No Staff found Kindly Add Staff</a>\
								</div>\
								</div>\
								';							
							$('#top_staff_box').html(htmlArray);
						}				
					}
				});		
			}
		});
}

function fetchClientByRevenue(){
	var htmlArray = '';
	var boyCounter = 0;
	var girlCounter = 0;
	var avatar = ''
		$.ajax({
			url: HOST_URL + '/client/getClientsByRevenue',
			success:function(response){	
				$.each(JSON.parse(response),function (key,value){
					if(key == 'data'){
						$.each(value,function(k,v){
							var clientId = v.clientId;
							var clientName = v.fullName;
							var clientNumber = v.mobileNumber;
							var revenueGenerated = v.revenue_generated;
							var gender = v.gender;
							if(gender == 'Male'){
								boyCounter++;
								avatar = 'boy-'+boyCounter;
							}else{
								girlCounter++;
								avatar = 'girl-'+girlCounter;
							}							
							htmlArray += '\
								<div class="d-flex align-items-center mb-10">\
								<div class="symbol symbol-40 symbol-light-white mr-5">\
								<div class="symbol-label">\
								<img src="assets/media/svg/avatars/'+ avatar +'.svg" class="h-75 align-self-end" alt="">\
								</div>\
								</div>\
								<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
								<a title="View Client Details" href="client?showDetails='+ clientId +'" class="text-dark text-hover-primary mb-1 font-size-lg">' + clientName + '</a> <span class="text-muted">' + clientNumber + '</span>\
								</div>\
								<div class="d-flex align-items-center py-lg-0 py-2">\
								<div class="d-flex flex-column text-right">\
								<span class="text-dark-75 font-weight-bolder font-size-h4"> &#8377; ' + revenueGenerated + '</span>\
								</div>\
								</div>\
								</div>\
								';										
						});
						$('#top_client_box').html(htmlArray);	
					}else if(key == 'meta'){
						if(value.total == 0){
							htmlArray = '\
								<div class="d-flex align-items-center mb-10">\
								<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
								<a href="#" class="text-dark text-hover-primary mb-1 font-size-lg">No Client found Kindly Add Staff</a>\
								</div>\
								</div>\
								';							
							$('#top_client_box').html(htmlArray);							
						}				
					}
				});		
			}
		});

}

function fetchMostAvailedServices(){
	var htmlArray = '';
	$.ajax({
		url: HOST_URL + '/services/getMostAvailedService',
		success:function(response){	
			$.each(JSON.parse(response),function (key,value){				
				if(key == 'listData'){
					$.each(value,function(k,v){
						var serviceId = v.serviceId;
						var serviceName = v.serviceName;
						var serviceCost = v.serviceCost;
						var serviceDuration = v.serviceDuration;
						serviceDuration = serviceDuration.split(':');
						if(serviceDuration[0] == 00){
							serviceDuration = serviceDuration[1] + ' min';
						}else if(serviceDuration[1] == 00){
							serviceDuration = serviceDuration[0] + ' h';
						}else{
							serviceDuration = serviceDuration[0] + ' h ' + serviceDuration[1] + ' min';;
						}
						htmlArray += '\
							<div class="d-flex align-items-center mb-10">\
							<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
							<a title="Edit Service" href="services?showDetails='+ serviceId +'" class="text-dark text-hover-primary mb-1 font-size-lg">' + serviceName + '</a> <span class="text-muted">' + serviceDuration + '</span>\
							</div>\
							<div class="d-flex flex-column text-right">\
							<span class="text-dark-75 font-weight-bolder font-size-h4"> &#8377; ' + serviceCost + '</span>\
							</div>\
							</div>\
							';
					});
				}else if(key == 'meta'){					
					if(value.total == 0){
						htmlArray = '\
							<div class="d-flex align-items-center mb-10">\
							<div class="d-flex flex-column flex-grow-1 my-lg-0 my-2 pr-3">\
							<a href="#" class="text-dark text-hover-primary mb-1 font-size-lg">No Services Completed yet</a>\
							</div>\
							</div>\
							';							
						$('#top_service_box').html(htmlArray);
					}					
				}
				$('#top_service_box').html(htmlArray);
			});	
			KTApp.unblockPage();
		}
	});
}

jQuery(document).ready(function() {
	KTApp.blockPage({
		overlayColor: 'red',
		opacity: 0.1,
		state: 'primary' // a bootstrap color
	});
	fetchLastWeekSales();
	fetchStaffByRevenue();
	fetchClientByRevenue();
	fetchMostAvailedServices();
});