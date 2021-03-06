package com.serratec.techbank1.model;

import org.springframework.stereotype.Component;

@Component
public class Conta {

	private Integer numero;
	private String titular;
	private Double saldo;
	private boolean ativa;

	public Conta(Integer numero, String titular, Double saldo, boolean ativa) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
		this.ativa = ativa;
	}
	
	
	

	public boolean isAtiva() {
		return ativa;
	}




	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}




	public Conta() {
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Numero conta:  " + numero + "\n" + "Titular: " + titular + "\n" + "Saldo:R$" + saldo;
	}

}
