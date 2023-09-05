package com.example.boardcrudex.boardcrudex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BoardCrudExApplication {

	@RequestMapping("/")
	String hello(){
		return "안녕!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(BoardCrudExApplication.class, args);
	}

}
