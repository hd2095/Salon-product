package org.net.erp.config;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
    // In this example we do not use Security.
    // Override this method with empty code
    // to disable the default Spring Boot security.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Empty code!
		/*
		 * http.authorizeRequests() .anyRequest() .permitAll() .and()
		 * .sessionManagement() .invalidSessionStrategy(new
		 * CustomInvalidSessionStrategy());
		 */
    }
    
    private static class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
        @Override
        public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
          if (request.getSession(false) == null) { // IMPORTANT: retrieve session
            request.getSession(true);
            response.sendRedirect("/"); // home page
          } else {
            response.sendRedirect("/session-expired"); // expired session page
          }
        }
      }
}
