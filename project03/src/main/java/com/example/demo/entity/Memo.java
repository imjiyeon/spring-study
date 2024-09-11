package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 데이터베이스의 테이블 구조를 정의하는 클래스
 * */

@Entity // 엔티티 클래스임을 명시
@Table(name = "tbl_memo") // 테이블 이름
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 키값을 자동으로 생성할 때 사용(Auto increment)
	int no;

	@Column(length = 200, nullable = true) // 컬럼의 제약사항 지정
	String text; // 컬럼의 타입과 이름 지정
}

/* 확인 */
// 클래스 생성 후 프로젝트를 실행한다
// 디비버에 접속하여 테이블이 생성되었는지 확인한다
// PK 필드를 삭제한 후 발생하는 오류를 확인한다: PK가 하나도 없으면 테이블 연결 안됨

/* 설정을 추가한 후 확인 */
//생성된 sql문을 확인한다
//테이블 이름은 @Table에서 설정한 이름으로 생성된다
//@Table 삭제한 후 동작을 확인한다: 생략하면 클래스 이름과 동일한 이름으로 테이블이 생성됨

