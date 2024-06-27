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
	public void 게시물등록() {

		BoardDTO dto = BoardDTO.builder()
				.title("안녕하세요").content("안녕하세요").writer("user1")
				.build();

		int no = service.register(dto);

		System.out.println("새로운 게시물 번호: " + no);
	}

	@Test
	public void 게시물목록조회() {
		Page<BoardDTO> page = service.getList(1);
		List<BoardDTO> list = page.getContent();
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 게시물단건조회() {
		BoardDTO dto = service.read(1);
		System.out.println(dto);
	}

}
