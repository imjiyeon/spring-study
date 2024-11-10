package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;


@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	public void 게시물추가() {
		BoardDTO dto = BoardDTO.builder()
								.title("안녕")
								.content("안녕하세요")
								.writer("아무나")
								.build();		
		service.register(dto);
	}
	
	@Test
	public void 외래키추가후_게시물추가() {
		// writer는 DB에 있는 계쩡을 사용해야함
		BoardDTO dto = BoardDTO.builder()
								.title("안녕")
								.content("안녕하세요")
								.writer("user")
								.build();		
		service.register(dto);
	}
	
	@Test
	public void 게시물목록조회() {
		List<BoardDTO> list = service.getList();
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 게시물단건조회() {
		BoardDTO dto = service.read(1);
		System.out.println(dto);
	}
	
	@Test
	public void 게시물수정() {
		// 수정은 등록과 달리 no가 포함되어야함
		BoardDTO dto = service.read(1);
		dto.setContent("내용이수정되었습니다~");
		service.modify(dto);
	}
	
	@Test
	public void 게시물삭제() {
		service.remove(1);
	}

}
