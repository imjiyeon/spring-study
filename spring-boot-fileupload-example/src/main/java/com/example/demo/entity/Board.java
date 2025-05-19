package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="table_board")
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
    
    // 파일 업로드를 위한 새로운 필드 추가!
    @Column(length = 200, nullable = true)
	String imgFileName; // 이미지 파일 이름

}
