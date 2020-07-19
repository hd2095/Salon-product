/*
 * package org.net.erp.interceptor;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.net.erp.util.Constants; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.servlet.HandlerInterceptor; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * @Component public class Interceptor implements HandlerInterceptor{ private
 * static String redirectUrl = Constants.EMPTY;
 * 
 * @Override public boolean preHandle( HttpServletRequest request,
 * HttpServletResponse response, Object handler) throws Exception {
 * if(!(request.getRequestURI().equalsIgnoreCase(Constants.EMPTY) ||
 * request.getRequestURI().equalsIgnoreCase(Constants.FORWARD_SLASH) ||
 * request.getRequestURI().equalsIgnoreCase(Constants.FORWARD_SLASH +
 * Constants.LOGIN_JSP))) { String requestUri = request.getRequestURI();
 * if(!(requestUri.contains(Constants.STATIC_RESOURCES_CSS) ||
 * requestUri.contains(Constants.STATIC_RESOURCES_FONTAWESOME) ||
 * requestUri.contains(Constants.URL_ERROR))) { if(null ==
 * request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
 * redirectUrl = request.getRequestURI();
 * request.getSession().setAttribute(Constants.REDIRECT_URL, redirectUrl);
 * response.sendRedirect(Constants.FORWARD_SLASH); return false; } } } return
 * true; }
 * 
 * @Override public void postHandle( HttpServletRequest request,
 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
 * throws Exception {}
 * 
 * @Override public void afterCompletion(HttpServletRequest request,
 * HttpServletResponse response, Object handler, Exception exception) throws
 * Exception {} }
 */