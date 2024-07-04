package com.example.demo.member.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	
	private String id; //아이디
	private String password; //패스워드
	private String name; //이름
	/* 사용자등급 추가 */
	private String role; //사용자등급 (사용자:ROLE_USER, 관리자:ROLE_ADMIN)
	private Date regDate; //등록일
	private Date updateDate; //수정일	
}
