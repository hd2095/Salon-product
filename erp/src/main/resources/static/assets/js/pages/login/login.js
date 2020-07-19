$(document).ready(function() {
	$('#loginForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			email_phone: {
				validators: {
					notEmpty: {
						message: 'Please Enter your Email/Phone Number'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'Please Enter your password'
					}
				}
			}
		}
	});
});