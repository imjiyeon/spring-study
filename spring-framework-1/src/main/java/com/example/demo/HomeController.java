package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

	/* 메인화면 */
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	/* 로그인페이지 */
	@GetMapping(value = "/customLogin")
	public String login() {
		return "login";
	}
	
	/* 로그인 실패처리 */
	@PostMapping(value = "/customLoginFail")
	public String loginFail(@RequestParam String loginFailMessage, Model model) {
		model.addAttribute("loginFailMessage", loginFailMessage); //에러메세지
		return "login";
	}
	
	/* 접근제한 에러페이지 */
	@GetMapping(value = "/accessError")
	public String accessError(Authentication auth) { //스프링 시큐리티가 사용자정보를 파라미터로 받아온다
		log.info("access denied : " + auth);
		return "accessError";
	}
	
}
