package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;

// 1. 주소
// 모든 주소의 중간경로가 같으므로 /param을 클래스 레벨에 적용
@Controller
@RequestMapping("/param")
public class QuizController2 {

	// 2. 파라미터 형태
	// 간단 -> @RequestParam 또는 @PathVariable
	// 복잡 -> @RequestParam, @PathVariable 보다는 @RequestBody

	@GetMapping("/q1")
	public ResponseEntity quiz1(@RequestParam(name = "str") String str) {

		System.out.println("String타입 파라미터 수집: " + str);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/q2")
	public ResponseEntity quiz2(@RequestParam(name = "f") float f, @RequestParam(name = "b") boolean b) {

		System.out.println("float타입 파라미터 수집: " + f + ", boolean타입 파라미터 수집: " + b);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/q3")
	public ResponseEntity quiz3(@RequestParam(name = "arr") char[] arr) {

		System.out.println("char형 배열 수집: " + Arrays.toString(arr)); //배열 -> 문자열로 변환

		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}

		System.out.println("배열의 개수:" + arr.length);

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/q4")
	public ResponseEntity quiz4(@RequestBody StudentDTO dto) { //JSON -> 클래스 변환

		System.out.println("객체 수집: " + dto);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/q5")
	public ResponseEntity quiz5(@RequestBody ArrayList<StudentDTO> list) { //JSON -> 클래스 변환

		System.out.println("객체타입 리스트 수집: " + list);

		for(StudentDTO dto : list) {
			System.out.println(dto);
		}

		System.out.println("리스트의 개수:" + list.size());

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/q6")
	public ResponseEntity quiz6(@RequestBody ArrayList<CarDTO> list) { //JSON -> 클래스 변환

		int size = list.size();

		System.out.println("객체타입 리스트 수집: " + list);

		for(CarDTO dto : list) {
			System.out.println(dto);
		}

		System.out.println("리스트 마지막 요소: " + list.get(size-1));

		return new ResponseEntity(HttpStatus.OK);
	}

}
