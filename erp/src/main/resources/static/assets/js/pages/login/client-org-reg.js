$(document).ready(function() {
	$('#createOrganizationForm').bootstrapValidator({
		fields: {
			orgName: {
				validators: {
					notEmpty: {
						message: 'Please enter your Organization Name'
					}
				}
			},
			orgAddress: {
				validators: {
					notEmpty: {
						message: 'Please enter your Organization Address'
					}
				}
			}
		}
	});
});