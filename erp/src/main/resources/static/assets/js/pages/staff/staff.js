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
				{data: 'gender'},
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
							<a href="staff/editStaff/'+full.staffId+'" class="btn btn-xs btn-custom" title="Edit Staff">\
							<i class="lnr lnr-pencil"></i>\
							</a>\
							<a href="javascript:deleteStaff(\'' +full.staffId+'\',\''+full.fullName+'\');" class="btn btn-xs btn-custom" title="Delete Staff">\
							<i class="lnr lnr-trash"></i>\
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

function submitEditForm(){	
	document.staffForm.action = "staff/editStaff/"+$('#edit_staffId').val();
	document.getElementById("editStaffForm").submit();
}

function setLinkActive(){
	var elementToFind = $('a.active');
	var element = $('ul.nav').find(elementToFind);
	$(element).removeClass('active');
	$('#staff_nav').addClass('active');
	$('#inventory_nav').removeClass('active');
}

jQuery(document).ready(function() {
	if($('#validation_error').length){
		$('.span-info').hide();
		$('#newStaffModal').modal();
	}
	setLinkActive();
	KTDatatablesDataSourceAjaxClient.init();
});
