
package org.net.erp.services;

import java.util.Arrays;

import org.net.erp.model.RegisterMember; import
org.net.erp.repository.RegisterMemberRepository;
import org.net.erp.util.Constants;
import
org.springframework.beans.factory.annotation.Autowired; import
org.springframework.security.core.GrantedAuthority; import
org.springframework.security.core.authority.SimpleGrantedAuthority; import
org.springframework.security.core.userdetails.User; import
org.springframework.security.core.userdetails.UserDetails; import
org.springframework.security.core.userdetails.UserDetailsService; import
org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class UserAuthService implements UserDetailsService {

	@Autowired 
	private RegisterMemberRepository registerMemberRepository;

	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null; 
		try {
			RegisterMember registerMember = registerMemberRepository.findByEmailId(username);
			if(null == registerMember) { 
				registerMember = registerMemberRepository.findByMobileNumber(username); 
			} 	
			if(null != registerMember) {
				GrantedAuthority authority = new SimpleGrantedAuthority(registerMember.getMember_type());
				userDetails = (UserDetails) new User(registerMember.getFirst_name()+Constants.COMMA+registerMember.getRegisterOrganization().getMasterId()+Constants.COMMA+registerMember.getMember_id(),registerMember.getMemberPassword(),Arrays.asList(authority)); 
			}
		}catch(Exception e) {

		}
		return userDetails; 
	}

}
