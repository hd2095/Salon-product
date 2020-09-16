package org.net.erp.config;

import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	@ConditionalOnEnabledResourceChain
	public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
		return new ResourceUrlEncodingFilter();
	}

}
