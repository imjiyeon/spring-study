package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Memo;

/*
 * 메모 엔티티 클래스를 처리하기 위한 인터페이스 만들기
 * */
public interface MemoRepository extends JpaRepository<Memo, Integer> {

}

/* 
 * JpaRepository를 상속받으면 crud 처리 메소드를 물려받음
 * JpaRepository를 사용할 때는 엔티티와 PK의 타입 지정해야 함
 * */