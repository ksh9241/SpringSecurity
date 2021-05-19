package co.kr.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Form Login 실패 시 수행되는 핸들러
 * SimpleUrlAuthenticationFailureHandler를 상속받으며, 실패 시 로그인 페이지로 리다이렉트 하는 역할을 수행합니다.
 * */
@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.debug("CustomAuthenticationFailureHandler.onAuthenticationFailure:::");
		super.onAuthenticationFailure(request, response, exception);
	}
}
