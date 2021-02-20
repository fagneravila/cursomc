package com.fagneravila.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String nome) {
		super(nome);
	}
	
	public DataIntegrityException(String nome,  Throwable causa) {
		super(nome, causa);
	}
	
	
}
