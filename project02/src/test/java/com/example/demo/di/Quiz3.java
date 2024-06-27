package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//  1.다음과 같이 카페매니저(Manager)클래스를 만드세요
//  - 아무것도 없음
//  2.다음과 같이 카페(Cafe)클래스를 만드세요
//  - 속성: 카페매니저
//  3.스프링컨테이너에 카페, 매니저 객체를 저장하세요
//  4.단위테스트 클래스를 만드세요
//  5.Cafe타입의 변수를 선언하고 컨테이너에서 객체를 주입받으세요
//  6.Manager타입의 변수를 선언하고 컨테이너에서 객체를 주입받으세요
//  7.카페와 매니저를 사용하여 매니저의 주소를 확인하세요

@SpringBootTest
public class Quiz3 {

	@Autowired
	Cafe cafe;
	
	@Autowired
	Manager manager;
	
	@Test
	void 객체가생성되었는지확인() {

		System.out.println("cafe: " + cafe);
		System.out.println("manager: " + manager);
	}

	@Test
	void 두객체가같은지확인() {
		// 두 객체의 주소가 같음
		System.out.println("manager: " + manager);
		System.out.println("cafe의 manager: " + cafe.manager);
	}

}
