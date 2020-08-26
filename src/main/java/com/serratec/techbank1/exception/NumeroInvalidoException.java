package com.serratec.techbank1.exception;

public class NumeroInvalidoException extends Exception{

	private static final long serialVersionUID = 19848948949L;

	Integer numero;

	public NumeroInvalidoException(Integer numero) {
		super();
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	NumeroInvalidoException(){}
	
	
	
}
