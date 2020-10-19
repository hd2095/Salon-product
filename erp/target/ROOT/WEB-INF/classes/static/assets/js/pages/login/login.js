$(document).ready(function() {
	$('#loginForm').bootstrapValidator({
		fields: {
			username: {
				validators: {
					notEmpty: {
						message: 'Please enter your Email/Phone Number'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'Please enter your password'
					}
				}
			}
		}
	});
});