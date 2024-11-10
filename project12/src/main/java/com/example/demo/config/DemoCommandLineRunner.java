package com.example.demo.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoCommandLineRunner implements CommandLineRunner  {

	@Value("${filepath}")
	String filepath;
	
	@Override
	public void run(String... args) throws Exception {

		File file = new File(filepath);
		
		if(file.exists()) {
//			System.out.println(file + " 폴더가 존재합니다.");
		} else {
			file.mkdir();
			System.out.println(file + " 폴더가 생성되었습니다.");
		}

	}

}
