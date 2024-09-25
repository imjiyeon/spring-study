package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;


@Controller
//모든 메소드에 공통으로 중간경로가 설정됨
@RequestMapping("/param")
//모든 메소드가 JSON형식으로 응답을 반환함
@ResponseBody
public class QuizController2 {

	//1,2,3번: 간단한 파라미터는 URI에 데이터를 담을 것
	//@RequestParam 또는 @PathVariable

	@GetMapping("/q1")
	public String quiz1(@RequestParam(name = "str") String str) {

		System.out.println("String타입 파라미터 수집: " + str);
		return "ok";
	}

	@GetMapping("/q2")
	public String quiz2(@RequestParam(name = "f") float f, @RequestParam(name = "b") boolean b) {

		System.out.println("float타입 파라미터 수집: " + f + ", boolean타입 파라미터 수집: " + b);
		return "ok";
	}

	// 같은 이름으로 여러번 파라미터를 보낼 수 있음
	// 예: ?arr=a&arr=b&arr=c
	@GetMapping("/q3")
	public String quiz3(@RequestParam(name = "arr") char[] arr) {

		System.out.println("char형 배열 수집: " + Arrays.toString(arr)); //배열 -> 문자열로 변환
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		System.out.println("배열의 개수:" + arr.length);
		return "ok";
	}

	//4,5,6번: 복잡한 파라미터는 메세지 바디에 데이터를 담을 것
	//@RequestBody 사용하여 JSON형식의 문자열을 클래스로 변환

	@PostMapping("/q4")
	public String quiz4(@RequestBody StudentDTO dto) {

		System.out.println("객체 수집: " + dto);
		return "ok";
	}

	//JSON에서 객체는{} 리스트는 []로 작성
	@PostMapping("/q5")
	public String quiz5(@RequestBody ArrayList<StudentDTO> list) { //JSON -> 클래스 변환

		System.out.println("객체타입 리스트 수집: " + list);
		for(StudentDTO dto : list) {
			System.out.println(dto);
		}
		// 리스트의 크기는 SIZE함수를 사용
		System.out.println("리스트의 개수:" + list.size());
		return "ok";
	}

	@PostMapping("/q6")
	public String quiz6(@RequestBody ArrayList<CarDTO> list) { //JSON -> 클래스 변환

		int size = list.size();
		System.out.println("객체타입 리스트 수집: " + list);
		for(CarDTO dto : list) {
			System.out.println(dto);
		}
		// 마지막요소의 인덱스를 구해야함
		// 리스트 크기 - 1
		System.out.println("리스트 마지막 요소: " + list.get(size-1));
		return "ok";
	}

}
