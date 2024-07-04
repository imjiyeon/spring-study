package com.example.demo.member.service;


import java.util.List;

import com.example.demo.member.domain.MemberDto;

public interface MemberService {

	public MemberDto register(MemberDto dto);

	public MemberDto get(String id);
	
	public List<MemberDto> getList();

	public boolean modify(MemberDto dto);

	public boolean remove(String id);

}
