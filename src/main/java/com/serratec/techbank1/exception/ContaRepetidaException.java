package com.serratec.techbank1.exception;

public class ContaRepetidaException extends Exception{


	private static final long serialVersionUID = 597168399982939419L;

	Integer numero;

	public ContaRepetidaException(Integer numero) {
		super();
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}


	
	
}
