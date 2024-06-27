package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속받고, 처리할 데이터 설정
public interface MemberRepository extends JpaRepository<Member, String> {
}
