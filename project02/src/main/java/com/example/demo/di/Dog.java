package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class Dog {
	
	public void sound() {
		System.out.println("왕왕 짖는다");
	}
}
