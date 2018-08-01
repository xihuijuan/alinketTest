package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:/hbase-spring.xml"})
public class Hbasetest2Application {

	public static void main(String[] args) {
		System.setProperty("hadoop.home.dir", "D:\\\\HbaseProgram\\\\hadoop-2.9.1");
		SpringApplication.run(Hbasetest2Application.class, args);
	}
}
