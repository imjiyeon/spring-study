package com.example.spring11.repository;


import com.example.spring11.entity.Board;
import com.example.spring11.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //작성자 필드를 기준으로 게시물을 삭제하는 메소드 추가
    @Query("delete from Board b where b.writer = :member")
    public void deleteWriter(@Param("member") Member member);

}
