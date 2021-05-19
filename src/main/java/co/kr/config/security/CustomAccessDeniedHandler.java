package co.kr.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		response.setStatus(HttpStatus.FORBIDDEN.value());
//		
//		if(accessDeniedException instanceof AccessDeniedException) {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			
//		}
	}

	public void setErrorPage(String string) {
		
	}

}
