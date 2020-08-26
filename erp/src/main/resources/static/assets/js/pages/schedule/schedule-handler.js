"use strict";

function submitForm(){	
	//$('.error').remove();
	//$('#validation_error').remove();
	var valid = true;
	/*	var appointment_date = $('#appointmentDate').val();
	if (appointment_date.length < 1) {
		$('#appointmentDate_span').after('<span id="appointmentDate_error" class="error">please enter appointment date</span>');
		$('#appointmentDate_span').hide();
		valid = false;
	}else{
		$('#appointmentDate_span').show();
		$('#appointmentDate_error').hide();
	}*/
	if(valid){
		document.getElementById("scheduleForm").action = 'schedule/add/';
		document.getElementById("scheduleForm").submit();
	}
}

function editSubmitForm(){	
	//$('.error').remove();
	//$('#validation_error').remove();
	var valid = true;
	/*	var appointment_date = $('#appointmentDate').val();
	if (appointment_date.length < 1) {
		$('#appointmentDate_span').after('<span id="appointmentDate_error" class="error">please enter appointment date</span>');
		$('#appointmentDate_span').hide();
		valid = false;
	}else{
		$('#appointmentDate_span').show();
		$('#appointmentDate_error').hide();
	}*/
	if(valid){
		document.getElementById("editScheduleForm").action = 'schedule/editSchedule/'+$('#edit_scheduleId').val();
		document.getElementById("editScheduleForm").submit();
	}
}

function deleteMeeting(){
	var id = $('#edit_scheduleId').val();
	Swal.fire({
		title: "Are you sure you want to delete this meeting!",
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
			return fetch(`${HOST_URL}/schedule/deleteSchedule/${id}`)
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
				title: "Meeting deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('schedule');
				}
			});
		}
	});	
}