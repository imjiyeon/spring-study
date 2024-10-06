package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	public void 게시물30개추가() {
		for(int i=1; i<=30; i++) {
			service.register(new BoardDTO(0,i+"번글","안녕하세요","user1",null,null));
		}
	}
	
	@Test
	public void 게시물목록조회() {
		Page<BoardDTO> page = service.getList(1);
		List<BoardDTO> list = page.getContent();
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
	}

}
