package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    //@Schema는 데이터모델을 정의할때 사용함
    @Schema(description = "글번호") //필드의 정보를 작성
            int no;

    @Schema(description = "제목")
    String title;

    @Schema(description = "내용")
    String content;

    @Schema(description = "작성자")
    String writer;

    @Schema(description = "등록일")
    LocalDateTime regDate;

    @Schema(description = "수정일")
    LocalDateTime modDate;
}
