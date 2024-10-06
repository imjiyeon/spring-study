package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity { // 등록시간과 수정시간 필드 상속받기

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no; // 글번호

	@Column(length = 100, nullable = false)
	String title; // 제목

	@Column(length = 1500, nullable = false)
	String content; // 내용

//    @Column(length = 50, nullable = false)
//    String writer; //작성자

	// 작성자 필드를 외래키로 설정하여 Member 테이블의 PK를 참조
	@ManyToOne // 관계차수는 1:N로 설정
	Member writer; // 작성자

}

// 기존 테이블을 삭제하고, 새로 생성
// 테이블 생성 시, SQL 쿼리를 보면 FK가 설정된 것을 확인할 수 있음
// 실제 테이블에서 외래키와 엔티티 관계도 확인



