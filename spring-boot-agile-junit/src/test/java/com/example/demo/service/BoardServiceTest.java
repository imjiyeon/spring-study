package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;

// TDD 방식으로 테스트

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	void 게시물등록() {
		
		// Given: 게시물 생성
		BoardDTO dto = BoardDTO.builder().title("1번글").content("내용입니다").writer("또치").build();
		
		// When: 저장
		int no = service.register(dto);
		
		// Then: 새로 생성된 게시물의 번호가 1번이 맞는지 확인
		assertThat(no).isEqualTo(1);
		
		// 번호를 바꿔서 다시 테스트
		// 예상결과가 맞지 않으면 테스트 실패
//		assertThat(no).isEqualTo(3);
	}
	
	@Test
	public void 게시물수정() {

		// Given: 1번 게시물을 조회하고 내용 수정
		BoardDTO dto = service.read(1);
		dto.setContent("내용수정~");

		// When: 수정된 게시물을 저장
		service.modify(dto);

		// Then: 다시 1번 게시물을 조회하여 내용이 수정되었는지 확인
		BoardDTO modifyDto = service.read(1);
		assertThat(modifyDto.getContent()).isEqualTo("내용수정~");
	}

	@Test
	public void 게시물삭제() {
		// Given: 게시물번호
		int boardNo = 1;

		// When: 게시물 삭제
		service.remove(boardNo);

		// Then: 게시물이 삭제되었는지 확인
		BoardDTO dto = service.read(boardNo);
		assertThat(dto).isNull();
	}

}
