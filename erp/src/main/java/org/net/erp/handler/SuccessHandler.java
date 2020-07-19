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
			request.getSession().setAttribute(Constants.SESSION_FIRTSNAME,tokens[0]);
			int organizationId = Integer.parseInt(tokens[1]);
			int clientId = Integer.parseInt(tokens[2]);
			request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,organizationId);
			request.getSession().setAttribute(Constants.SESSION_CLIENTID,clientId);
			response.sendRedirect(Constants.DASHBOARD_JSP);
		}catch(Exception e) {

		}
	}

}
