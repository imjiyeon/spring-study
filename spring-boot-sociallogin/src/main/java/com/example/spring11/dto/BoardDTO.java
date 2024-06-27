package com.example.spring11.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    int no;

    String title;

    String content;

    String writer;

    LocalDateTime regDate;

    LocalDateTime modDate;

}
