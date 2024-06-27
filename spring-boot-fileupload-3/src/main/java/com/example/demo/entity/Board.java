package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity { // 등록일, 수정일 필드 상속받기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; //글번호

    @Column(length = 100, nullable = false)
    String title; //제목

    @Column(length = 1500, nullable = false)
    String content; //내용

    @Column(length = 50, nullable = false)
    String writer; //작성자
    
    @Column(length = 200, nullable = true)
	private String imgPath; //첨부파일 이름

}
