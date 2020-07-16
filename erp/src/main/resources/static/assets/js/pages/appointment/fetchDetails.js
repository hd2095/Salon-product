"use strict";

var serviceArray;
var staffArray; 

function fetchServices(){
	$.ajax({
		url: HOST_URL + '/services/getAllServices',
		type: 'get',
		dataType: 'json',
		success: function(response){      
			serviceArray = response.data;
            for( var i = 0; i<response.data.length; i++){
                var service_cost = response.data[i]['serviceCost'];
                var service_duration = response.data[i]['serviceDuration']
            	if(i == 0){
                	$('#appointment_cost').val(service_cost);
                	$('#appointment_duration').val(service_duration);
                }
            	var service_id = response.data[i]['serviceId'];
            	var service_name = response.data[i]['serviceName'];                
                $("#appointment_service").append("<option value='"+service_id+"'>"+service_name+"</option>");
               }
		}
	});
}

function fetchStaff(){
	$.ajax({
		url: HOST_URL + '/staff/getAllStaff',
		type: 'get',
		dataType: 'json',
		success: function(response){
			staffArray = response.data;
            for( var i = 0; i<response.data.length; i++){
            	var staff_id = response.data[i]['staffId'];
            	var staff_name = response.data[i]['fullName'];                
                $("#appointment_staff").append("<option value='"+staff_id+"'>"+staff_name+"</option>");
               }
		}
	});
}

function changeService(value){
	for( var i = 0; i<serviceArray.length; i++){
		var service_cost = serviceArray[i]['serviceCost'];
        var service_duration = serviceArray[i]['serviceDuration'];
        var serviceId  = serviceArray[i]['serviceId'];
        if(serviceId == value){
		$('#appointment_cost').val(service_cost);
    	$('#appointment_duration').val(service_duration);
    	}
	}
}
//Class Initialization
jQuery(document).ready(function() {
	fetchServices();
	fetchStaff();
});