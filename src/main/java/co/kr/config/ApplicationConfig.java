package co.kr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@ComponentScan(basePackages = {"co.kr.**"})
@Import({DBConfig.class,MybatisConfig.class})
public class ApplicationConfig extends AbstractAnnotationConfigDispatcherServletInitializer{ //extends 한 클래스는 dispatcherServlet을 설정한다. 

	@Override
	protected Class<?>[] getRootConfigClasses() { // 리턴된 클래스(DBConfig)들은 ContextLoaderListender가 생성한 애플리케이션 컨텍스트를 설정하는데 사용된다.
		return new Class[] {DBConfig.class,WebSecurityConfig.class};	  // ContextLoaderListener : 애플리케이션 내의 그 외의 다른 빈을 로딩한다.
		//return new Class[] {DBConfig.class,WebSecurityConfig.class};	// springSecurityFilterChain 은 RootContext에 등록되어야 한다.
	} 

	@Override
	protected Class<?>[] getServletConfigClasses() { // 리턴된 클래스(WebMvcConfig)들은 DispatcherServlet의 애플리케이션 컨텍스트에 대한 핀들을 정의한다.
		return new Class[] {WebMvcConfig.class};	 // DispatcherServlet : 컨트롤러, 뷰, 리졸버, 핸들러 매핑과 같은 웹 컴포넌트가 포함된 빈을 로딩한다.
	}

	@Override
	protected String[] getServletMappings() { //DispatcherServlet이 매핑되기 위한 하나 혹은 여러개의 도메인을 지정한다.
		return new String[] {"/"}; 
	}
}
