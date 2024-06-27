package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
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

    @Column(length = 50, nullable = false)
    String writer;

}
