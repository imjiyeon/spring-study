package com.example.spring11.repository;


import com.example.spring11.entity.Board;
import com.example.spring11.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByBoard(Board board);

	void deleteByBoard(Board board);

}
