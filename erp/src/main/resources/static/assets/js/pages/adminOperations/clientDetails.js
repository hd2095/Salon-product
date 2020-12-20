'use strict';
var KTDatatablesDataSourceAjaxClient = function() {
	var initTable1 = function() {
		var table = $('#all_clients_dataTable');
		table.DataTable({
			responsive: true,
			ajax: {
				url: HOST_URL + '/getAllMembers',
				type: 'GET',
				data: {
					pagination: {
						perpage: 50,
					},
				},
			},
			columns: [
				{data: 'first_name'},
				{
					data: 'last_name',
					defaultContent:''
				},
				{data: 'mobileNumber'},
				{
					data: 'registerOrganization.organization_name',
					defaultContent:''
				},
				{
					data: 'registerOrganization.plan',
					defaultContent:''
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
							</a>\
							<a href="javascript:upgradeClientPlan(\'' +full.memberId+'\',\''+full.mobileNumber+'\',\'Basic\');" class="btn btn-sm btn-clean btn-icon" title="Upgrade Plan to Basic">\
							<i class="fab la-bootstrap"></i>\
							</a>\
							</a>\
							<a href="javascript:upgradeClientPlan(\'' +full.memberId+'\',\''+full.mobileNumber+'\',\'Standard\');" class="btn btn-sm btn-clean btn-icon" title="Upgrade Plan to Standard">\
							<i class="fab la-stripe-s"></i>\
							</a>\
							</a>\
							<a href="javascript:upgradeClientPlan(\'' +full.memberId+'\',\''+full.mobileNumber+'\',\'Premium\');" class="btn btn-sm btn-clean btn-icon" title="Upgrade Plan to Premium">\
							<i class="fab la-patreon"></i>\
							</a>\
							';
						},
					},
					],
		});
	};
	return {
		init: function() {
			initTable1();
		},
	};
}();

function upgradeClientPlan(id,clientNumber,plan){
	Swal.fire({
		title: 'Please enter expiry date',
		input: 'text',
		inputPlaceholder: '*Only Format allowed DD/MM/YYYY',
		showCancelButton: true,
		confirmButtonText: 'Submit',
	}).then((result) => {
		var regex = /^[0-9]{2}[\/][0-9]{2}[\/][0-9]{4}$/g;
		if (regex.test(result.value)) {
			Swal.fire({
				title: "Are you sure you want to upgrade " +clientNumber+ " to " +plan+ " plan?!",
				icon: "warning",		  
				confirmButtonText: "Yes, upgrade it!",
				showCancelButton: true,
				cancelButtonText: "No, Cancel!",
				customClass: {
					confirmButton: "btn btn-danger",
					cancelButton: "btn btn-default"
				},
				showLoaderOnConfirm: true,
				preConfirm: () => {
					return fetch(`${HOST_URL}/upgradeClientPlan/${id}/${plan}?expiryDate=${result.value}`)
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
						title: clientNumber + " plan updated to "+plan+" successfully!",
						confirmButtonText: "OK"
					}).then(function(result){
						if(result.value){
							location.replace('clientDetails');
						}
					});
				}
			});
		}else{
			if('cancel' != result.dismiss){
				Swal.fire({
					title: "Invalid Date Format received",
					icon: "warning",
					confirmButtonText: "OK"
				});
			}
		}
	});
}

function setLinkActive(){
	var elementToFind = $('li.menu-item-active');
	var element = $('ul.menu-nav').find(elementToFind);
	$(element).removeClass('menu-item-active');
	$('#clientDetails_nav').addClass('menu-item-active');
	$('#inventory_nav').removeClass('menu-item-active');
}

jQuery(document).ready(function() {	
	setLinkActive();	
	KTDatatablesDataSourceAjaxClient.init();
	$('#loading-spinner').hide();
});
