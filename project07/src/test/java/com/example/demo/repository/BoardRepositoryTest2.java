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
public class BoardRepositoryTest2 {

	@Autowired
	BoardRepository repository;

	@Test
	void 게시물등록() {
		Board board = Board.builder().title("1번글").content("안녕하세요").writer("둘리").build();

		Board board2 = Board.builder().title("2번글").content("안녕").writer("둘리").build();

		Board board3 = Board.builder().title("3번글").content("하이").writer("또치").build();

		repository.save(board);
		repository.save(board2);
		repository.save(board3);
	}

	@Test
	void 단일항목검색테스트() {

		Pageable pageable = PageRequest.of(0, 10);

		// Q도메인 클래스 가져오기
		QBoard qBoard = QBoard.board;

		// builder 객체 생성
		BooleanBuilder builder = new BooleanBuilder();

		// 원하는 쿼리 조건 만들기
		// 엔티티의 필드를 꺼내고 조건 넣기
		BooleanExpression expression = qBoard.writer.contains("둘리");

		// 조건 결합
		builder.and(expression);

		Page<Board> result = repository.findAll(builder, pageable);

		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}

	}
	// SQL에 LIKE 연산자가 추가된다 
	// ESCAPE '!'는 이스케이프 문자로 % 부분을 특수기호가 아닌 문자 그대로 사용하겠다는 의미
	// LIKE 검색을 할때는 키워드에 %가 붙어야 한다

	@Test
	void 다중항목검색테스트1() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 원하는 쿼리 조건 만들기
		// 내용과 작성자로 조건 만들기
		BooleanExpression expression = qBoard.content.contains("안녕");
		builder.and(expression);
		BooleanExpression expression2 = qBoard.writer.contains("둘리");
		
		// 조건 결합하기
		builder.and(expression2);
		Page<Board> result = repository.findAll(builder, pageable);
		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}
	}
	// SQL에 AND 키워드가 추가된다
	
	@Test
	void 다중항목검색테스트2() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 원하는 쿼리 조건 만들기
		// "내용이 안녕하세요 또는 작성자가 또치"인 조건 만들기
		BooleanExpression expression = qBoard.content.contains("안녕하세요");
		
		BooleanExpression expression2 = qBoard.writer.contains("또치");
		
		// 조건 결합하기
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
