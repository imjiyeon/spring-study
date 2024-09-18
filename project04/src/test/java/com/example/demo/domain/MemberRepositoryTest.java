package com.example.demo.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

	// 직접 리파지토리 객체 생성해야함
	MemberRepository repository = new MemberRepository();
	
	@Test
	public void 테스트() {
		
		//회원 등록
		Member member1 = new Member(0,"user1","1234");
		repository.save(member1);
		
		Member member2 = new Member(0,"user2","1234");
		repository.save(member2);
		
		//회원 목록 조회
		List<Member> findList = repository.findAll();
		System.out.println("회원 목록: ");
		for(Member member : findList) {
			System.out.println(member);
		}
		
		//1번째 회원 조회
		Member getMember = repository.findById(1);
		System.out.println("1번째 회원: " + getMember);
		
		//회원 전체 삭제
		repository.clearStore();
		List<Member> clearList = repository.findAll();
		System.out.println("삭제 후 회원 목록: ");
		for(Member member : clearList) {
			System.out.println(member);
		}
		
	}

}
