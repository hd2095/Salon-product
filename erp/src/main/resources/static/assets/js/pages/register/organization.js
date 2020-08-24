"use strict"

function fetchPlan(){
	$.ajax({
		url: HOST_URL + '/getAllPlans',
		type: 'get',
		dataType: 'json',
		success: function(response){     
			console.log(response);
            for( var i = 0; i<response.length; i++){
            	var plan_name = response[i]['plan_name'];                
                $("#plan").append("<option value='"+plan_name+"'>"+plan_name+"</option>");
               }
		}
	});
}

jQuery(document).ready(function() {
	fetchPlan();
});