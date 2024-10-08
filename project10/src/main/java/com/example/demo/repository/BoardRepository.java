package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

//INSERT, UPDATE, DELETE 기능을 사용할 때 추가 (commit 처리 필요)
@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {

	// 특정 작성자가 작성한 모든 게시물을 삭제하는 메소드
	@Modifying //INSERT, UPDATE, DELETE 메소드 작성시 필요
	@Query("delete from Board where writer = :member")
	// 파라미터는 엔티티의 참조 타입에 따라 아이디 값이 아닌 member 엔티티를 사용해야함
    public void deleteWriter(@Param("member") Member member);

}
