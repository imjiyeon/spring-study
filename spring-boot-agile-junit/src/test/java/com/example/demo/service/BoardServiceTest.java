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
	
	// AssertJ 라이브러리의 assertThat를 사용하여 테스트를 진행한다.
	// 테스트 시나리오가 의도한 대로 동작하는지 확인한다
	// 예상 결과와 일치하면 단위 테스트가 성공하고, 그렇지 않으면 실패한다
	
	// 기존방식과 차이점: 기존방식을 결과를 단수히 결과를 출력하고 끝나지만, 
	// assertThat은 예상결과와 다를 경우 단위테스트가 실패처리되어 결과를 정확하게 확인할 수 있다

	// 준비: board 테이블을 삭제하고 다시생성해주세요!

	@Test
	void 게시물등록() {
		
		// Given: 게시물 생성
		BoardDTO dto = BoardDTO.builder().title("1번글").content("내용입니다").writer("또치").build();
		
		// When: 저장
		int no = service.register(dto);
		
		// Then: 새로 생성된 게시물의 번호가 1번이 맞는지 확인
		assertThat(no).isEqualTo(1);
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
