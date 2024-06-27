package com.example.spring11.dto;


import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Map;

// OAuth2User 인증 클래스 상속받기
@Getter
public class CustomUser extends User implements OAuth2User {

    // 소셜로그인시 패스워드를 확인하기 위해서 필드 추가
    String password;

    Map<String, Object> attr;

    public CustomUser(MemberDTO dto) {
    	super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));

        this.password = dto.getPassword();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }

    @Override
    public String getName() {
        return null;
    }
}

