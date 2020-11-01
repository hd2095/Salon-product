package org.net.erp.config;
import org.net.erp.handler.FailureHandler;
import org.net.erp.handler.SuccessHandler;
import org.net.erp.services.UserAuthService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private SuccessHandler successHandler;

	@Autowired
	private FailureHandler failureHandler;

	@Autowired 
	private UserAuthService userAuthService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
		.authorizeRequests()
		.antMatchers(Constants.FORWARD_SLASH)
		.permitAll()
		.antMatchers("/forgotPassword/**")
		.permitAll()
		.antMatchers("/signup/**")
		.permitAll()
		.antMatchers("/complete-organization-registration/**")
		.permitAll()
		.antMatchers("/createOrganization/**")
		.permitAll()
		.antMatchers("/verify/**")
		.permitAll()
		.antMatchers("/forgot-password/**")
		.permitAll()
		.antMatchers("/autoLogin/**")
		.permitAll()
		.antMatchers("/login/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage(Constants.FORWARD_SLASH + "login")
		.successHandler(successHandler)
		.failureHandler(failureHandler)
		.and()
		.logout()
		.logoutSuccessUrl(Constants.FORWARD_SLASH)
		.invalidateHttpSession(true)	
		.permitAll()
		.and()
		.csrf()
		.disable()
		.sessionManagement()
		.sessionFixation()
		.migrateSession();
	}

	@Override
	public void configure(WebSecurity web) {
		web
		.ignoring() 
		.antMatchers("/assets/**");
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userAuthService).passwordEncoder(bCryptPasswordEncoder); 
	}


}

