package co.kr.config.security;

import java.beans.ExceptionListener;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Form Login(AuthenticationFilter)에서 인증이 성공했을 때 수행될 핸들러
 * SimpleUrlAuthenticationSuccessHandler를 상속받아 클래스를 구현합니다.
 * 추상클래스가 아니라도 인터페이스형태로도 가능합니다.
 * 성공 시 인증토큰을 쿠키에 넣어주거나, index페이지로 리다이렉트 하는 역할을 수행합니다.
 * */
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	// implements ExceptionProcessor 없음

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		log.debug("CustomAuthenticationSuccessHandler.onAuthenticationSuccess ::::");
		
		// 쿠키에 인증 토큰을 넣어준다. 
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
