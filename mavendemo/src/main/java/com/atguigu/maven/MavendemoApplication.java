package com.atguigu.maven;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.atguigu.maven.mapper")
public class MavendemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavendemoApplication.class, args);
	}

}
