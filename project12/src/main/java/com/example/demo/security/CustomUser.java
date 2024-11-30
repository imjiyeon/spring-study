package com.example.demo.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.dto.MemberDTO;

// Spring Security에서 제공하는 User 클래스 상속받아
// 사용자 인증 정보를 저장하기 위해 만든 클래스
public class CustomUser extends User {

  public CustomUser(MemberDTO dto) {
	super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));    
  }
  
}