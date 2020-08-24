package com.serratec.techbank1.exception;

public class InvalidSaldoException extends Exception{

	private static final long serialVersionUID = 14744L;
	

	Double saldo;
	Double valor;

	public InvalidSaldoException(Double saldo, Double valor) {
		super();
		this.valor = valor;
		this.saldo = saldo;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	
}
