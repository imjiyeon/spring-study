package com.example.spring11.controller;

import com.example.spring11.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class HomeController {

	/*
	* 컨트롤러에서 로그인된 사용자의 정보를 확인하는 방법
	* - 컨트롤러가 제공하는 파라미터(Principal) 사용하기
	* */
	
	@GetMapping("/")
	public String main(Principal principal) {
		System.out.println(principal); // 로그인된 사용자 정보 출력
		return "/home/main";
	}

}
