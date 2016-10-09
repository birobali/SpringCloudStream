package com.springclouddemo.sinkfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages="org.springframework.cloud.stream.app.file.sink")
public class SinkfileApplication {
	public static void main(String[] args) {
		SpringApplication.run(SinkfileApplication.class, args);
	}
}
