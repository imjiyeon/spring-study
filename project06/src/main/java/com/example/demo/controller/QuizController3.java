package com.example.demo.controller;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/return")
public class QuizController3 {

	/*
	 * 템플릿 return 폴더 밑에 q1.html파일을 추가하세요.
	 * localhost:8080/return/q1 주소로 요청이 들어오면 q1.html 파일을 반환하세요.
	 * */
	@GetMapping("/q1")
	public void quiz1() {
		// template/ + /return + /q1.html 파일을 자동으로 반환 
	}
	
	/*
	 * 템플릿 return 폴더 밑에 test.html파일을 추가하세요.
	 * localhost:8080/return/q2 주소로 요청이 들어오면 test.html 파일을 반환하세요.
	 * */
	@GetMapping("/q2")
	public String quiz2() {
		return "/return/test.html"; //html경로를 지정하여 반환
	}
	
	/*
	 * 학생클래스를 만드세요. (속성: 번호, 이름, 학년)
	 * localhost:8080/return/q3 주소로 요청이 들어오면 학생정보를 반환하세요.
	 * */
	@ResponseBody
	@GetMapping("/q3")
	public StudentDTO quiz3() {
		StudentDTO studentDTO = new StudentDTO(1,"둘리",3);
		return studentDTO; //StudentDTO 객체를 반환
	}
	
	/*
	 * 자동차클래스를 만드세요. (속성: 제조사, 모델, 색)
	 * localhost:8080/return/q4 주소로 요청이 들어오면 자동차정보를 반환하세요.
	 * */
	@ResponseBody
	@GetMapping("/q4")
	public CarDTO quiz4() {
		CarDTO carDTO = new CarDTO("현대","코나","블랙");
		return carDTO; //CarDTO 객체를 반환
	}
	
	/*
	 * localhost:8080/return/q5 주소로 요청이 들어오면 학생 목록을 반환하세요.
	 * */
	@ResponseBody
	@GetMapping("/q5")
	public List<StudentDTO> quiz5() {
		List<StudentDTO> list = new ArrayList<>();
		list.add(new StudentDTO(1,"둘리",3));
		list.add(new StudentDTO(2,"또치",1));
		list.add(new StudentDTO(3,"도우너",2));
		return list; //객체 리스트 반환		
	}
	
	/*
	 * localhost:8080/return/q6 주소로 요청이 들어오면
	 * ResponseEntity를 이용하여 500 에러코드와 "response fail.." 메세지를 반환하세요.
	 * */
	@GetMapping("/q6")
	public ResponseEntity<String> quiz6() {
		return new ResponseEntity<>("response fail..", HttpStatus.INTERNAL_SERVER_ERROR);  //500 ok 응답 + message
	}
	
	/*
	 * localhost:8080/return/q7 주소로 요청이 들어오면
	 * ResponseEntity를 이용하여 200 응답코드와 자동차정보를 반환하세요.
	 * */
	@GetMapping("/q7")
	public ResponseEntity<CarDTO> quiz7() {
		CarDTO carDTO = new CarDTO("현대","코나","블랙");
		return new ResponseEntity<>(carDTO, HttpStatus.OK);  //200 ok 응답 + CarDTO 객체
	}
	
}
