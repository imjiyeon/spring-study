package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class) // 엔티티에 변화를 감지하는 리스너 지정
@Table(name = "tbl_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int boardNo;

	@Column(length = 30, nullable = false)
	String title;

	@Column(length = 200)
	String content;

	@CreatedDate // 인스턴스가 생성되는 것을 감지하여 날짜를 저장
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdDate;

	@LastModifiedDate // 인스턴스가 수정되는 것을 감지하여 날짜를 저장
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime modifiedDate;
}


// 사용자가 입력하는지? 시스템이 입력하는지? 생각해보기!

/* 확인 */
//생성된 sql문을 확인한다
//'boardNo' 필드는 스네이크 케이스 방식에 따라 'board_no'로 변환되어 컬럼이 생성된다
