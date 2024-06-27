package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    String title; //제목

    String author; //저자

    LocalDate publicationDate; //출간일

}

