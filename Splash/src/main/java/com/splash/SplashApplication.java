package com.splash;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splash.entity.mysql.UserEntity;
import com.splash.services.UserService;
import com.splash.services.UserServiceImpl;

@SpringBootApplication
public class SplashApplication {


	public static void main(String[] args) {
		SpringApplication.run(SplashApplication.class, args);
	}


	  static String  asJsonString(final Object obj) {
	    	try {
	    		return new ObjectMapper().writeValueAsString(obj);
	    	}catch (Exception exp) {
	    		throw new RuntimeException(exp);
	    	}
	    }
}
