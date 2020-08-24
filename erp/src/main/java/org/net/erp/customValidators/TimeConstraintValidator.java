package org.net.erp.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeConstraintValidator implements ConstraintValidator<Duration,String>{

    public void initialize(Duration constraint) {
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println(value);
		System.out.println("in here");
		return true;
	}

}
