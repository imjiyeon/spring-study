package com.example.demo.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

// 이 어노테이션 필요 없음
//@SpringBootTest
public class MemberRepositoryTest {

	// 스프링을 사용하지 않았기 때문에 직접 리파지토리 객체 생성해야함
	MemberRepository repository = new MemberRepository();
	
	@Test
	public void 테스트() {
		
		// 1.회원 등록
		Member member1 = new Member(0,"user1","1234");
		repository.save(member1);
		
		Member member2 = new Member(0,"user2","1234");
		repository.save(member2);
		
		// 2.회원 목록 조회
		List<Member> findList = repository.findAll();
		System.out.println("회원 목록: ");
		for(Member member : findList) {
			System.out.println(member);
		}
		
		// 3.1번째 회원 조회
		Member getMember = repository.findById(1);
		System.out.println("1번째 회원: " + getMember);
		
		// 4.모든 회원 삭제
		repository.clearStore();
		List<Member> clearList = repository.findAll();
		System.out.println("삭제 후 회원 목록: ");
		for(Member member : clearList) {
			System.out.println(member);
		}
		
	}

}
