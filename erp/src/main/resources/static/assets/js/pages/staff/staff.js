'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#staff_dataTable');        
		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL +'/staff/getAllStaff',
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
				{data: 'revenue_generated',
					render: function(revenue_generated){
						return '<p> &#8377; ' + revenue_generated + '</p>';
					}	
				},					
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {							
							return '\
							<a href="javascript:viewStaffDetails(\'' +full.staffId+'\');" class="btn btn-sm btn-clean btn-icon" title="View Staff Details">\
							<i class="la la-cog"></i>\
							</a>\
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

function viewStaffDetails(id){
	$('#detailStaffId').val(id);
	$('#staffDetailsModal').modal();
}

function editStaff(id){
	document.staffForm.action = "staff/editStaff/"+id;
	document.getElementById("staffForm").submit();
}

function formattedDate(date){
	if(undefined != date){
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
			month = "03";
			break;
		case "Apr":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "Jun":
			month = "06";
			break;
		case "Jul":
			month = "07";
			break;
		case "Aug":
			month = "08";
			break;
		case "Sep":
			month = "09";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		}
		var day = tokens[1];
		var year = tokens[2];
		date = month + "/" + day + "/" +year;
	}
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
			return fetch(`${HOST_URL}/staff/deleteStaff/${id}`)
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

function calculateCommission(){	
	document.staffDetailsForm.action = "staff/calculateCommission/"+$('#detailStaffId').val();
	document.getElementById("staffDetailsForm").submit();
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
	$('#inventory_nav').removeClass('menu-item-active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newStaffModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
