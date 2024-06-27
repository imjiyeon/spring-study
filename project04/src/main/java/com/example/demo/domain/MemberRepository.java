package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 가상 리파지토리 만들기
* */
public class MemberRepository {

    static Map<Integer, Member> store = new HashMap<>();
    static int sequence = 0;

    // 회원 등록
    public Member save(Member member) {
        member.setNo(++sequence);
        store.put(member.getNo(), member);
        return member;
    }
    
    // 회원 단건 조회
    public Member findById(int id) {
        return store.get(id);
    }

    // 회원 목록 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 회원 전체 삭제
    public void clearStore() {
        store.clear();
    }
}
