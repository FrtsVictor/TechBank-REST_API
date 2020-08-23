package com.serratec.techbank1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.techbank1.exception.NumeroNotFoundIdException;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetida;
import com.serratec.techbank1.exception.InvalidIdException;
import com.serratec.techbank1.exception.InvalidSaldoException;
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
	
	
	public Conta exibirPorNumero(Integer numero) throws InvalidIdException, NumeroNotFoundIdException {
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
					
	
	
	public Conta adicionarConta(Conta conta) throws ContaRepetida, ContaNullException{
		Conta ct = conta;
		if(conta.getNumero() == null || conta.getTitular() == null) {
			throw new ContaNullException(conta);
		}
		validarNumero(conta.getNumero());
		listaContas.add(ct);
		return ct;
	}
	
	
	
	public Conta atualizarConta(Conta conta) throws InvalidIdException, NumeroNotFoundIdException {
		Conta ct = exibirPorNumero(conta.getNumero());
		listaContas.set(listaContas.indexOf(ct), conta);
		return ct;
	}

	

	public void deletarConta(Integer numero) throws InvalidIdException, NumeroNotFoundIdException {
		Conta ct = exibirPorNumero(numero);
		listaContas.remove(ct);
	}
	
	
	
	
//____________________________EXCEPTIONS_______________________________________________________________________	
	
	
	
	private void numeroNaoEncontrado(Integer numero) throws InvalidIdException, NumeroNotFoundIdException{
		if(numero == null ) {
			throw new NumeroNotFoundIdException();
		}

	}
	
	
	
	
	private void verificarIdValido(Integer numero) throws InvalidIdException{
		if (numero <= 0 || numero == null) {
			throw  new InvalidIdException();
		}
	}
	
	
	
	public void validarNumero(Integer numero) throws ContaRepetida{
		for (Conta conta : listaContas) {
			Boolean numeroInvalido = conta.getNumero().equals(numero);
			if (numeroInvalido) {
				throw new ContaRepetida(numero);
			
		}
		
	}
	}
	
	
	
	
	
	
//___________________________OPERACOES________________________________________________________________

	
	@Autowired
	 Operacao operacao;


	public void operacao(Integer numero, Double valor, String tipo) throws InvalidIdException, NumeroNotFoundIdException, InvalidSaldoException {
		operacao.setTipo(Tipo.valueOf(tipo.toUpperCase()));
		Conta ct = exibirPorNumero(numero);
		
		
		if(operacao.getTipo() == Tipo.DEBITO && ct.getSaldo() > valor) {
			operacao.debitar(ct, valor);
		}
					
			
		 if(operacao.getTipo() == Tipo.CREDITO ) {
			operacao.creditar(ct, valor);
		}
	
		else {
			throw new InvalidSaldoException();
		}
		
	}
	
	
	
	
	
	
}
