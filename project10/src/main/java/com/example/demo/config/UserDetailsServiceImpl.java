package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUser;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

/*
 * 사용자 커스텀 로그인 인증 서비스
 * loadUserByUsername를 오버라이드 하여 사용자 정보를 조회하고 인증객체를 생성한다
 * 유저 인증서비스는 provider에 등록한다
 * */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberService service;

	// 사용자 아이디를 기반으로 인증을 처리하는 메소드
	// 스프링 시큐리티는 아이디 대신 userName 이라는 용어를 사용한다
	@Override
	public UserDetails loadUserByUsername(String userName) {

		System.out.println("login id : " + userName);

		// 아이디로 실제 회원 정보를 조회
		MemberDTO dto = service.read(userName);

		if(dto == null) {
			//사용자 정보가 없다면 에러를 발생시킴
			throw new UsernameNotFoundException(""); 
		} else {
			//조회한 사용자 정보를 인증객체로 변환하여 반환
			return new CustomUser(dto); 
		}

	}

}
