/*
 * package org.net.erp.config;
 * 
 * import org.net.erp.interceptor.Interceptor; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.InterceptorRegistry; import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 * 
 * @Configuration public class WebMvcConfig implements WebMvcConfigurer {
 * 
 * @Autowired Interceptor interceptor;
 * 
 * @Override public void addInterceptors(InterceptorRegistry registry) {
 * registry.addInterceptor(interceptor) .excludePathPatterns("/js/**")
 * .excludePathPatterns("/css/**") .excludePathPatterns("/assets/**")
 * .excludePathPatterns("/fonts/**") .excludePathPatterns("/images/**")
 * .excludePathPatterns("/favicon") .excludePathPatterns("/registration/**"); }
 * 
 * }
 */