package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//  1.다음과 같이 카페 매니저(Manager)클래스를 설계하세요
//   아무것도 없음
//  2.다음과 같이 카페(Cafe)클래스를 설계하세요
//   속성: 카페 매니저
//  3.스프링 컨테이너에 카페와 매니저 객체를 저장하세요
//  4.단위테스트 클래스를 작성하세요
//   Cafe타입의 변수를 선언하고, 스프링 컨테이너에서 객체를 해당 주입받으세요
//   Manager타입의 변수를 선언하고, 스프링 컨테이너에서 해당 객체를 주입받으세요
//   카페와 매니저 객체를 사용하여 매니저의 주소를 확인하세요

@SpringBootTest
public class Quiz4 {

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
