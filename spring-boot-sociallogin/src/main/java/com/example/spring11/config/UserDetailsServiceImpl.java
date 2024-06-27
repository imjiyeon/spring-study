package com.example.spring11.config;


import com.example.spring11.dto.CustomUser;
import com.example.spring11.dto.MemberDTO;
import com.example.spring11.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * 로그인 인증 커스텀 서비스
 * */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberService service;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		System.out.println("login id : " + userName);
		MemberDTO dto = service.read(userName);
		if(dto == null) {
			throw new UsernameNotFoundException("");
		} else {
			return new CustomUser(dto);
		}
			
	}

}
