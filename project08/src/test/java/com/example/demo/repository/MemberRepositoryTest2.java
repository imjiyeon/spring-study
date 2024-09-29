package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;


@SpringBootTest
public class MemberRepositoryTest2 {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void 게시물을작성하지않은_회원삭제() {
		// 게시물을 작성하지 않은 회원은 바로 삭제할 수 있음
		memberRepository.deleteById("user2");
	}
	
	@Test
	public void 게시물을작성한_회원삭제() {
		// 게시물이 작성한 회원은 바로 삭제할 수 없음
		// 회원을 삭제하려면 회원이 작성한 게시물을 먼저 삭제해야함
		// 이를 위해 boardRepository에 삭제 메소드 추가
		
		Member member = Member.builder().id("user1").build();
		
		// 해당 회원이 작성한 게시물들을 먼저 삭제
		boardRepository.deleteWriter(member);
		// 그 후에 회원을 삭제
		memberRepository.deleteById("user1");
		
		// SQL에 작성자의 아이디가 조건으로 추가됨
	}
	
	@Test
	public void 회원일괄등록() {
		List<Member> list = new ArrayList<>();
		for(int i=1; i<=30;i++) {
			list.add(new Member("user"+i,"1234","둘리"));
		}
		memberRepository.saveAll(list);
	}
	
}