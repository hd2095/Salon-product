package org.net.erp.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.util.Constants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {	
		try {
			if(null != exception) {
				if(exception.getMessage().contains(Constants.USER_IS_DISABLED)) {
					response.sendRedirect(request.getContextPath()+Constants.FORWARD_SLASH+"login"+Constants.QUESTION_MARK +Constants.USER_NOT_FOUND);
				}else if(exception.getMessage().contains(Constants.USER_IS_LOCKED)) {
					response.sendRedirect(request.getContextPath()+Constants.FORWARD_SLASH+"login"+Constants.QUESTION_MARK +Constants.USER_HAS_EXPIRED);
				}
			}
			response.sendRedirect(request.getContextPath()+Constants.FORWARD_SLASH+"login"+Constants.QUESTION_MARK +Constants.URL_ERROR);	
		}catch(Exception e) {
			
		}
	}

}
