package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	public void work() {
		System.out.println("회사원이 일을 한다");
	}
}
