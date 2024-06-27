package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/method/q")
//@RequestMapping("/method")
public class QuizController1 {

	// get방식 + /method/q
	@ResponseBody
	@GetMapping
//	@GetMapping("/q")
	public String quiz1() {
		System.out.println("get메소드 요청..");
		return "ok";
	}

	// post방식 + /method/q
	@ResponseBody
	@PostMapping
	public String quiz2() {
		System.out.println("post메소드 요청..");
		return "ok";
	}

	// put방식 + /method/q
	@ResponseBody
	@PutMapping
	public String quiz3() {
		System.out.println("put메소드 요청..");
		return "ok";
	}

	// delete방식 + /method/q
	@ResponseBody
	@DeleteMapping()
	public String quiz4() {
		System.out.println("delete메소드 요청..");
		return "ok";
	}

}
