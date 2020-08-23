package com.serratec.techbank1.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
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
	
	public HttpHeaders Header() {		
	HttpHeaders header = new HttpHeaders();
	header.add("TECHBANK", "REST_API");
	header.add("SERRATEC", "SOFTWARE_IMERSION");
	return header;
	}
	
		
	public List<Conta> listarContas(){
		return listaContas;
	}
	
	
	public Conta exibirPorNumero(Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
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
					
	
	
	public Conta adicionarConta(Conta conta) throws ContaRepetidaException, ContaNullException{
		Conta ct = conta;
		if(conta.getNumero() == null || conta.getTitular() == null) {
			throw new ContaNullException(conta);
		}
		validarNumero(conta.getNumero());
		listaContas.add(ct);
		return ct;
	}
	
	
	
	public Conta atualizarConta(Conta conta) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ct = exibirPorNumero(conta.getNumero());
		if(conta.getTitular() == null || conta.getTitular().isBlank()) {conta.setTitular(ct.getTitular());}
		if(conta.getSaldo() == null) {conta.setSaldo(ct.getSaldo());}
		listaContas.set(listaContas.indexOf(ct), conta);
		return ct;
	}

	

	public void deletarConta(Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ct = exibirPorNumero(numero);
		listaContas.remove(ct);
	}
	
	
	
	
//____________________________EXCEPTIONS_______________________________________________________________________	
	
	
	
	private void numeroNaoEncontrado(Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException{
		if(numero == null ) {
			throw new NumeroNaoEncontradoException();
		}

	}
	
	
	
	
	private void verificarIdValido(Integer numero) throws NumeroInvalidoException{
		if (numero <= 0 || numero == null) {
			throw  new NumeroInvalidoException(numero);
		}
	}
	
	
	
	public void validarNumero(Integer numero) throws ContaRepetidaException{
		for (Conta conta : listaContas) {
			Boolean numeroInvalido = conta.getNumero().equals(numero);
			if (numeroInvalido) {
				throw new ContaRepetidaException(numero);
			
		}
		
	}
	}
	
	
	
	
	
	
//___________________________OPERACOES________________________________________________________________

	
	@Autowired
	 Operacao operacao;


	public Conta operacao(Integer numero, Double valor, String tipo) throws NumeroInvalidoException, NumeroNaoEncontradoException, InvalidSaldoException {
		operacao.setTipo(Tipo.valueOf(tipo.toUpperCase()));
		Conta ct = exibirPorNumero(numero);

		if(operacao.getTipo() == Tipo.CREDITO ) {
			operacao.creditar(ct, valor);
			return ct;
		}
		
		
		if(operacao.getTipo() == Tipo.DEBITO && ct.getSaldo() > valor) {
			operacao.debitar(ct, valor);
		return ct;
		}else {
			 throw new InvalidSaldoException(ct.getSaldo(), valor);
		 }
	
		
	}
	
	
	
	
	
	
}
