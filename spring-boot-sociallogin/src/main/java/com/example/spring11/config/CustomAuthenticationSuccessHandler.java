package com.example.spring11.config;

import com.example.spring11.dto.CustomUser;
import com.example.spring11.dto.MemberDTO;
import com.example.spring11.service.MemberService;
import com.example.spring11.service.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.security.Principal;

/*
* 소셜 로그인 후 처리
* */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        // 로그인한 사용자 정보 꺼내기
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        String username = authentication.getName();

        String pw = customUser.getPassword();

        // 현재 비밀번호가 1111인지 확인
        boolean matchResult = passwordEncoder.matches("1111", pw);
        
        // 맞다면 회원정보 수정페이지로 이동, 아니면 메인페이지로 이동
        if(matchResult){
            response.sendRedirect("/member/modify?id="+username);
        } else {
            response.sendRedirect("/");
        }

    }
}
