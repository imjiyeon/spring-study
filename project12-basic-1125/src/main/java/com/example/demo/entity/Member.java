package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

	@Id
	@Column(length = 50)
	String id;

	@Column(length = 200, nullable = false)
	String password;

	@Column(length = 100, nullable = false)
	String name;

	@Column(length = 100, nullable = false)
	String role;

}
