package com.example.demo.board.repository;


import java.util.List;

import com.example.demo.board.domain.BoardDto;

public interface BoardRepository {
	
	// 등록
	public BoardDto insert(BoardDto dto); /* 리턴타입 변경 */
	
	// 목록 읽기
	public List<BoardDto> selectList();
	
	// 단건 읽기
	public BoardDto select(int no);
	
	// 수정
	public int update(BoardDto dto);
	
	// 삭제
	public int delete(int no);
	
}

