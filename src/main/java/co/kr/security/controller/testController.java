package co.kr.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class testController {
	public void test() {
		log.debug("testMethod 들어옴 :::");
	}
	
	@GetMapping("/login")
	public String loginForm() {
		log.debug("loginForm Method 들어옴 :::");
		return "security/login";
	}
	
	@GetMapping("/welcome")
	public String welcomeForm() {
		log.debug("welcomeForm Method 들어옴 :::");
		return "security/welcome";
	}
}
