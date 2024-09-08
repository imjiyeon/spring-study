package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class ScienceTeacher implements Teacher{

	@Override
	public void teach() {
		System.out.println("과학 선생님이 수업을 가르칩니다");
	}

}
