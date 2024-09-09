package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Memo;

/*
 * 메모 엔티티 클래스를 처리하기 위한 인터페이스 만들기
 * */
public interface MemoRepository extends JpaRepository<Memo, Integer> {

}

//JpaRepository는 모든 기능을 상속받았기 때문에 CRUD 기능와 페이징 기능을 사용할 수 있다
//JpaRepository를 사용할 때는 엔티티와 해당엔티티의 PK 타입 지정해야 한다
