package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 * 요청 매핑
 *
 * 클래스 레벨과 메소드 레벨에 각각 url을 지정하여 주소 조합
 * */

@RequestMapping("/board") //중간경로
@Controller
public class MappingController2 {

	// GET localhost:8080/board/list
	@ResponseBody
	@GetMapping("/list") //마지막경로
	public String list() {
		System.out.println("게시물 조회..");
		return "ok";
	}

	// POST localhost:8080/board/save
	@ResponseBody
	@PostMapping("/save")
	public String save() {
		System.out.println("게시물 등록..");
		return "ok";
	}

	// PUT localhost:8080/board/modify
	@ResponseBody
	@PutMapping("/modify")
	public String modify() {
		System.out.println("게시물 수정..");
		return "ok";
	}

	// DELETE localhost:8080/board/remove
	@ResponseBody
	@DeleteMapping("/remove")
	public String remove() {
		System.out.println("게시물 삭제..");
		return "ok";
	}

}
