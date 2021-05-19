package co.kr.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그아웃에 성공했을 시 수행되는 핸들러
 * SimpleUrlLogoutSuccessHandler를 상속하며, 로그아웃 성공 시 로그인 페이지로 리다이렉트 하는 역할을 수행합니다.
 * */
@Slf4j
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.debug("CustomLogoutSuccessHandler.onLogoutSuccess:::");
		super.onLogoutSuccess(request, response, authentication);
	}
}
