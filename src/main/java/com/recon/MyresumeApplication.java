package com.recon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.recon.dao.UserDao;

@SpringBootApplication
@EnableAutoConfiguration
public class MyresumeApplication extends SpringBootServletInitializer  {
	  
	@Autowired
	UserDao userdao;
    
	public static void main(String[] args) {
		SpringApplication.run(MyresumeApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(MyresumeApplication.class);
	}  

}
