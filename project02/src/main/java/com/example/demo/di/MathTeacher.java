package com.example.demo.di;

public class MathTeacher implements Teacher{

	@Override
	public void teach() {
		System.out.println("수학 선생님이 수업을 가르칩니다");
	}

}
