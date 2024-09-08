package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//  1.다음과 같이 의사(Doctor)클래스을 설계하세요
//   아무것도 없음
//  2.다음과 같이 병원(Hospital)클래스을 설계하세요
//   속성: 담당의사
//  3.스프링 컨테이너에 병원과 의사 객체를 저장하세요
//  4.단위테스트 클래스를 작성하세요
//   Hospital타입의 변수를 선언하고, 스프링 컨테이너에서 해당 객체를 주입받으세요
//   Doctor타입의 변수를 선언하고, 스프링 컨테이너에서 해당 객체를 주입받으세요
//   병원과 의사 객체를 사용하여 의사의 주소를 확인하세요

@SpringBootTest
public class Quiz5 {

	@Autowired
	Hospital hospital;
	
	@Autowired
	Doctor doctor;
	
	@Test
	void 객체가생성되었는지확인() {
		System.out.println("hospital: " + hospital);
		System.out.println("doctor: " + doctor);
	}

	@Test
	void 두객체가같은지확인() {
		// 두 객체의 주소가 같음
		System.out.println("doctor: " + doctor);
		System.out.println("hospital의 doctor: " + hospital.doctor);
	}
	
}
