package com.serratec.techbank1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.model.Operacao;
import com.serratec.techbank1.model.Tipo;



@Service
public class ContaService {
	
	private List<Conta> listaContas;	
	
	public ContaService() {		
		listaContas = new ArrayList<Conta>(); 	
		listaContas.add(new Conta(1, "Victor", 5000.00));
		listaContas.add(new Conta(2, "Lais", 4000.00));
		listaContas.add(new Conta(3, "Priscila", 5000.00));
		listaContas.add(new Conta(4, "Evodio", 7000.00));
		listaContas.add(new Conta(5, "Carlos", 5000.00));
	}
		

	
	public List<Conta> listarContas(){
		return listaContas;
	}
	
	
	public Conta exibirPorNumero(Integer numero) {
		for (Conta conta : listaContas) {
			if(conta.getNumero().equals(numero)){
			return conta;
			}
		}
		return null;
	}
	
	
	
	public Conta adicionarConta(Conta conta) {
		Conta ct = conta;
		listaContas.add(ct);
		return ct;
	}
	
	
	
	public Conta atualizarConta(Conta conta) {
		Conta ct = exibirPorNumero(conta.getNumero());
		listaContas.set(listaContas.indexOf(ct), conta);
		return ct;
	}

	

	public void deletarConta(Integer numero) {
		Conta ct = exibirPorNumero(numero);
		listaContas.remove(ct);
	}
	
//___________________________OPERACOES________________________________________________________________

	@Autowired
	 Operacao operacao;
	

	public Conta debitar(Integer numero, Double valor) {
		Conta ct = exibirPorNumero(numero);
		operacao.debitar(ct,valor);
		operacao.setTipo(Tipo.DEBITO);
		return ct;
	}
	
	
	public Conta operacao(Integer numero, Double valor, String tipo) {
		operacao.setTipo(Tipo.valueOf(tipo));
		Conta ct = exibirPorNumero(numero);
		
		if(operacao.getTipo() == Tipo.DEBITO) {
			operacao.debitar(ct,valor);
			
			return ct;
		}
		return ct;		

	}
	
	
	
	
	
	
}