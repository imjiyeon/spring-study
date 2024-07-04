package com.example.demo.member.repository;

import java.util.List;

import com.example.demo.member.domain.MemberDto;

public interface MemberRepository {
	
	// 등록
	public MemberDto insert(MemberDto dto);
	
	// 목록 읽기
	public List<MemberDto> selectList();
	
	// 단건 읽기
	public MemberDto select(String id);
	
	// 수정
	public int update(MemberDto dto);
	
	// 삭제
	public int delete(String id);
	
}

