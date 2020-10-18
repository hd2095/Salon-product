'use strict'

function purchasePlan(param){
	var plan_id = 0;
	if(param == 'Standard'){
		plan_id = 1;
	}else{
		plan_id = 2;
	}
	Swal.fire({
		title: "Are you sure you want to purchase " + param + " plan!",
		icon: "warning",		  
		confirmButtonText: "Yes, purchase it!",
		showCancelButton: true,
		cancelButtonText: "No, Cancel!",
		customClass: {
			confirmButton: "btn btn-danger",
			cancelButton: "btn btn-default"
		},
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return fetch(`${HOST_URL}/purchasePlan/${plan_id}`)
			.then(response => {
				if(!response.ok){
					throw new Error(response.statusText);	
				}
				return response.json();
			})
			.catch(error => {
				Swal.showValidationMessage(
						`Request failed: ${error}`
				)
			})  
		}
	}).then(function(result){
		if(result.value){
			Swal.fire({
				title: " Our sales team will contact you soon regarding plan upgrade! Thankyou for your patience",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('dashboard');
				}
			});
		}
	});	
}