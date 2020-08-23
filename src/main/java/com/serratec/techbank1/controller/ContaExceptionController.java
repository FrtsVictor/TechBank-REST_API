package com.serratec.techbank1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.techbank1.exception.NumeroNotFoundIdException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetida;
import com.serratec.techbank1.exception.InvalidIdException;
import com.serratec.techbank1.exception.InvalidSaldoException;

@RestControllerAdvice
public class ContaExceptionController {

	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> invalidId(InvalidIdException exception){				
		String msg = "Numero inserido invalido";
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "INVALID_NUMBER")
				.build();		
	}
	
	
	
	@ExceptionHandler(InvalidSaldoException.class)
	public ResponseEntity<?> invalidValue(InvalidSaldoException exception){	
		String msg = "Voce não possui saldo suficiente para esta operacao";
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "IVALID_BALANCE")
				.build();
	}
	
	
	
	@ExceptionHandler(NumeroNotFoundIdException.class)
	public ResponseEntity<?> notFoundNumber(NumeroNotFoundIdException exception){	
		String msg ="Numero inserido não existente";
		return ResponseEntity.notFound()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "NOT_FOUND")
				.build();
	}
	
	
	
	@ExceptionHandler(ContaRepetida.class)
	public ResponseEntity<String> numeroRepetido(ContaRepetida exception){	
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
