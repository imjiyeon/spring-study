package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	public void 게시물추가() {
		Board board = Board.builder()
									.title("1번글")
									.content("안녕하세요")
									.writer("user1")
									.build();
		repository.save(board);
	}

}
