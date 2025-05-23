package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {

	int register(BoardDTO dto);

	Page<BoardDTO> getList(int pageNumber);

	BoardDTO read(int no);

	void modify(BoardDTO dto);

	void remove(int no);

	default Board dtoToEntity(BoardDTO dto) {
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
	}

	// 변환 메소드 수정
	default BoardDTO entityToDto(Board entity) {
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.imgFileName(entity.getImgFileName()) // 이미지 파일 이름 추가
				.build();
		return dto;
	}

}
