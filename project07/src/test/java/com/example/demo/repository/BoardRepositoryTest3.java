package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

// querydsl 테스트

@SpringBootTest
public class BoardRepositoryTest3 {

	@Autowired
	BoardRepository repository;

	@Test
	void 테스트용게시물등록() {
		
		Board board = Board.builder().title("1번글").content("안녕하세요").writer("둘리").build();
		Board board2 = Board.builder().title("2번글").content("안녕").writer("둘리").build();
		Board board3 = Board.builder().title("3번글").content("하이").writer("또치").build();

		repository.save(board);
		repository.save(board2);
		repository.save(board3);
	}

	@Test
	void 단일항목검색테스트() {

		// 페이징 조건으로 첫번째 페이지, 데이터 10개 설정
		Pageable pageable = PageRequest.of(0, 10);

		// Q 도메인 클래스 생성
		QBoard qBoard = QBoard.board;

		// builder 객체 생성
		BooleanBuilder builder = new BooleanBuilder();

		// 검색 조건 작성하기
		// 작성자에 '둘리'가 포함된 게시물 검색
		BooleanExpression expression = qBoard.writer.contains("둘리");

		// builder에 조건을 추가
		builder.and(expression);

		// 조건을 사용하여 검색
		Page<Board> result = repository.findAll(builder, pageable);

		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}

	}
	// SQL에 LIKE 연산자를 사용하며,
	// ESCAPE '!'는 % 부분을 문자 그대로 처리하기 위한 이스케이프 문자로이다
	// LIKE 검색 시 키워드에 %가 추가된다

	@Test
	void 다중항목검색테스트1() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 검색 조건 작성하기
		// 내용에 '안녕'이 포함되고 작성자에 '둘리'가 포함되는 조건 생성
		BooleanExpression expression = qBoard.content.contains("안녕");
		builder.and(expression);
		BooleanExpression expression2 = qBoard.writer.contains("둘리");
		
		// 조건을 AND로 연결
		builder.and(expression2);
		Page<Board> result = repository.findAll(builder, pageable);
		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}
	}
	// SQL에 AND 키워드가 사용된다
	
	@Test
	void 다중항목검색테스트2() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 검색 조건 작성하기
		// 내용에 '안녕하세요'가 포함되거나 작성자에 '또치'가 포한되는 조건 생성
		BooleanExpression expression = qBoard.content.contains("안녕하세요");
		
		BooleanExpression expression2 = qBoard.writer.contains("또치");
		
		// 조건을 OR로 연결
		BooleanExpression allExpression = expression.or(expression2);
		
		builder.and(allExpression);
		
		Page<Board> result = repository.findAll(builder, pageable);
		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}
	}
	// SQL에 OR 키워드가 추가된다

}
