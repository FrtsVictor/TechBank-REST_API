package com.serratec.techbank1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.techbank1.exception.NumeroNotFoundIdException;
import com.serratec.techbank1.exception.invalidIdException;
import com.serratec.techbank1.exception.invalidSaldoException;
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
	
	
	public Conta exibirPorNumero(Integer numero) throws invalidIdException, NumeroNotFoundIdException {
		verificarIdValido(numero);
			
		int cont = 0;
			for (Conta conta : listaContas) {
				
				if(conta.getNumero() == numero){
					cont++;
					return conta;
					}
				}
			
			if(cont == 0) {
				numeroNaoEncontrado(numero);
		}			
			return null;
	}
					
	
	
	public Conta adicionarConta(Conta conta) {
		Conta ct = conta;
		listaContas.add(ct);
		return ct;
	}
	
	
	
	public Conta atualizarConta(Conta conta) throws invalidIdException, NumeroNotFoundIdException {
		Conta ct = exibirPorNumero(conta.getNumero());
		listaContas.set(listaContas.indexOf(ct), conta);
		return ct;
	}

	

	public void deletarConta(Integer numero) throws invalidIdException, NumeroNotFoundIdException {
		Conta ct = exibirPorNumero(numero);
		listaContas.remove(ct);
	}
	
	
	
	
//____________________________EXCEPTIONS_______________________________________________________________________	
	
	
	
	private void numeroNaoEncontrado(Integer numero) throws invalidIdException, NumeroNotFoundIdException{
		if(numero == null ) {
			throw new NumeroNotFoundIdException();
		}

	}
	
	
	
	
	private void verificarIdValido(Integer numero) throws invalidIdException{
		if (numero <= 0 || numero == null) {
			throw  new invalidIdException();
		}
	}
	
	
	
	
	
//___________________________OPERACOES________________________________________________________________

	
	@Autowired
	 Operacao operacao;


	public void operacao(Integer numero, Double valor, String tipo) throws invalidIdException, NumeroNotFoundIdException, invalidSaldoException {
		operacao.setTipo(Tipo.valueOf(tipo.toUpperCase()));
		Conta ct = exibirPorNumero(numero);
		
		
		if(operacao.getTipo() == Tipo.DEBITO && ct.getSaldo() > valor) {
			operacao.debitar(ct, valor);
			// "Valor debitado = " + valor + " Saldo = " + ct.getSaldo();
					
			
		if(operacao.getTipo() == Tipo.CREDITO ) {
			operacao.creditar(ct, valor);
			//return "Valor creditado = " + valor + " Saldo = " + ct.getSaldo();
		}
	
		}else {
			throw new invalidSaldoException();
		}
		
	}
	
	
	
	
	
	
}
