package co.kr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import co.kr.config.security.CustomAccessDeniedHandler;
import co.kr.config.security.CustomAuthenticationEntryPoint;
import co.kr.config.security.CustomAuthenticationFailureHandler;
import co.kr.config.security.CustomAuthenticationSuccessHandler;
import co.kr.config.security.CustomLogoutSuccessHandler;
import co.kr.config.security.JwtAuthenticationFilter;
import co.kr.config.security.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{ //security-context 를 클래스화 함.
	// 이 클래스는 SrpingSecurityFilterChain으로 알려진 어플리케이션 내 모든 보안 처리를 (어플리케이션 URL 보호, 제출한 사용자 이름과 비밀번호 검증, 로그인 폼으로 리다이렉트 등)
	// 담당하는 서블릿 필터를 생성한다. 다음은 가장 기본적인 스프링 시큐리티 자바 설정의 예시다.
	
	/**
	 * No qualifying bean of type 'org.springframework.security.config.annotation.ObjectPostProcessor<?>' available : 어노테이션 두개 다 있는데 왜 못찾는지 모르겠다.
	 * */
	

	// 스프링 시큐리티가 사용자를 인증하는 방법이 담긴 객체
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		// AuthenticationProvider 구현체
//		auth.authenticationProvider(authenticationProvider);
//		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	}
	
	// 스프링 시큐리티 룰을 무시하게 하는 URL (여기 추가된 URL은 시큐리티의 규칙에 적용되지않음)
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//			.antMatchers("/resources/**")
//			;
//	}
	
	// 스프링 시큐리티 규칙 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 보호된 리소스 URI에 접근할 수 있는 권한을 설정
			.antMatchers("/login").permitAll() // 전체 접근 허용
			//.antMatchers("/logout/**").permitAll()
			.antMatchers("/test").permitAll()
			.antMatchers("/msg").permitAll()
			.antMatchers("/admin").hasRole("ADMIN") //ADMIN이라는 ROLE(룰)을 가진 사용자만 접근 허용
			.antMatchers("/welcome").hasRole("USER")
			.anyRequest().authenticated().and().logout()
			.and()
			.formLogin()
				.loginPage("/login")
			.and()
				.logout()
					.logoutSuccessUrl("/test")
				//.logoutUrl("/logout")
				//.logoutSuccessHandler(logoutSuccessHandler())
				.and().csrf() // csrf(cross site request forgery)보안 설정을 비활성화
					.disable() // 해당 기능을 사용하기 위해서는 프론트단에서 csrf토큰값 보내줘야함.
				//.addFilter(jwtAuthenticationFilter()) // Form Login에 사용되는 custom AuthenticationFilter 구현체를 등록
				//.addFilter(jwtAuthorizationFilter()) // Header 인증에 사용되는 BasicAuthenticationFilter 구현체를 등록
				//.exceptionHandling()
					//.accessDeniedHandler(accessDeniedHandler())
					//.authenticationEntryPoint(authenticationEntryPoint())
				;
	}
	/**
	 * antMatchers()로 지정할 수 있는 항목
	 * - hasRole() or hasAnyRole() : 특정 권한을 가지는 사용자만 접근 할 수 있다.
	 * - hasAuthority() or hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있다.
	 * - hasIpAddress() : 특정 아이피 주소를 가지는 사용자만 접근할 수 있다.
	 * - permitAll() or denyAll() : 접근을 전부 허용하거나 제한합니다.
	 * - rememberMe() : 리멤버 기능을 통해 로그인한 사용자만 접근할 수 있습니다.
	 * - anonymous() : 인증되지 않은 사용자가 접근할 수 있습니다.
	 * - authenticated() : 인증된 사용자만 접근할 수 있습니다.
	 * 
	 * Role은 역할이고 Authority는 권한이지만 사실은 표현의 차이입니다.
	 * Role은 "ADMIN"으로 표현하고 Authority는 "ROLE_ADMIN"으로 표기합니다.
	 * */
	
	// SuccessHandler bean register
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/index");
		return successHandler;
	}
	
	// FailureHandler Bean register
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/loginPage?error=error");
		return failureHandler;
	}
	
	// LogoutSuccessHandler Bean register
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		CustomLogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
		logoutSuccessHandler.setDefaultTargetUrl("/logoutPage?logout=logout");
		return logoutSuccessHandler;
	}
	
	// AccessDeniedHandler Bean register
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler(); 
		accessDeniedHandler.setErrorPage("/error/403");
		return accessDeniedHandler;
	}
	
	// AuthenticationEntrypoint Bean register
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint("/loginPage?error=e");
	}
	
	// Form Login 시 걸리는 Filter Bean register
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		jwtAuthenticationFilter.setUsernameParameter("username");
		jwtAuthenticationFilter.setPasswordParameter("password");
		
		jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
		
		return jwtAuthenticationFilter;
	}
	
	// Filter Bean register
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager());
		return jwtAuthorizationFilter;
	}
	
	// 간단하게 비밀번호 암호화 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	
	
	//example 기능
	//1. 어플리케이션의 모든 URL에 인증요구	2. 로그인 폼 생성 	3. 사용자 이름 user와 비밀번호 password를 사용한 사용자의 폼 기반 인증 지원
	//4. 사용자 로그아웃 지원		5. CSRF(cross site request forgery)공격 방어		6. Session Fixation방어
	//7. 보안 헤더 통합		8. 서블릿 API 메소드 통합
	//	@Bean
	//	public UserDetailsService userDetailService() throws Exception{ //이 부분은 설정한 아이디와 비밀번호가 일치할 경우 설정한 룰의 권한을 준다.
	//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	//		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("123").roles("ROLE_USER").build());
	//		manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("123").roles("ROLE_ADMIN","ROLE_USER").build());
	//		
	//		return manager;
	//	}
}
