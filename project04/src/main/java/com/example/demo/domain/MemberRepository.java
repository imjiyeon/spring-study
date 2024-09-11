package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//스프링 없이 순수하게 서블릿만 사용하기 위해 가상의 리파지토리 구현
public class MemberRepository {

	// 회원 정보를 저장할 가상 저장소 (테이블 대신)
    static Map<Integer, Member> store = new HashMap<>();
    
    // 회원 번호를 자동으로 생성하기 위한 시퀀스
    static int sequence = 0;

    // 새로운 회원을 저장소에 추가
    public Member save(Member member) {
        member.setNo(++sequence); //회원 번호를 증가시켜서 설정
        store.put(member.getNo(), member);
        return member;
    }
    
    // 회원번호로 저장소에서 회원을 조회
    public Member findById(int id) {
        return store.get(id);
    }

    // 저장소에 있는 모든 회원을 조회
    public List<Member> findAll() {
    	// map에 저장된 값들을 collection으로 가져온 후, List로 변환하여 반환
        return new ArrayList<>(store.values());
    }

    // 저장소에 있는 모든 회원 정보를 삭제
    public void clearStore() {
        store.clear();
    }
}
