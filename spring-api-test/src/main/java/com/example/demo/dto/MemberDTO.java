package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;

	String password;

	String name;

	LocalDateTime regDate;

	LocalDateTime modDate;
	
	String role;

}
