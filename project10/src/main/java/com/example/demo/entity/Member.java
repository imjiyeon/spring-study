package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity { // BaseEntity 상속받기

	@Id
	@Column(length = 50)
	String id; // 아이디

	@Column(length = 200, nullable = false)
	String password; // 패스워드

	@Column(length = 100, nullable = false)
	String name; // 이름

	@Column(length = 100, nullable = false)
	String role; //사용자 등급 추가

}
