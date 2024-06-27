package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Board;

//JpaRepository 상속받기
public interface BoardRepository extends JpaRepository<Board, Integer> { //엔티티와 pk타입 지정
}
