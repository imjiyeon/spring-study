package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/*
 * 요청 파라미터 (HTML Form)
 * */
@Controller
@RequestMapping("/param3")
public class ParamController3 {

	// GET + localhost:8080/param2/register
	// 폼 화면을 반환하는 메소드
	@GetMapping("/register")
	public String ex1() {
		return "/param/register";
	}

	// 입력필드에 이름과 나이 입력 -> 버튼 클릭
	// (POST + localhost:8080/param2/register?username=둘리&age=20)
	// 폼에서 데이터를 전달받는 메소드
	@ResponseBody
	@PostMapping("/register")
	public String ex2(@ModelAttribute UserDTO dto) {
		System.out.println("username="+dto.getUsername()+",age="+dto.getAge());
		return "ok";
	}

}
