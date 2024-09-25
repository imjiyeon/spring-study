package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;

@Controller
@RequestMapping("/return")
public class QuizController3 {

	// 파일이름과 마지막 경로가 같을 때는 리턴타입으로 void를 사용
	// q1.html 파일은 return 폴더 아래 만들면됨
	@GetMapping("/q1")
	public void quiz1() {
		// template/return/q1.html 뷰 파일의 경로 자동으로 반환
	}

	// 파일이름과 마지막 경로가 다를 때는 리턴타입으로 String를 사용
	// test 파일은 원하는 위치에 만들면됨
	@GetMapping("/q2")
	public String quiz2() {
		return "/return/test.html"; //파일 경로를 직접 작성
	}

	// DTO 객체를 JSON문자열로 변환하여 전송
	@ResponseBody
	@GetMapping("/q3")
	public StudentDTO quiz3() {
		StudentDTO studentDTO = new StudentDTO(1,"둘리",3);
		return studentDTO;
	}

	@ResponseBody
	@GetMapping("/q4")
	public CarDTO quiz4() {
		CarDTO carDTO = new CarDTO("현대","코나","블랙");
		return carDTO; //CarDTO 객체를 반환
	}

	@ResponseBody
	@GetMapping("/q5")
	public List<StudentDTO> quiz5() {
		List<StudentDTO> list = new ArrayList<>();
		list.add(new StudentDTO(1,"둘리",3));
		list.add(new StudentDTO(2,"또치",1));
		list.add(new StudentDTO(3,"도우너",2));
		return list; //객체 리스트 반환
	}

	// 메세지의 헤더를 직접 설정할 때는 ResponseEntity를 사용
	@GetMapping("/q6")
	public ResponseEntity<String> quiz6() {
		//500 ok 응답 + message
		return new ResponseEntity<>("response fail..", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/q7")
	public ResponseEntity<CarDTO> quiz7() {
		CarDTO carDTO = new CarDTO("현대","코나","블랙");
		//200 ok 응답 + CarDTO 객체
		return new ResponseEntity<>(carDTO, HttpStatus.OK);
	}

}
