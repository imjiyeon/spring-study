package com.example.demo.repository;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;

	@Test
	public void 게시물등록() {
		Member member1 = Member.builder().id("user1").build();
		List<Board> list = new ArrayList<>();
		list.add(new Board(0,"1번글","내용입니다", member1));
		list.add(new Board(0,"2번글","내용입니다", member1));
		list.add(new Board(0,"3번글","내용입니다", member1));
		repository.saveAll(list);
	}

	@Test
	public void 없는아이디로게시물등록하기() {
		Member member = Member.builder().id("user2").build();
		Board board = new Board(0,"4번글","내용입니다", member);
		repository.save(board);
	}

    @Test
    public void 게시물단건조회(){
        Optional<Board> optional =  repository.findById(1);
        Board board = optional.get();
        System.out.println(board);
    }

}
