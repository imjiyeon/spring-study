package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_book")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	// 변수명은 카멜케이스 방식으로 작성할 것
	// jpa에 컬럼을 만들때 자동으로 스네이크 방식으로 바꿈
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bookNo;

	@Column(length = 30, nullable = false)
	String title;

	//  nullable은 기본값이 true여서 생략 가능
	@Column(length = 100, nullable = false)
	String publisher;

	@Column(nullable = false)
	int price;
}
