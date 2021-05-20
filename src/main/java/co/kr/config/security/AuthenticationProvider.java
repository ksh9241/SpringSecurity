package co.kr.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Repository;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
	
	@Autowired
	CustomUserDetailService userDeSer;

	@SuppressWarnings("unchcked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getPrincipal(); 	//화면에 입력한 아이디를 username에 담는다.
		String password = (String) authentication.getCredentials(); //화면에 입력한 비밀번호를 password에 담는다.
		
		CustomUserDetails user = (CustomUserDetails) userDeSer.loadUserByUsername(username); //DB에 있는 사용자의 정보를 UserDetails 타입으로 가져와 user에 담는다.
		
		if(!matchPassword(password,user.getPassword())) { //화면에 입력한 비밀번호와 DB에서 가져온 비밀번호를 확인 후 일치하지않을 시 예외처리한다.
			throw new BadCredentialsException(username);
		}
		
		if(!user.isEnabled()) { //계정 활성화 여부를 확인하는 로직이다. AuthenticationProvider 인터페이스를 구현하게 되면 계정 잠금 여부나 활성화 여부 등은 여기에서 확인한다.
			throw new BadCredentialsException(username);
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities()); 
		//계정이 인증됐다면 UsernamePasswordAuthenticationToken 객체에 화면에서 입력한 정보와 DB에서 가져온 권한을 담아서 리턴한다.
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	private boolean matchPassword(String loginPwd, String password) { //비밀번호를 비교하는 메서드이다.
		return loginPwd.equals(password);
	}

}
