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

		// 회원 테이블에 존재하지 않는 회원을 작성자로 사용하면 에러남
		Board board = Board.builder()
									.title("안녕하세요")
									.content("안녕하세요")
									.writer(member)
									.build();

		repository.save(board); //에러남
		//com.example.demo.entity.Board.writer -> com.example.demo.entity.Member
	}

	@Test
	void 게시물등록() {
		
		// 테이블에 존재하는 회원 정보로 엔티티 생성 (PK만 필요)
		Member member = Member.builder().id("user1").build();

		// 작성자 필드에 회원 정보 입력
		Board board = Board.builder()
									.title("안녕하세요")
									.content("안녕하세요")
									.writer(member)
									.build();
		repository.save(board);
		
		// 한명의 회원이 여러개의 게시물을 작성할 수 있음
		Board board2 = Board.builder()
									.title("반갑습니다")
									.content("반갑습니다")
									.writer(member)
									.build();
		repository.save(board2);
	}

	@Test
	public void 게시물조회() {
		
		Optional<Board> optional = repository.findById(1);
		
		Board board = optional.get();
		
		System.out.println(board); //회원정보도 함께 출력됨
		
		// SQL에서 board 테이블과 member 테이블이 join 처리됨
	}

}
