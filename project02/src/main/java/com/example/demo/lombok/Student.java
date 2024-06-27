package com.example.demo.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 학생 클래스

@Getter
@Setter
@ToString
@NoArgsConstructor // 생성자
@AllArgsConstructor // 생성자
@Builder // 생성자
public class Student {

	int studentId; //학번
	String studentName; //이름
	int age; //나이
}
