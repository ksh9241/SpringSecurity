package co.kr.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * Form Login 시 걸리는 Filter
 * UsernamePasswordAuthenticationFilter를 상속하며, 사용자가 Form으로 입력한 로그인 정보를 인터셉트해서 AuthenticationManager에게 Authentication 객체를 넘겨줍니다.
 * */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private boolean postOnly = true;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	// 해당 필터에서 인증 프로세스 이전에 요청해서 사용자 정보를 가져와서 Authentication 객체를 인증 프로세스 객체에게 전달하는 역할
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.debug("JwtAuthentication.attemptAuthentication :::");
		
		//POST로 넘어왔는지 체크
		if(postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
				"Authentication method not supported: "+request.getMethod());
		}
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if(StringUtils.isEmpty(username)) {
			username = "";
		}
		if(StringUtils.isEmpty(password)) {
			password = "";
		}
		
		username = username.trim();
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		setDetails(request, authRequest);
		
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
