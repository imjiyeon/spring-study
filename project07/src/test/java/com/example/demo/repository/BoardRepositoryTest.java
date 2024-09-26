package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	void 게시물등록() {

		//시간은 입력할 필요없음
		Board board = Board.builder()
						.title("1번글").content("내용입니다").writer("둘리")
						.build();
		repository.save(board);
	}

	@Test
	void 게시물목록조회() {
		List<Board> list = repository.findAll();
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	@Test
	void 게시물단건조회() {
		Optional<Board> result = repository.findById(1);
		if(result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}

	@Test
	void 게시물수정() {
		Optional<Board> result = repository.findById(1);
		Board board = result.get();
		board.setContent("내용이수정되었습니다~");
		repository.save(board);
	}
	
	@Test
	void 게시물삭제() {
		repository.deleteById(1);
	}

	@Test
	void querydsl테스트() {



	}


}


