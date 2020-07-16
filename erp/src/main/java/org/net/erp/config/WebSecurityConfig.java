package org.net.erp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    }
    
}
