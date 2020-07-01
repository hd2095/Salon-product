package org.net.erp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;

public class LoginMember {
	
	@NotBlank(message = Constants.CUSTOMER_USERNAME)
	private String username;
	
	@Size(min = 8, max = 15)
	@NotBlank(message = Constants.CUSTOMER_PASSWORD)	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
