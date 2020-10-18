$(document).ready(function() {
	$('#forgot-password').bootstrapValidator({
		fields: {
			mobileNumber: {
				validators: {
					notEmpty: {
						message: 'Please enter your Phone Number'
					}
				}
			}
		}
	});
});