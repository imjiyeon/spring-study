package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//  1.다음과 같이 교사(Teacher)클래스을 만드세요
//  - 아무것도 없음
//  2.다음과 같이 수업(Class)클래스을 만드세요
//  - 속성: 담당교사
//  3.스프링컨테이너에 수업, 선생님 객체를 저장하세요
//  4.단위테스트 클래스를 만드세요
//  5.Class타입의 변수를 선언하고 컨테이너에서 객체를 주입받으세요
//  6.Teacher타입의 변수를 선언하고 컨테이너에서 객체를 주입받으세요
//  7.수업와 교사를 사용하여 교사의 주소를 확인하세요

@SpringBootTest
public class Quiz4 {

	@Autowired
	Class class1;
	
	@Autowired
	Teacher teacher;
	
	@Test
	void test() {

		System.out.println("teacher: " + teacher);
		
		System.out.println("getTeacher(): " + class1.getTeacher());	// 두 객체의 주소가 같음
	}
}
