"use strict"
function fetchLastWeekSales(){
	var responseLabels = [];
	var responseData = [];
	var data, options;
	$.ajax({
		url: HOST_URL + '/inventory/lastWeekSales',
		success:function(response){	
			$.each(JSON.parse(response), function(key, value) {
				if(key == 'data'){
					$.each(value, function(k,v){
						responseLabels.push(v.label);
						responseData.push(v.sellingPrice);
					});
				}
			});			
			data = {
					labels : responseLabels,
					series : [responseData]
			}
			options = {
					fullWidth: true,
					height: "270px",
					chartPadding: {
						right: 40
					}
			};
			new Chartist.Line('#sales-chart', data, options);
		}
	});
}	

function fetchStaffByRevenue(){
	$.ajax({
		url: HOST_URL + '/staff/getStaffByRevenue',
		success:function(response){	
			$.each(JSON.parse(response),function (key,value){
				if(key == 'data'){
					$.each(value,function(k,v){
						var staffName = v.fullName;
						var staffNumber = v.mobileNumber;
						var revenueGenerated = v.revenue_generated;
						$('#topStaffTable tr:last').after("<tr><td>" + staffName + "</td><td>" + staffNumber + "</td><td><p> &#8377; "+ revenueGenerated +"</p></td></tr>");
					});
				}else if(key == 'meta'){
					if(value.total == 0){
						$('#topStaffTable tr:last').after("<tr><td colspan='3' style='text-align:center'> No Staff found Kindly Add Staff </td></tr>");
					}				
				}
			});		
		}
	});
}

function fetchClientByRevenue(){
	$.ajax({
		url: HOST_URL + '/client/getClientsByRevenue',
		success:function(response){	
			$.each(JSON.parse(response),function (key,value){
				if(key == 'data'){
					$.each(value,function(k,v){
						var clientName = v.fullName;
						var clientNumber = v.mobileNumber;
						var revenueGenerated = v.revenue_generated;
						$('#topClientTable tr:last').after("<tr><td>" + clientName + "</td><td>" + clientNumber + "</td><td><p> &#8377; "+ revenueGenerated +"</p></td></tr>");
					});
				}else if(key == 'meta'){
					if(value.total == 0){
						$('#topClientTable tr:last').after("<tr><td colspan='3' style='text-align:center'> No Client found Kindly Add Client </td></tr>");
					}				
				}
			});		
		}
	});

}

function fetchTopServices(){
	$.ajax({
		url: HOST_URL + '/services/getTopServices',
		success:function(response){	
			$.each(JSON.parse(response),function (key,value){
				if(key == 'data'){
					$.each(value,function(k,v){
						var serviceName = v.serviceName;
						var serviceCost = v.serviceCost;
						var serviceDuration = v.serviceDuration;
						$('#topServicesTable tr:last').after("<tr><td>" + serviceName + "</td><td><p> &#8377; " + serviceCost + "</p></td><td>"+ serviceDuration +"</p></td></tr>");
					});
				}else if(key == 'meta'){					
					if(value.total == 0){
						$('#topServicesTable tr:last').after("<tr><td colspan='3' style='text-align:center'> No Services found Kindly Add Services </td></tr>");
					}					
				}
			});		
		}
	});
}

jQuery(document).ready(function() {
	//setLinkActive();
	fetchLastWeekSales();
	fetchStaffByRevenue();
	fetchClientByRevenue();
	fetchTopServices();
});