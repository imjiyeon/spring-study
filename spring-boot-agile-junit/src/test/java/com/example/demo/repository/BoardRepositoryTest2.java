package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import jakarta.transaction.Transactional;

// AssertJ 라이브러리의 assertThat를 사용하여 테스트
// 테스트가 완료되면 Transactional을 통해 자동으로 롤백하여, 실제 데이터베이스에 데이터를 남기지 않고
// 주요 기능(등록, 수정, 삭제)만 검증
@Transactional 
@SpringBootTest
public class BoardRepositoryTest2 {

	@Autowired
	BoardRepository repository;

	// 시나리오가 의도한 대로 동작하는지 확인한다
	// 예상 결과와 일치하면 테스트 성공, 그렇지 않으면 실패
	
	@Test
	void 게시물등록() {
		
		// Board 엔티티를 저장하고 게시물 번호로 조회
		// Given: 게시물 생성
		Board board = Board.builder().title("1번글").content("내용입니다").writer("또치").build();

		// When: 저장
		Board savedBoard = repository.save(board);

		// Then: 저장된 게시물 조회
		Optional<Board> foundBoard = repository.findById(savedBoard.getNo());
		assertThat(foundBoard).isPresent();
		assertThat(foundBoard.get().getTitle()).isEqualTo("1번글");
	}

	@Test
	public void 게시물수정() {
		// 미리 데이터베이스에 1번 게시물 추가할 것

		// Given: 1번 게시물을 조회하고 내용 수정
		Optional<Board> optional = repository.findById(1);
		assertThat(optional).isPresent();

		if (optional.isPresent()) {
			Board board = optional.get();
			board.setContent("내용이수정되었습니다~");

			// When: 수정된 게시물을 저장
			repository.save(board);

			// Then: 다시 1번 게시물을 조회하여 수정된 내용을 확인
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
