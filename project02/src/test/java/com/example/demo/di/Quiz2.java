package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//  1.다음과 같이 고양이(Cat) 클래스를 설계하세요
//   eat 메소드: "쥐를 먹는다" 라는 메세지를 출력
//  2.스프링 컨테이너에 고양이 객체를 저장하세요
//  3.단위테스트 클래스를 작성하세요
//   Cat타입의 변수를 선언하고, 스프링 컨테이너에서 해당 객체를 주입받으세요
//   주입받은 변수를 사용하여 eat() 메소드를 호출하세요

@SpringBootTest
public class Quiz2 {

	@Autowired
	Cat cat;

	@Test
	void 고양이테스트() {
		cat.eat();
	}
}