
package org.net.erp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component 
public class Interceptor implements HandlerInterceptor{ 


	@Override 
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String remoteAddr = Constants.EMPTY;
		try {
			if (request != null) {
	            remoteAddr = request.getHeader(Constants.REQUEST_HEADER_IP);
	            if (remoteAddr == null || Constants.EMPTY.equals(remoteAddr)) {
	                remoteAddr = request.getRemoteAddr();
	            }
	        }
			System.out.println(remoteAddr);
		}catch(Exception e) {
			System.out.println("Exception in preHandle :: "+e.getMessage());
		}
		return true;
	}

	@Override 
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exception) throws Exception {

	} 

}