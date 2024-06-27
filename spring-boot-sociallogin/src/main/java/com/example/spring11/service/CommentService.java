package com.example.spring11.service;


import com.example.spring11.dto.CommentDTO;
import com.example.spring11.entity.Board;
import com.example.spring11.entity.Comment;
import com.example.spring11.entity.Member;

import java.util.List;

public interface CommentService {

	int register(CommentDTO dto);

//	List<CommentDTO> getList();
	
	List<CommentDTO> getListByBoardNo(int boardNo);

//	CommentDTO read(int no);

//	void modify(CommentDTO dto);

	void remove(int no);

	default Comment dtoToEntity(CommentDTO dto) {

		Member member = Member.builder().id(dto.getWriter()).build(); //엔티티 생성

		Board board = Board.builder().no(dto.getBoardNo()).build();  //엔티티 생성

		Comment entity = Comment.builder()
				.commentNo(dto.getCommentNo())
				.board(board)
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}

	default CommentDTO entityToDto(Comment entity) {

		CommentDTO dto = CommentDTO.builder()
				.commentNo(entity.getCommentNo())
				.boardNo(entity.getBoard().getNo())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate()) 
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

}
