package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	int register(BoardDTO dto);

	List<BoardDTO> getList();

	BoardDTO read(int no);

	void modify(BoardDTO dto);

	void remove(int no);

	default Board dtoToEntity(BoardDTO dto) {
		
		// DTO와 Entity의 작성자 필드는 자료형이 다르기 때문에
		// 데이터 변환시 코드를 추가해야함
		
		// String -> Member 객체로 생성
		// 여기서 Member 객체를 생성할 때는 PK값만 필요함. 그외데이터는 사용안함
		Member member = Member.builder().id(dto.getWriter()).build();
		
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
//				.writer(dto.getWriter())
				.writer(member)
				.build();
		return entity;
	}

	default BoardDTO entityToDto(Board entity) {
		
		// 작성자 필드
		// Member 객체 -> String
		
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
//				.writer(entity.getWriter())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				// 추가
//				.imgPath(entity.getImgPath())
				.build();
		return dto;
	}

}
