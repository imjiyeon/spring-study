package com.example.demo.dto;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

public class CustomUser extends User {

    // MemberDTO -> USER
    // 사용자 아이디, 패스워드, 권한을 꺼내서 User 객체 생성
  public CustomUser(MemberDTO dto) {
	super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));    
  }
  
}

