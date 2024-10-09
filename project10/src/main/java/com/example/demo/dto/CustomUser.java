package com.example.demo.dto;


import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

    // MemberDTO -> USER
    // 사용자 아이디, 패스워드, 권한을 꺼내서 User 객체 생성
  public CustomUser(MemberDTO dto) {
	  // 사용자는 여러개의 권한을 가질 수 있으므로 리스트로 처리
	  super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));    
  }
  
}

