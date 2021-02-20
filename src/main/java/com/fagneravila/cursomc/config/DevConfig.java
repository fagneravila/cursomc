package com.fagneravila.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fagneravila.cursomc.services.DBService;
import com.fagneravila.cursomc.services.EmailService;
import com.fagneravila.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

@Autowired
private DBService dbService;

@Value("${spring.jpa.hibernate.ddl-auto}")
private String strategy;


@Bean
public boolean instantiareDataBase() throws ParseException {
	
	if(!"create".equals(strategy)) {
		return false;
	}
	dbService.instantiaretesteDataBase();
	return true;
}

@Bean
public EmailService emailService() {
	return new SmtpEmailService();
}

	
}
