package org.net.erp.exceptionHandler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
    public String handle(final UsernameNotFoundException exception) {
       return "login?notFound";
    }
}
