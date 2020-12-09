package org.net.erp.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		try {
			String tokens[] = authentication.getName().split(Constants.COMMA);
			if(tokens.length == 3) {
				request.getSession().setAttribute(Constants.SESSION_FIRSTNAME,tokens[0]);
				request.getSession().setAttribute(Constants.SESSION_LASTNAME,tokens[1]);
				int memberId = Integer.parseInt(tokens[2]);				
				request.getSession().setAttribute(Constants.SESSION_MEMBERID,memberId);
				response.sendRedirect("complete-registration");
			}else {
				request.getSession().setAttribute(Constants.SESSION_FIRSTNAME,tokens[0]);
				request.getSession().setAttribute(Constants.SESSION_LASTNAME,tokens[1]);
				int organizationId = Integer.parseInt(tokens[2]);
				int memberId = Integer.parseInt(tokens[3]);
				request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,organizationId);
				request.getSession().setAttribute(Constants.SESSION_MEMBERID,memberId);	
				response.sendRedirect("appointment");
			}			
		}catch(Exception e) {

		}
	}

}
