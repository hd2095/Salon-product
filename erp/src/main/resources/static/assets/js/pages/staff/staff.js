'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#staff_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: '/staff/getAllStaff',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'fullName'},
				{data: 'mobileNumber'},
				{data: 'emailId'},
				{data: 'gender'},
				{data: 'revenue_generated'},					
				{data: 'actions', responsivePriority: -1},
			],
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,					
					render: function(data, type, full, meta) {							
						return '\
							<a href="staff/editStaff/'+full.staffId+'" class="btn btn-sm btn-clean btn-icon" title="Edit Staff">\
								<i class="la la-edit"></i>\
							</a>\
							<a href="javascript:deleteStaff(\'' +full.staffId+'\',\''+full.fullName+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Staff">\
								<i class="la la-trash"></i>\
							</a>\
						';
					},
				},
			],
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();


function editStaff(id){
	document.staffForm.action = "staff/editStaff/"+id;
	document.getElementById("staffForm").submit();
/*	$.ajax({
		url:'staff/editStaff/'+id,
		success:function(data){
			$.each(JSON.parse(data), function(key, value) {
				  if(key == 'data'){					  
					  $.each(value, function(k,v){
						  	$('#edit_staffId').val(v.staffId);
						  	$('#edit_fullName').val(v.fullName);		
						  	$('#edit_emailId').val(v.emailId);
						  	$('#edit_mobileNumber').val(v.mobileNumber);						  	
						  	if(v.gender == 'Female'){
						  		$('#edit_male').attr('checked',false);
						  		$('#edit_female').attr('checked',true);
						  	}else{
						  		$('#edit_female').attr('checked',false);
						  		$('#edit_male').attr('checked',true);
						  	}							
						  	$('#edit_staff_birthday').datepicker('setDate',formattedDate(v.birthday));
						  	$('#edit_staffPincode').val(v.staffPincode);
						  	$('#edit_staff_address').val(v.staff_address);
						  	$('#edit_staffDesignation').val(v.staffDesignation);
						  	$('#edit_staff_start_date').datepicker('setDate',formattedDate(v.staff_start_date));
						  	$('#edit_staff_end_date').datepicker('setDate',formattedDate(v.staff_end_date));
						  	$('#edit_staff_in_time').timepicker('setTime',v.staff_in_time);
						  	$('#edit_staff_out_time').timepicker('setTime',v.staff_out_time);
					  });
				  }
				});
			$('#editStaffModal').modal();
		}
	});*/
}

function formattedDate(date){
	var tokens = date.split(" ");
	var month = "";
	switch(tokens[0]){
	case "Jan":
		month = "01";
		break;
	case "Feb":
		month = "02";
		break;
	case "Mar":
		month = "02";
		break;
	case "Apr":
		month = "02";
		break;
	case "May":
		month = "02";
		break;
	case "Jun":
		month = "02";
		break;
	case "Jul":
		month = "02";
		break;
	case "Aug":
		month = "02";
		break;
	case "Sep":
		month = "02";
		break;
	case "Oct":
		month = "02";
		break;
	case "Nov":
		month = "02";
		break;
	case "Dec":
		month = "02";
		break;
	}
	var day = tokens[1];
	var year = tokens[2];
	date = month + "/" + day + "/" +year;
	return date;
}

function deleteStaff(id,staffName){
	Swal.fire({
		title: "Are you sure you want to delete " + staffName+ "!",
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
			return fetch(`/staff/deleteStaff/${id}`)
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
					title: staffName + " deleted successfully!",
					confirmButtonText: "OK"
				}).then(function(result){
					if(result.value){
						location.reload();
					}
				});
			}
		});	
}

function submitForm(){	
	document.getElementById("staffForm").submit();
}

function submitEditForm(){	
	document.staffForm.action = "staff/editStaff/"+$('#edit_staffId').val();
	document.getElementById("editStaffForm").submit();
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#staff_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-open');
}


jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newStaffModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
