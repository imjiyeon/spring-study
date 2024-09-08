package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 1.다음과 같이 선생님(Teacher) 인터페이스를 설계하세요
//  추상 메소드: teach()
// 2.Teacher 인터페이스를 구현하는 두 개의 클래스를 설계하세요
//  MathTeacher 클래스: teach 메소드에서 "수학 선생님이 수업을 가르칩니다" 라는 메세지를 출력
//  ScienceTeacher 클래스: teach 메소드에서 "과학 선생님이 수업을 가르칩니다" 라는 메세지를 출력
// 3.스프링 컨테이너에 MathTeacher와 ScienceTeacher 객체를 저장하세요
// 4.단위테스트 클래스를 작성하세요
//  Teacher 타입의 변수를 선언하고, MathTeacher 객체 또는 ScienceTeacher 객체를 주입받으세요
//  주입받은 변수를 사용하여 teach 메소드를 호출하세요

@SpringBootTest
public class Quiz6 {
	
	@Autowired
	Teacher teacher;
	
	@Test
	void 테스트() {
		teacher.teach();
	}

}
