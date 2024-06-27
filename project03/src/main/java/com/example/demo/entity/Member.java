package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class) // 엔티티에 변화를 감지하는 리스너 지정
@Table(name = "tbl_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	@Id
	String userId; // 회원아이디

	@Column(nullable = true)
	String password; // 패스워드

	@Column(nullable = true)
	String grade; // 등급

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	LocalDate registerDate; // 가입일

}
