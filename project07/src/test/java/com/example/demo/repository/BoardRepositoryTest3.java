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

	// 기존 게시물 테이블을 삭제한 후, 새로 생성하세요
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

		// 첫번째 페이지에서 데이터 10개를 가져오는 페이지 조건 설정
		Pageable pageable = PageRequest.of(0, 10);

		// Q도메인 클래스의 인스턴스 생성
		QBoard qBoard = QBoard.board;

		// builder 객체 생성 (조건을 담을 수 있는 객체)
		BooleanBuilder builder = new BooleanBuilder();

		// 검색 조건 작성
		// 작성자에 '둘리'가 포함된 게시물 검색
		BooleanExpression expression = qBoard.writer.contains("둘리");

		// 만들어진 조건을 and로 연결
		builder.and(expression);

		// 검색 실행
		Page<Board> result = repository.findAll(builder, pageable);

		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}

	}
	// contains함수를 사용하여 SQL에 LIKE 연산자가 추가되었고, LIKE 키워드 앞뒤에 %가 추가되었다
	// ESCAPE '!'는 % 부분을 문자 그대로 처리하기 위한 이스케이프 문자이다

	@Test
	void 다중항목검색테스트1() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 조건: 내용 + 작성자
		
		// 첫번째 조건: 내용에 '안녕'이 포함되는 게시물
		BooleanExpression expression = qBoard.content.contains("안녕");
		builder.and(expression); // 조건을 AND로 연결
		
		// 두번째 조건: 작성자에 '둘리'가 포함되는 게시물
		BooleanExpression expression2 = qBoard.writer.contains("둘리");
		builder.and(expression2); // 조건을 AND로 연결
		
		Page<Board> result = repository.findAll(builder, pageable);
		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}
	}
	// and 함수를 사용하여 SQL에 AND 키워드가 추가되었다
	
	@Test
	void 다중항목검색테스트2() {

		Pageable pageable = PageRequest.of(0, 10);
		QBoard qBoard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
		// 조건: 게시물 번호 + 내용
		
		// 첫번째 조건: 게시물 번호가 2보다 큰 게시물
		BooleanExpression expression = qBoard.no.gt(2);
		
		// 두번째 조건: 내용에 '안녕하세요'이 포함되는 게시물
		BooleanExpression expression2 = qBoard.content.contains("안녕하세요");

		// 조건을 OR로 연결
		BooleanExpression allExpression = expression.or(expression2);
		
		builder.and(allExpression);
		
		Page<Board> result = repository.findAll(builder, pageable);
		List<Board> list = result.getContent();

		for (Board b : list) {
			System.out.println(b);
		}
	}
	// gt 함수를 사용하여 > 비교연산자가 추가되었고
	// or 함수를 사용하여 SQL에 OR 키워드가 추가되었다

}
