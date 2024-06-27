//package com.example.demo.etc;
//
//import com.example.demo.entity.Board;
//import com.example.demo.entity.Member;
//import com.example.demo.repository.BoardRepository;
//import com.example.demo.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest
//public class RepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    BoardRepository boardRepository;
//
//    @Test
//    public void 회원일괄등록() {
//        List<Member> list = new ArrayList();
//        list.add(new Member("user1","1234","둘리"));
//        list.add(new Member("user2","1234","또치"));
//        list.add(new Member("user3","1234","도우너"));
//        list.add(new Member("user4","1234","마이콜"));
//        list.add(new Member("user5","1234","고길동"));
//        memberRepository.saveAll(list);
//    }
//
//    @Test
//    public void 게시물일괄등록() {
//        List<Board> list = new ArrayList();
//        list.add(new Board(0,"1번글","오늘 날씨가..",memberRepository.findById("user1").get()));
//        list.add(new Board(0,"2번글","집에 오늘 길은..",memberRepository.findById("user2").get()));
//        list.add(new Board(0,"3번글","오늘 점심에..",memberRepository.findById("user3").get()));
//        list.add(new Board(0,"4번글","차가 막혀서..",memberRepository.findById("user1").get()));
//        list.add(new Board(0,"5번글","이번 개봉 영화..",memberRepository.findById("user2").get()));
//        boardRepository.saveAll(list);
//    }
//
//}
