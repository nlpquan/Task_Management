package com.da;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.da"})
/**
 * This is the main application that runs
 * @author Quan Nguyen
 *
 */
public class TaskManagementApplication {
	/**
	 * This method is the main method
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
