package com.recon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.recon.dao.UserDao;

@SpringBootApplication
@EnableAutoConfiguration
public class MyresumeApplication  {
	  
	@Autowired
	UserDao userdao;
    
	public static void main(String[] args) {
		SpringApplication.run(MyresumeApplication.class, args);
	}


}
