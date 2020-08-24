"use strict"

function fetchOrganization(){
	$.ajax({
		url: HOST_URL + 'register/getAllMasters',
		type: 'get',
		dataType: 'json',
		success: function(response){     
            for( var i = 0; i<response.length; i++){
            	var organization_id = response[i]['masterId'];
            	var organization_name = response[i]['organizationName'];                
                $("#organization").append("<option value='"+organization_id+"'>"+organization_name+"</option>");
               }
		}
	});
}

jQuery(document).ready(function() {
	fetchOrganization();
});