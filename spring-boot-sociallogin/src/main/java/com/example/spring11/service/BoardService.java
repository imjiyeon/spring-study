package com.example.spring11.service;


import com.example.spring11.dto.BoardDTO;
import com.example.spring11.entity.Board;
import com.example.spring11.entity.Member;
import org.springframework.data.domain.Page;

public interface BoardService {

	int register(BoardDTO dto);

	Page<BoardDTO> getList(int pageNumber);

	BoardDTO read(int no);

	void modify(BoardDTO dto);

	int remove(int no);

	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().id(dto.getWriter()).build(); //작성자 객체 생성

		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}

	default BoardDTO entityToDto(Board entity) {
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

}
