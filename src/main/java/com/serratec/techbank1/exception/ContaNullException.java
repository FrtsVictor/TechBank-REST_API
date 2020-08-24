package com.serratec.techbank1.exception;

import com.serratec.techbank1.model.Conta;

public class ContaNullException extends Exception {

	private static final long serialVersionUID = 1611659059397609933L;

	Conta conta;

	public ContaNullException(Conta conta) {
		super();
		this.conta = conta;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
