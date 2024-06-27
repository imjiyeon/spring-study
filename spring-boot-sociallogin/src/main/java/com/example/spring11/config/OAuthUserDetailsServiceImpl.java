package com.example.spring11.config;


import com.example.spring11.dto.CustomUser;
import com.example.spring11.dto.MemberDTO;
import com.example.spring11.entity.Member;
import com.example.spring11.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * 구글 소셜 로그인 인증 커스텀 서비스
 * */

// 스프링 컨테이너에 빈으로 등록
// DefaultOAuth2UserService 상속 받기
@Service
public class OAuthUserDetailsServiceImpl extends DefaultOAuth2UserService {

    @Autowired
    MemberService memberService;
    
    // 로그인 함수 재정의
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("--------------------------------");
        System.out.println("userRequest: " + userRequest); // 전달받은 로그인 정보 출력

        String clientName = userRequest.getClientRegistration().getClientName();
        System.out.println(clientName); //Google

        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        // 구글 로그인을 한 경우, 사용자 정보에서 이메일 꺼내기
        String email = null;

        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }

        System.out.println("EMAIL:" + email);

        // 자동으로 회원가입 하기
        MemberDTO memberDTO = memberService.saveSocialMember(email);

        // CustomUser로 변환하여 반환 (일반 로그인과 결과를 맞춤)
        return new CustomUser(memberDTO);
    }

}
