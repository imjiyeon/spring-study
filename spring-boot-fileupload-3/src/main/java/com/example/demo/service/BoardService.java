package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {

	// 게시물 등록
	int register(BoardDTO dto);

	// 게시물 목록조회
	List<BoardDTO> getList();

	// 게시물 상세조회
	BoardDTO read(int no);

	// 게시물 수정
	void modify(BoardDTO dto);

	// 게시물 삭제
	int remove(int no);

	// dto를 엔티티로 변환하는 메소드
	default Board dtoToEntity(BoardDTO dto) { // default키워드를 사용하여 일반메소드 추가
		Board entity = Board.builder() // builder를 사용하면 필요한 값만 넣어서 인스턴스를 생성할수 있음
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter()) //날짜 생략
				.build();
		return entity;
	}

	// 엔티티를 dto로 변환하는 메소드
	default BoardDTO entityToDto(Board entity) {

		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.imgPath(entity.getImgPath()) //이미지경로 추가
				.build();

		return dto;
	}

}
