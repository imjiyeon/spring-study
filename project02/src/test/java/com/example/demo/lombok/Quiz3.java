package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//  1) 다음과 같이 학생(Student) 클래스을 설계하세요
//  속성: 학번, 이름, 나이
//  기능: 모든 멤버변수의 getter/setter, 디폴트생성자, 모든 멤버변수를 초기화하는 생성자, 학생 정보를 반환하는 메소드
//  2) 학생 인스턴스를 3개 생성하세요
//  3) 모든 학생의 정보를 출력하세요

@SpringBootTest
public class Quiz3 {

	@Test
	void 학생클래스생성테스트() {
		Student student1 = new Student();
		student1.setStudentId(1001);
		student1.setStudentName("둘리");
		student1.setAge(15);
		System.out.println(student1.getStudentId());
		System.out.println(student1.getStudentName());
		System.out.println(student1.getAge());

		Student student2 = new Student(1002, "또치", 16);
		System.out.println(student2.toString());

		// builder는 순서를 자유롭게 입력할 수 있음
		Student student3 = Student.builder().studentName("도우너").studentId(1003).age(17).build();
		System.out.println(student3.toString());

	}
}
