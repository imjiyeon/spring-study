package com.example.spring11.service;


import com.example.spring11.dto.CommentDTO;
import com.example.spring11.entity.Board;
import com.example.spring11.entity.Comment;
import com.example.spring11.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public int register(CommentDTO dto) {
		Comment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}

//	@Override
//	public List<CommentDTO> getList() {
//		List<Comment> entityList = repository.findAll();
//		List<CommentDTO> dtoList = new ArrayList<>();
//		for (Comment entity : entityList) {
//			CommentDTO dto = entityToDto(entity);
//			dtoList.add(dto);
//		}
//
//		return dtoList;
//	}
	
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		Board board = Board.builder().no(boardNo).build();  //엔티티 생성
		List<Comment> entityList = repository.findByBoard(board);
		List<CommentDTO> dtoList = new ArrayList<>();
		for (Comment entity : entityList) {
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}

		return dtoList;
	}

//	@Override
//	public CommentDTO read(int no) {
//		Optional<Comment> result = repository.findById(no);
//		if(result.isPresent()) {
//			Comment entity = result.get();
//			return entityToDto(entity);
//		}
//		return null;
//	}

//	@Override
//	public void modify(CommentDTO dto) {
//		int commentNo = dto.getCommentNo();
//		Optional<Comment> result = repository.findById(commentNo);
//		if(result.isPresent()) {
//			Comment entity = result.get();
//			entity.setContent(dto.getContent());
//			repository.save(entity);
//		}
//	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}	

}
