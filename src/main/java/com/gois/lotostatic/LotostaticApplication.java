package com.gois.lotostatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LotostaticApplication {
	//https://spring.io/guides/gs/batch-processing#initial
	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(LotostaticApplication.class, args)));
	}

}
