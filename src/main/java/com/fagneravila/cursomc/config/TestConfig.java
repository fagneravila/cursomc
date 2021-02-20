package com.fagneravila.cursomc.config;

import java.text.ParseException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fagneravila.cursomc.services.DBService;
import com.fagneravila.cursomc.services.EmailService;
import com.fagneravila.cursomc.services.MockEmailService;

@Configuration
@Profile("teste")
public class TestConfig {

	@Autowired
	private DBService dbService;

	
	@Bean
	public boolean instantiareDataBase() throws ParseException {
		dbService.instantiaretesteDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
	
}