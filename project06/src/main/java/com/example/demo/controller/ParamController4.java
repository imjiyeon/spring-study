package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BookDTO;

/*
 * 요청 메세지
 * postman 테스트: body -> row, json 선택
 * */
@Controller
@RequestMapping("/param4")
public class ParamController4 {

	//GET localhost:8080/param4/ex1 + json
	@ResponseBody
	@GetMapping("/ex1")
	public String ex1(@RequestBody BookDTO dto) { //JSON -> 클래스 변환
		System.out.println("메세지 바디에 담긴 json 객체 꺼내기: " + dto);
		return "ok";
	}

	//GET localhost:8080/param4/ex2 + json
	@ResponseBody
	@GetMapping("/ex2")
	public String ex2(@RequestBody ArrayList<BookDTO> list) { //JSON -> 클래스 변환
		System.out.println("메세지 바디에 담긴 json 리스트 수집: " + list);
		return "ok";
	}

//	@GetMapping("/")
//	public String ex() {
//		System.out.println();
//		return "ok";
//	}

}
