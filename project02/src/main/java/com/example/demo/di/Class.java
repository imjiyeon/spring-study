package com.example.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Class {

	@Autowired
	Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

}
