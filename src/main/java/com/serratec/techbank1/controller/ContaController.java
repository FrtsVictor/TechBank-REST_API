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
import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
import com.serratec.techbank1.exception.InvalidSaldoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.service.ContaService;



@RestController
@RequestMapping("/conta")
public class ContaController {
	
	
	@Autowired
	ContaService contaService;
	
	
	@GetMapping
	public ResponseEntity<List<Conta>> listarContas(){
		return new ResponseEntity<List<Conta>>(contaService.listarContas(), contaService.Header(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		//contaService.exibirPorNumero(numero);		
		return new ResponseEntity<Conta>(contaService.exibirPorNumero(numero), contaService.Header(), HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Conta> adicionarConta(Conta conta) throws ContaRepetidaException, ContaNullException{
		//contaService.adicionarConta(conta);
		//return ResponseEntity.status(HttpStatus.CREATED).body(conta);
		return new ResponseEntity<Conta>(contaService.adicionarConta(conta), contaService.Header(), HttpStatus.CREATED);
	}
		
	
	
	@PutMapping
	public ResponseEntity<Conta> atualizarConta(Conta conta) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		contaService.atualizarConta(conta);
//		return ResponseEntity.status(HttpStatus.OK).body(conta);
		return new ResponseEntity<Conta>(conta,contaService.Header(), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		contaService.deletarConta(numero);
		return new ResponseEntity<HttpStatus>(contaService.Header(), HttpStatus.OK);
	}
	
	
	@PutMapping("/{numero}/{tipo}={valor}")
	public ResponseEntity<String> operacao(@PathVariable Integer numero,
						   @PathVariable Double valor,
						   @PathVariable String tipo) throws NumeroInvalidoException, NumeroNaoEncontradoException, InvalidSaldoException {
		
		contaService.operacao(numero, valor, tipo);	
        String op = "Valor operacao = " + valor + "\nSaldo =  " + contaService.exibirPorNumero(numero).getSaldo();
        //return ResponseEntity.status(HttpStatus.OK).body(op);
        return new ResponseEntity<String>(op, contaService.Header(), HttpStatus.OK);
	}
	
	
	
	
}
