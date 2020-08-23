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
import com.serratec.techbank1.exception.ContaRepetida;
import com.serratec.techbank1.exception.InvalidIdException;
import com.serratec.techbank1.exception.InvalidSaldoException;
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
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero) throws InvalidIdException, NumeroNotFoundIdException {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.exibirPorNumero(numero));

	}

	
	@PostMapping
	public ResponseEntity<Conta> adicionarConta(Conta conta) throws ContaRepetida{
		contaService.adicionarConta(conta);
		return ResponseEntity.status(HttpStatus.CREATED).body(conta);
	}
		
	
	
	@PutMapping
	public ResponseEntity<Conta> atualizarConta(Conta conta) throws InvalidIdException, NumeroNotFoundIdException {
		contaService.atualizarConta(conta);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero) throws InvalidIdException, NumeroNotFoundIdException {
		contaService.deletarConta(numero);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	@PutMapping("/{numero}/{tipo}={valor}")
	public ResponseEntity<String> operacao(@PathVariable Integer numero,
						   @PathVariable Double valor,
						   @PathVariable String tipo) throws InvalidIdException, NumeroNotFoundIdException, InvalidSaldoException {
		
		contaService.operacao(numero, valor, tipo);	
        String op = "Valor operacao = " + valor + "\nSaldo =  " + contaService.exibirPorNumero(numero).getSaldo();
        return ResponseEntity.status(HttpStatus.OK).body(op);
	}
	
	
	
	
}
