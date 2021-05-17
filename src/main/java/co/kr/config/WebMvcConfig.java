package co.kr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"co.kr.**"})
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		//↑ DispatcherServlet이 고정적인 리소스들에 대한 요청을 자신이 집접 처리하지 않고 서블릿 컨테이너의 디폴트 서블릿 전달을 요청한다.
	}

	@Override //리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해 리소스 위치와 이 리소스와 매칭될 url을 등록한다.
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("resources");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("test");
	}
	
	@Bean
	public ViewResolver viewResolver() { //지정한 접두사와 접미사를 통해 JSP파일을 찾아주는 역할
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		resolver.setExposeContextBeansAsAttributes(true); //스프링 빈을 ApplicationContext에 접근가능하게 설정
		return resolver;
	}
}
