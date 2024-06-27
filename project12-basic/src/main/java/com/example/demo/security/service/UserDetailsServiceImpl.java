//package com.example.demo.security.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.security.dto.CustomUser;
//import com.example.demo.service.MemberService;
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private MemberService service;
//
//	@Override
//	public UserDetails loadUserByUsername(String userName) {
//
//		System.out.println("login id : " + userName);
//
//		MemberDTO dto = service.read(userName);
//
//		if(dto == null) {
//			throw new UsernameNotFoundException("");
//		} else {
//			return new CustomUser(dto);
//		}
//
//	}
//
//}
