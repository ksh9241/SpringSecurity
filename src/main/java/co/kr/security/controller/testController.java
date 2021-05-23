package co.kr.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.kr.security.dto.UserDTO;
import co.kr.security.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class testController {
	@Autowired
	UserService userSvc;
	
	@GetMapping("/")
	public String defaultUrl(Model model) {
		List<UserDTO> list = userSvc.selectUserList();
		System.out.println("list.Size==="+list.size());
		for(UserDTO l : list) {
			System.out.println(l);
		}
		
		model.addAttribute("list",list);
		return "test";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		List<UserDTO> list = userSvc.selectUserList();
		System.out.println("list.Size==="+list.size());
		for(UserDTO l : list) {
			System.out.println(l);
		}
		
		model.addAttribute("list",list);
		return "test";
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
	
	@PostMapping("/create")
	public String CreateLoginUser(@ModelAttribute UserDTO user,Model model) {
		int result = userSvc.createUser(user);
		switch(result) {
		case 1 : 
			model.addAttribute("msg","계정생성에 성공하였습니다.");
			model.addAttribute("url","test");
			return "msg";
		case 2 :
			model.addAttribute("msg","이미 존재하는 유저명입니다.");
			model.addAttribute("url","signup");
			return "msg";
		}
		return "test";
	}
	
	@GetMapping("/signup")
	public String CreateLoginUserUrl() {
		return "security/signup";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin() {
		return null;
	}
}
