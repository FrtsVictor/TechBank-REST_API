package com.serratec.techbank1.model;

import org.springframework.stereotype.Component;

@Component
public class Operacao {


	private Conta conta;
	private Double valor;
	private Tipo tipo;
	
	
	public Operacao(Tipo tipo, Conta conta, Double valor) {
		super();
		this.tipo = tipo;
		this.conta = conta;
		this.valor = valor;
	}

	public Operacao() {}




	public Conta getConta() {
		return conta;
	}


	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}
	

	
	public Conta debitar(Conta conta, Double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
		return conta;
	}
	
	public double creditar(Conta conta , Double valor) {
		conta.setSaldo(conta.getSaldo() + valor);	
		return 0;
	}
	
	
}
