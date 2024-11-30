package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;


// Spring Security에서 제공하는 UserDetailsService 인터페이스를 상속받아
// 사용자 인증을 처리하기 위해 만든 클래스

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberService service;

	// 사용자 아이디를 기반으로 데이터베이스에서 사용자 정보를 조회하고 인증 객체를 생성
	@Override
	public UserDetails loadUserByUsername(String userName) { // userName는 아이디를 의미

		System.out.println("login id : " + userName);

		// 아이디로 실제 회원정보 가져오기
		MemberDTO dto = service.read(userName);

		if(dto == null) {
			throw new UsernameNotFoundException(""); //사용자 정보가 없다면 에러를 발생시킴
		} else {
			return new CustomUser(dto); //dto를 인증객체로 변환하여 반환
		}

	}

}


