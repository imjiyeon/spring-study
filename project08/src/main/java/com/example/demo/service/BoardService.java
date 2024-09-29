package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	// 게시물 등록 메소드
	int register(BoardDTO dto);
	
	// 게시물 목록조회 메소드
	Page<BoardDTO> getList(int pageNumber);

	// 게시물 상세조회 메소드
	BoardDTO read(int no);

	// 게시물 수정 메소드
	void modify(BoardDTO dto);

	// 게시물 삭제 메소드
	void remove(int no);

	// dto를 엔티티로 변환하는 메소드
	default Board dtoToEntity(BoardDTO dto) {
		
		/* 작성자 필드 수정 */
		//작성자 객체 생성
		Member member = Member.builder().id(dto.getWriter()).build(); 

		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
//				.writer(dto.getWriter())
				.writer(member) //변경
				.build();
		return entity;
	}

	// 엔티티를 dto로 변환하는 메소드
	default BoardDTO entityToDto(Board entity) {

		/* 작성자 필드 수정 */
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
//				.writer(entity.getWriter())
				.writer(entity.getWriter().getId()) //변경
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

}
