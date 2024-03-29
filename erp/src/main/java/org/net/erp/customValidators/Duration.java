package org.net.erp.customValidators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TimeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Duration {
	//error message  
	public String message() default "must contain jtp";  
	//represents group of constraints     
	public Class<?>[] groups() default {};  
	//represents additional information about annotation  
	public Class<? extends Payload>[] payload() default {};  
}
