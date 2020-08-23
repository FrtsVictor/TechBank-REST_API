package com.serratec.techbank1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
import com.serratec.techbank1.exception.InvalidSaldoException;

@RestControllerAdvice
public class ContaExceptionController {

	
	@ExceptionHandler(NumeroInvalidoException.class)
	public ResponseEntity<?> invalidId(NumeroInvalidoException exception){				
		String msg = String.format("O numero = %d  é invalido, digite um valor maior que 0", exception.getNumero() );
		return ResponseEntity.badRequest()
				.header("Error-msg", msg)
				.header("Error-code", "INVALID_NUMBER")
				.build();		
	}
	
	
	
	@ExceptionHandler(InvalidSaldoException.class)
	public ResponseEntity<?> invalidValue(InvalidSaldoException exception){	
		String msg = String.format("Saldo: %.2f Valor da operecao: %.2f. Saldo insuficiente para esta operacao.", exception.getSaldo(), exception.getValor());
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "IVALID_BALANCE")
				.build();
	}
	
	
	
	@ExceptionHandler(NumeroNaoEncontradoException.class)
	public ResponseEntity<?> notFoundNumber(NumeroNaoEncontradoException exception){	
		String msg ="Numero inserido não existente";
		return ResponseEntity.notFound()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "NOT_FOUND")
				.build();
	}
	
	
	
	@ExceptionHandler(ContaRepetidaException.class)
	public ResponseEntity<String> numeroRepetido(ContaRepetidaException exception){	
		String msg = String.format("Numero de conta = %d já existe em nosso sistema", exception.getNumero());
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code","NUMBER_ALREADY_EXISTS" + exception.getNumero())
				.build();
	}
	
	
	
	@ExceptionHandler(ContaNullException.class)
	public ResponseEntity<Conta> contaNull(ContaNullException exception){	
		String msg = String.format("Voce nao inseriu todos os atributos requeridos para a criacao da nova conta");
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code","NULL_VALUES")
				.build();
	}
	
	
}
