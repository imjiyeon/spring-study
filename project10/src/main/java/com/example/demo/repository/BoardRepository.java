package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //작성자 필드를 기준으로 게시물을 삭제하는 메소드 추가
    @Query("delete from Board b where b.writer = :member")
    public void deleteWriter(@Param("member") Member member);

}
