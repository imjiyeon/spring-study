package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass // JPA가 해당클래스는 테이블로 생성하지 않음
//엔티티가 등록되거나 수정되는지 변화를 감지하는 리스너 지정
@EntityListeners(value = { AuditingEntityListener.class }) 
@Getter
abstract class BaseEntity {

    @CreatedDate //인스턴스가 생성되는 것을 감지하여 현재시간을 저장
    LocalDateTime regDate;

    @LastModifiedDate //인스턴스가 수정되는 것을 감지하여 현재시간을 저장
    LocalDateTime modDate;

}
