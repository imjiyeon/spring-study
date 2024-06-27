package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;

//	// 페이지 테스를 위해 게시물 30건 추가
//	@Test
//	public void 게시물30개추가() {
//		for(int i=1; i<=30; i++) {
//			Board board = Board.builder()
//							.title(i+"번글").content("안녕하세요").writer("둘리")
//							.build();
//			repository.save(board);
//		}
//	}

	@Test
	public void 페이지테스트() {
		//1페이지 10개
		Pageable pageable = PageRequest.of(0,10); // of메소드로 객체 생성
		Page<Board> result = repository.findAll(pageable);
		System.out.println(result); // 게시물 리스트 + 페이지정보

		List<Board> list = result.getContent();
		System.out.println(list); //게시물 리스트
	}

	@Test
	public void 정렬조건추가하기() {
		//no 필드값을 기준으로 역정렬
		Sort sort = Sort.by("no").descending();

		Pageable pageable = PageRequest.of(0,10, sort); //정렬정보 추가
		Page<Board> result = repository.findAll(pageable);

		List<Board> list = result.getContent();
		System.out.println(list);
	}

	@Test
	void 게시물등록1() {

		//회원 엔티티를 먼저 생성
		Member member = Member.builder().id("user1").build();

		//게시물 엔티티에 작성자 입력
		Board board = Board.builder()
				.title("안녕하세요").content("안녕하세요").writer(member)
				.build();

		repository.save(board);
	}

	@Test
	void 게시물등록2() {

		//회원 엔티티 생성
		Member member = Member.builder().id("둘리").build();

		//회원테이블에 없는 아이디를 사용하면 에러남
		Board board = Board.builder()
				.title("안녕하세요").content("안녕하세요").writer(member)
				.build();

		repository.save(board);
	}

	@Test
	public void 게시물조회() {
		Optional<Board> optional = repository.findById(1);  //쿼리가 내부적으로 join 처리됨
		Board board = optional.get();
		System.out.println(board); //회원정보가 함께 출력됨
	}

}
