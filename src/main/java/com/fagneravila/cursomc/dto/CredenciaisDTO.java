package com.fagneravila.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fagneravila.cursomc.domain.Cliente;
import com.fagneravila.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class CredenciaisDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;


	private String email;
	public String senha;
	
	
	public CredenciaisDTO() {
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
