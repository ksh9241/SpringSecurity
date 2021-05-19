package co.kr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer 
		extends AbstractSecurityWebApplicationInitializer{
	// AbstractSecurityWebApplicationInitializer 의 기능
	// 1. 어플리케이션의 모든 URL에 자동으로 SpringSecurityFilterChain 필터를 등록한다.
	// 2. WebSecuriyConfig를 로드하는 ContextLoaderListener를 추가한다.
}
