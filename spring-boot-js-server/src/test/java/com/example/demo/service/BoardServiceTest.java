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
		BoardDTO board = BoardDTO.builder()
									.title("1번글")
									.content("안녕하세요")
									.writer("user1")
									.build();
		service.register(board);
	}

	@Test
	public void 게시물30개추가() {
		for(int i=1; i<=30; i++) {
			service.register(new BoardDTO(0,i+"번글","안녕하세요","user1",null,null));
		}
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
		BoardDTO dto = service.read(1);
		dto.setContent("내용이수정되었습니다~");
		service.modify(dto);
	}
	
	@Test
	public void 게시물삭제() {
		service.remove(1);
	}

}
