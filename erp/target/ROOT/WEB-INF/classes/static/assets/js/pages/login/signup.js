$(document).ready(function() {
	$('#singUpForm').bootstrapValidator({
		fields: {
			fullname: {
				validators: {
					notEmpty: {
						message: 'Please enter your full name'
					}
				}
			},
			mobileNumber: {
				validators: {
					notEmpty: {						
						message: 'Please enter your phone number'
					},
					stringLength: {
                        min: 10,
                        max: 10,
                        message: 'Mobile number must be 10 characters long'
                    },
                    regexp: {
                        regexp: /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/,
                        message: 'Mobile number must not contain alphabets'
                    }
				}
			},
			email: {
				validators: {
					notEmpty: {
						message: 'Please enter your email id'
					},
					 emailAddress: {
	                        message: 'Please enter a valid email address'
	                    }
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'The password is required'
					}
				}
			},
			cpassword: {
				validators: {
					notEmpty: {
						message: 'The password confirmation is required'
					},
					callback: {
					    callback: function(value, validator, $field) {
					        if (value == $('#password').val()) {
					            return {
					                valid: true,    // or false
					                message: 'The password and its confirm are same'
					            }
					        }else{
					        	return {
						            valid: false,       // or true
						            message: 'The password and its confirm are not the same'
						        }	
					        }					       
					    }
					}
				}
			}
		}
	});
});