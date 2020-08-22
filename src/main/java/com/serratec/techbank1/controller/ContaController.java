package com.serratec.techbank1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.techbank1.exception.NumeroNotFoundIdException;
import com.serratec.techbank1.exception.invalidIdException;
import com.serratec.techbank1.exception.invalidSaldoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.service.ContaService;



@RestController
@RequestMapping("/conta")
public class ContaController {
	
	
	@Autowired
	ContaService contaService;
	
	
	@GetMapping
	public List<Conta> listarContas(){
		return contaService.listarContas();
	}
	
	
	@GetMapping("/{numero}")
	public Conta exibePorNumero(@PathVariable Integer numero) throws invalidIdException, NumeroNotFoundIdException {
		return contaService.exibirPorNumero(numero);
	}

	
	@PostMapping
	public Conta adicionarConta(Conta conta) {
		contaService.adicionarConta(conta);
		return conta;
	}
	
	
	@PutMapping
	public Conta atualizarConta(Conta conta) throws invalidIdException, NumeroNotFoundIdException {
		contaService.atualizarConta(conta);
		return conta;
	}
	
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero) throws invalidIdException, NumeroNotFoundIdException {
		contaService.deletarConta(numero);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	@PutMapping("/{numero}/{tipo}={valor}")
	public String operacao(@PathVariable Integer numero,
						   @PathVariable Double valor,
						   @PathVariable String tipo) throws invalidIdException, NumeroNotFoundIdException, invalidSaldoException {
		
		contaService.operacao(numero, valor, tipo);	
		return "Valor operacao = " + valor + "\nSaldo =  " + contaService.exibirPorNumero(numero).getSaldo();
	}
	
	
	
	
}
