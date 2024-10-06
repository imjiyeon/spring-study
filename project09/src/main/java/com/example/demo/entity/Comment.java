package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tbl_comment")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentNo;

    @ManyToOne
    Board board; //외래키

    @Column(length = 1500)
    String content;

    @ManyToOne
    Member writer; //외래키

}
