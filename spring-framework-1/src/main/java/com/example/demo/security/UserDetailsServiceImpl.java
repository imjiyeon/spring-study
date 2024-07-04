package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.member.domain.CustomUser;
import com.example.demo.member.domain.MemberDto;
import com.example.demo.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

/*
 * 사용자 커스텀 로그인 인증 서비스
 * loadUserByUsername를 오버라이드 하여 사용자 정보를 조회하고 인증객체를 생성한다
 * 유저 인증서비스는 provider에 등록한다
 * */

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{ // userName는 아이디를 의미한다
		log.info("Load User By UserName : " + userName);
		MemberDto dto = repository.select(userName);
		if(dto == null) {
			throw new UsernameNotFoundException(""); //사용자가 존재하지 않을때 에러를 발생시킴
		} else {
			return new CustomUser(dto); //사용자도메인을 시큐리티 인증객체로 변환
		}
			
	}

}
