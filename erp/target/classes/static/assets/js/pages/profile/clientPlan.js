'use strict';

function deletePlan(id,planName){
	Swal.fire({
		title: "Are you sure you want to delete " + planName+ "!",
		icon: "warning",		  
		confirmButtonText: "Yes, delete it!",
		showCancelButton: true,
		cancelButtonText: "No, Cancel!",
		customClass: {
			confirmButton: "btn btn-danger",
			cancelButton: "btn btn-default"
		},
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return fetch(`${HOST_URL}/deleteClientPlan/${id}`)
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
				title: planName + " deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('membership');
				}
			});
		}
	});	
}

function editPlan(id){
	$.ajax({
		url: HOST_URL + '/editMembership/'+id,
		type: 'get',
		dataType: 'json',
		success:function(data){
			$('#editPlanId').val(id);
			$('#editPlanName').val(data['planName']);
			$('#editPlanDescription').val(data['planDescription']);
			$('#editPlanModal').modal();
		}
	});	
}

function submitEditForm(){
	document.editMembershipForm.action = "editMembership/"+$('#editPlanId').val();
	document.getElementById("editMembershipForm").submit();
}