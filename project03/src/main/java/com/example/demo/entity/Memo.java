package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 데이터베이스의 테이블 구조를 정의하는 클래스
 * */

@Entity // 엔티티 클래스임을 명시
@Table(name = "tbl_memo") // 테이블 이름. 생략하면 클래스 이름과 동일한 이름으로 생성됨
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {

	@Id // PK. 엔티티는 PK에 해당하는 필드를 지정해야함
	@GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 자동으로 생성할 때 사용 (Auto increment 방식)
	int no;

	@Column(length = 200, nullable = true) // 컬럼의 제약사항 지정
	String text; // 컬럼의 타입과 이름 지정
}

// 클래스 생성 후 프로젝트를 실행
// 디비버 접속 후 테이블이 생성되었는지 확인
