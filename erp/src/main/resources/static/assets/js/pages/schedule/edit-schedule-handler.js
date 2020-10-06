"use strict";

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

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#schedule_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

var handleForms = function () {
	
	var _handleEditForm = function() {
		var validation;
		const editScheduleForm = document.getElementById('editScheduleForm');
		validation = FormValidation.formValidation(
				editScheduleForm,
				{
					fields: {
						scheduleWith : {
							validators : {
								notEmpty : {
									message : 'Please enter meeting with'
								}
							}
						},
						scheduleTopic : {
							validators : {
								notEmpty : {
									message : 'Please enter meeting topic'
								}
							}
						},
						scheduleDate : {
							validators : {
								notEmpty : {
									message : 'Please enter schedule date'
								},
							}
						},
						scheduleTo: {
							validators: {
								notEmpty: {
									message: 'Please enter service duration'
								}
							}
						},
						scheduleFrom: {
							validators: {
								notEmpty: {
									message: 'Please enter service duration'
								}
							}
						}
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						submitButton: new FormValidation.plugins.SubmitButton(),	                   
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
		);
		$('[name="scheduleDate"]').datepicker({
			format : 'mm/dd/yyyy'
		}).on('changeDate', function(e) {
			validation.revalidateField('scheduleDate');
		});
		$('[name="scheduleFrom"]').timepicker().on('changeTime.timepicker', function(e) {
			validation.revalidateField('scheduleFrom');
		});
		$('[name="scheduleTo"]').timepicker().on('changeTime.timepicker', function(e) {
			validation.revalidateField('scheduleTo');
		});
		$('#editMeetingBtn').on('click', function (e) {
			e.preventDefault();
			validation.validate().then(function(status) {
				if (status == 'Valid') {										
					document.getElementById("editScheduleForm").action = 'schedule/editSchedule/'+$('#edit_scheduleId').val();
					document.getElementById("editScheduleForm").submit();
				} else {
					KTUtil.scrollTop();
				}
			});
		});
	}
	return {
		init: function() {
			_handleEditForm();
		}
	};
}();

jQuery(document).ready(function() {
	$('#loading-spinner').hide();
	setLinkActive();
	handleForms.init();
});