package com.example.demo.etc;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PasswordTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 암호화테스트() {

        String password = "1234";

        // 비밀번호 암호화
        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw" + enPw);

        // 비밀번호가 1234가 맞는지 확인 (문자열과 해시코드 비교)
        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("확인결과:" + matchResult);
    }

}
