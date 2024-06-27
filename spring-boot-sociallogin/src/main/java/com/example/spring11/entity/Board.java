package com.example.spring11.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString(exclude = "writer") //게시물을 출력할 때 회원정보는 제외
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no;

    @Column(length = 100, nullable = false)
    String title;

    @Column(length = 1500, nullable = false)
    String content;

//    @Column(length = 50, nullable = false)
//    private String writer;

    /* 작성자 필드는 외래키이며, 회원 엔티티를 참조한다 */
    @ManyToOne //연관관계 설정
    Member writer; //작성자

}
