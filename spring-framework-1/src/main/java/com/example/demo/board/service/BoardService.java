package com.example.demo.board.service;

import java.util.List;

import com.example.demo.board.domain.BoardDto;

public interface BoardService {

	public BoardDto register(BoardDto dto);

	public BoardDto get(int no);
	
	public List<BoardDto> getList();

	public boolean modify(BoardDto dto);

	public boolean remove(int no);

}
