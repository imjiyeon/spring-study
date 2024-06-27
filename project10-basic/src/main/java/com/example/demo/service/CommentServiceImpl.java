package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		Board board = Board.builder().no(boardNo).build();
		List<Comment> entityList = repository.findByBoard(board);
		List<CommentDTO> dtoList = new ArrayList<>();
		for (Comment entity : entityList) {
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public int register(CommentDTO dto) {
		Comment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}

	@Override
	public boolean remove(int no) {
		Optional<Comment> comment = repository.findById(no);
		if(comment.isEmpty()) {
			return false;
		}
		repository.deleteById(no);
		return true;
	}

}
