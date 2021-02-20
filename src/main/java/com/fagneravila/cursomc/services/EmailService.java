package com.fagneravila.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fagneravila.cursomc.domain.Pedido;


public interface EmailService {
	
void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	

}
