package co.kr.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//CustomUserDetails user = userAuthDAO.getUserById(username); //사용자의 정보를 CustomUserDetails 타입으로 가져온다.
		CustomUserDetails user = new CustomUserDetails(); 
		if(user == null) { //만약 해당 username의 사용자가 없으면 예외를 던져준다.
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}
