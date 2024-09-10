package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Quiz1 {

	// 컨테이너에 저장된 Dog 객체(빈)을 꺼내서 주입
	// dog변수는 객체의 주소를 참조
	@Autowired
	Dog dog;

	@Test
	void 강아지객체가_컨테이너에_생성되었는지확인() {
		dog.sound();
	}
}
