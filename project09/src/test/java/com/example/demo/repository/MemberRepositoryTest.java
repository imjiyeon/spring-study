package com.example.demo.repository;

import com.example.demo.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void 회원등록() {
		Member member = new Member("user1","1234","둘리");
		memberRepository.save(member);
	}

	@Test
	public void 회원목록조회() {
		List<Member> list = memberRepository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}
	}

	@Test
	public void 회원단건조회() {
		Optional<Member> result = memberRepository.findById("user1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}

	@Test
	public void 회원수정() {
		Optional<Member> result = memberRepository.findById("user1");
		Member member = result.get();
		member.setName("또치");
		memberRepository.save(member);
	}
	
	@Test
	public void 회원삭제() {
		memberRepository.deleteById("user1");
	}

	@Test
	public void 연관관계설정후_회원삭제() {
		//게시물이 없는 회원은 삭제해도 문제가 없지만, 게시물이 있는 경우에는 삭제할 수 없음
		//회원을 삭제하려면 회원을 참조하는 게시물을 먼저 삭제해야함
		Member member = Member.builder().id("user1").build();
		boardRepository.deleteWriter(member);
		memberRepository.deleteById("user1");
	}

}
