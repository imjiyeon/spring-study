package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

// JpaRepository 상속받고, 제네릭 타입 설정
public interface MemberRepository extends JpaRepository<Member, String> {
}
