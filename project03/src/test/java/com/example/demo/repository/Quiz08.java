package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class Quiz08 {

	@Autowired
	MemberRepository repository;

	@Test
	public void 아이디가user로시작하는_회원검색() {
        List<Member> list = repository.get1("user");
        for(Member member : list) {
            System.out.println(member);
        }
	}

	@Test
	public void 관리자등급인_회원검색() {
		List<Member> list = repository.get2("관리자");
		for(Member member : list) {
			System.out.println(member);
		}
	}

}
