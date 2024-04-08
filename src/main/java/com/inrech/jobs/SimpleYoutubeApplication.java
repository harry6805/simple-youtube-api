package com.inrech.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SimpleYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleYoutubeApplication.class, args);
	}

}
