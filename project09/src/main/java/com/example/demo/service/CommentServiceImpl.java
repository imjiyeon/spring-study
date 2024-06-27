package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

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

	@Override
	public int register(CommentDTO dto) {
		Comment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}

	@Override
	public boolean remove(int no) {
		// 해당 댓글이 있는지 확인
		Optional<Comment> comment = repository.findById(no);
		// 없다면 fasle 반환
		if(comment.isEmpty()) {
			return false;
		}
		// 있다면 댓글 삭제 후 true 반환
		repository.deleteById(no);
		return true;
	}

}
