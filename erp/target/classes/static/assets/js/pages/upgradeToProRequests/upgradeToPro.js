'use strict';
var KTDatatablesDataSourceAjaxClient = function() {

	var initTable1 = function() {
		var table = $('#request_dataTable');

		// begin first table
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/getAllRequests',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'clientFn'},
				{data: 'clientLn'},
				{data: 'clientNumber'},
				{data: 'clientOrgName'},
				{data: 'planName'},
				{data: 'requestedDate'},
				{data: 'actions', responsivePriority: -1},
				],
				columnDefs: [
					{
						targets: -1,
						title: 'Actions',
						orderable: false,					
						render: function(data, type, full, meta) {	
							return '\
							</a>\
							<a href="javascript:deleteRequest(\'' +full.recordId+'\',\''+full.clientNumber+'\');" class="btn btn-sm btn-clean btn-icon" title="Delete Client">\
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

function deleteRequest(id,clientNumber){
	Swal.fire({
		title: "Are you sure you want to delete request by " + clientNumber+ "!",
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
			return fetch(`${HOST_URL}/deleteRequest/${id}`)
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
				title: clientNumber + " request deleted successfully!",
				confirmButtonText: "OK"
			}).then(function(result){
				if(result.value){
					location.replace('upgradeToProRequests');
				}
			});
		}
	});	
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#upgradeToProRequests_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

jQuery(document).ready(function() {	
	setLinkActive();	
	KTDatatablesDataSourceAjaxClient.init();
	$('#loading-spinner').hide();
});
