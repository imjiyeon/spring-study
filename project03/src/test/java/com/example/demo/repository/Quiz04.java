package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

/*
 * Member엔티티클래스와 MemberReposiotry인터페이스를 이용하여 다음과 같이 테이블을 만드세요.
 * 다음과 같이 테이블에 데이터를 추가하세요.
 * 그리고 테이블에 데이터를 조회, 수정, 삭제 하세요.
 * */
@SpringBootTest
public class Quiz04 {
	
	@Autowired
	MemberRepository repository;

	@Test
	public void 데이터등록() {
		Member member1 = Member.builder().userId("user1").password("1234").grade("사용자").build();
		repository.save(member1);
		
		Member member2 = Member.builder().userId("user2").password("1234").grade("사용자").build();
		repository.save(member2);
		
		Member member3 = Member.builder().userId("admin").password("1234").grade("관리자").build();
		repository.save(member3);
		
		Member member4 = Member.builder().userId("yoyt22").password("1234").grade("관리자").build();
		repository.save(member4);
	}

	@Test
	public void 데이터단건조회() {
		Optional<Member> result = repository.findById("user1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Member> list = repository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}
	}

	@Test
	public void 데이터수정() {
		Optional<Member> result = repository.findById("user1");
		Member member = result.get();
		member.setPassword("5678");
		repository.save(member);
	}

	@Test
	public void 데이터삭제() {
		repository.deleteById("user1");
	}
}
