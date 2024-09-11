package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	// 아이디가 "user"로 시작하는 회원 검색
	@Query(value = "select * from tbl_member where user_id like :userId%", nativeQuery = true)
	List<Member> get1(@Param("userId") String userId);

	// 등급이 "관리자"인 회원 검색
	@Query(value = "select * from tbl_member where grade = :grade", nativeQuery = true)
	List<Member> get2(@Param("grade") String grade);
}
