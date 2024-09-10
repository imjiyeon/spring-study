package com.example.demo.di;

import org.springframework.stereotype.Component;

//Spring이 Dog 객체를 생성하고 컨테이너에 저장함
@Component
public class Dog {
	
	public void sound() {
		System.out.println("왕왕 짖는다");
	}
}
