package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemberRepositoryTest2 {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardRepository boardRepository;

	// 게시물을 작성한 회원 삭제
	@Test
	public void 첫번째회원삭제() {
		
		// 게시물이 작성한 회원은 바로 삭제할 수 없음
		// 회원을 삭제하려면 먼저 회원이 작성한 게시물을 삭제해야함

		Member member = Member.builder().id("user1").build();

		// 게시물 삭제 후, 회원을 삭제
		memberRepository.deleteById("user1");

		// SQL에 작성자의 아이디가 조건으로 추가됨
	}

	// 게시물을 작성하지 않은 회원 삭제
	@Test
	public void 두번째회원삭제() {
		// 게시물을 작성하지 않은 회원은 바로 삭제 가능
		memberRepository.deleteById("user2");
	}

	// 회원과 게시물 데이터를 다시 추가하고
	// 게시물 리파지토리의 삭제 메소드 테스트
	@Test
	public void 회원삭제() {
		
		// boardRepository에 작성자를 기준으로 게시물을 일괄 삭제하는 메소드 추가

		Member member = Member.builder().id("user1").build();

		// 해당 회원이 작성한 모든 게시물을 일괄 삭제
		boardRepository.deleteWriter(member);

		// 게시물 삭제 후, 회원 삭제
		memberRepository.deleteById("user1");
	}

	@Test
	public void 회원일괄등록() {
		List<Member> list = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			list.add(new Member("user" + i, "1234", "둘리"));
		}
		memberRepository.saveAll(list);
	}

}
