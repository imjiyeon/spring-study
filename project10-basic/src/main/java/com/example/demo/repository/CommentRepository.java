package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;

import jakarta.transaction.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByBoard(Board board);

	void deleteByBoard(Board board);

}
