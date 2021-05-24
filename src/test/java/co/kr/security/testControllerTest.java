package co.kr.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.kr.config.ApplicationConfig;
import co.kr.config.DBConfig;
import co.kr.config.MybatisConfig;
import co.kr.config.WebMvcConfig;
import co.kr.config.WebSecurityConfig;
import co.kr.security.dto.UserDTO;
import co.kr.security.mapper.UserMapper;
import co.kr.security.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class) // JUnitTest클래스를 실행하기 위한 러너를 명시적으로 지정한다.
@Transactional
@ContextConfiguration(classes = {MybatisConfig.class, WebMvcConfig.class,DBConfig.class,
		ApplicationConfig.class,WebSecurityConfig.class})
public class testControllerTest {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserServiceImpl userSvc;
	
	@Autowired
	UserMapper userMapper;
	
	

	@Test
	public void test() {
		UserDTO user = new UserDTO();
		String password = passwordEncoder.encode("password");
		user.setUsername("testUser");
		user.setPassword(password);
		System.out.println(user);
		assertEquals(userMapper.createUser(user), 1);
		
	}

}
