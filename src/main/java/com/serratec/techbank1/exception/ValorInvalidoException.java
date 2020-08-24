package com.serratec.techbank1.exception;

public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = -1292914176630403588L;
	Double valor;

	public ValorInvalidoException(Double valor) {
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
