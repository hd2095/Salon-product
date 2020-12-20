
package org.net.erp.services;

import java.time.LocalDate;
import java.util.Arrays;
import org.net.erp.model.RegisterMember;
import org.net.erp.repository.RegisterMemberRepository;
import org.net.erp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class UserAuthService implements UserDetailsService {

	@Autowired 
	private RegisterMemberRepository registerMemberRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthService.class);

	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		RegisterMember registerMember = null;
		try {
			if(username.contains(Constants.AT)) {
				registerMember = registerMemberRepository.findByEmailId(username);	
			}else {
				registerMember = registerMemberRepository.findByMobileNumber(username);
			}
			if(null != registerMember) {
				GrantedAuthority authority = new SimpleGrantedAuthority(registerMember.getMember_type());
				if(registerMember.getExpires_on().isBefore(LocalDate.now()) || registerMember.getExpires_on().equals(LocalDate.now())) {
					userDetails = (UserDetails) new User(username, registerMember.getMemberPassword(), false, false, true, false, Arrays.asList(authority));
				}else {
					if(null == registerMember.getRegisterOrganization()) {
						userDetails = (UserDetails) new User(registerMember.getFirst_name()+Constants.COMMA+registerMember.getLast_name()+Constants.COMMA+registerMember.getMember_id(),registerMember.getMemberPassword(),Arrays.asList(authority));
					}else {
						userDetails = (UserDetails) new User(registerMember.getFirst_name()+Constants.COMMA+registerMember.getLast_name()+Constants.COMMA+registerMember.getRegisterOrganization().getMaster_id()+Constants.COMMA+registerMember.getMember_id(),registerMember.getMemberPassword(),Arrays.asList(authority));						
					}		
				}							
			}else {
				GrantedAuthority authority = new SimpleGrantedAuthority(Constants.USER_NOT_FOUND_DB);
				userDetails = (UserDetails) new User(username,username,false,false,false,true,Arrays.asList(authority));
			}
		}catch(Exception e) {
			LOGGER.error("Exception in loadUserByUsername :: "+e.getMessage());
		}
		return userDetails; 
	}

}
