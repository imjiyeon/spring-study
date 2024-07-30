package com.example.demo.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;

	@Test
	void 게시물등록() {
		// Board 엔티티를 저장하고 게시물 번호로 조회하여 결과를 검증한다
		// 실제로 데이터베이스에 반영되지 않고, 테스트 시나리오가 올바르게 동작하는지만 확인한다
		// 예상결과를 맞으면 테스트가 성공하고, 틀리면 테스트가 실패한다

		// Given
		Board board = Board.builder().title("1번글").content("내용입니다").writer("둘리").build();

		// When
		Board savedBoard = repository.save(board);

		// Then
		Optional<Board> foundBoard = repository.findById(savedBoard.getNo());
		assertThat(foundBoard).isPresent();
		assertThat(foundBoard.get().getTitle()).isEqualTo("1번글");
	}

	@Test
	public void 게시물수정() {
		// 미리 데이터베이스에 1번 게시물 추가할 것

		// Given
		Optional<Board> optional = repository.findById(1);
		assertThat(optional).isPresent();

		if (optional.isPresent()) {
			Board board = optional.get();
			board.setContent("내용이수정되었습니다~");

			// When
			repository.save(board);

			// Then
			Optional<Board> optional2 = repository.findById(1);
			if (optional2.isPresent()) {
				assertThat(optional2.get().getTitle()).isEqualTo("1번글");
				assertThat(optional2.get().getContent()).isEqualTo("내용이수정되었습니다~");
			}
		}
	}

	@Test
	public void 게시물삭제() {
		// Given
		int boardNo = 1;

		// When
		repository.deleteById(boardNo);

		// Then
		Optional<Board> optional = repository.findById(boardNo);
		assertThat(optional).isNotPresent();
	}

}
