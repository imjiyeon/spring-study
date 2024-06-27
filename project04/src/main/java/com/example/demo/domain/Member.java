package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	int no;
	
	String userId; // 회원아이디

	String password; // 패스워드

}
