package com.serratec.techbank1.exception;

public class ValorOperacaoException extends Exception {

	private static final long serialVersionUID = 1510331425055396736L;

	Double valor;

	public ValorOperacaoException(Double valor) {
		super();
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	
}
