package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 * 요청 매핑
 *
 * @RequestMapping 또는 @GetMapping 사용하기
 * http 메소드가 다르면, url이 같아도 중복 아님
 * */
@Controller
public class MappingController1 {

	// GET localhost:8080/board
	@ResponseBody  // 메세지 바디에 데이터를 직접 담는 기능
	@RequestMapping(value = "/board", method = RequestMethod.GET)
//	@GetMapping("/board")
	public String list() {
		System.out.println("게시물 조회..");
		return "ok";
	}

	// Post localhost:8080/board
	@ResponseBody
	@PostMapping("/board")
	public String save() {
		System.out.println("게시물 등록..");
		return "ok";
	}

	// Put localhost:8080/board
	@ResponseBody
	@PutMapping("/board")
	public String modify() {
		System.out.println("게시물 수정..");
		return "ok";
	}

	// Delete localhost:8080/board
	@ResponseBody
	@DeleteMapping("/board")
	public String remove() {
		System.out.println("게시물 삭제..");
		return "ok";
	}

}
