package com.example.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	void 게시물등록_잘못된방법() {

		// 작성자 필드에 사용할 회원 엔티티 생성
		Member member = Member.builder().id("둘리").build();

		// 회원테이블에 없는 아이디를 사용하면 에러남
		Board board = Board.builder()
									.title("안녕하세요")
									.content("안녕하세요")
									.writer(member)
									.build();

		repository.save(board);
		//error
		//com.example.demo.entity.Board.writer -> com.example.demo.entity.Member
	}

	@Test
	void 게시물등록() {
		// 먼저 테이블에 존재하는 회원 정보를 생성함 후, 
		// 게시물 엔티티의 작성자 필드에 입력해야함
		Member member = Member.builder().id("user1").build();

		Board board = Board.builder()
									.title("안녕하세요")
									.content("안녕하세요")
									.writer(member) // 작성자 필드에 회원 정보 입력
									.build();

		repository.save(board);
	}

	@Test
	public void 게시물조회() {
		Optional<Board> optional = repository.findById(1);
		Board board = optional.get();
		System.out.println(board); //회원정보가 함께 출력됨
		
		// SQL에 member 테이블과의 join이 추가됨
	}

}
