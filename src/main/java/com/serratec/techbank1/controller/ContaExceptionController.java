package com.serratec.techbank1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.techbank1.exception.NumeroNotFoundIdException;
import com.serratec.techbank1.exception.invalidIdException;
import com.serratec.techbank1.exception.invalidSaldoException;

@RestControllerAdvice
public class ContaExceptionController {

	
	@ExceptionHandler(invalidIdException.class)
	public ResponseEntity<?> invalidId(invalidIdException exception){				
		String msg = "Numero inserido invalido";
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "INVALID_NUMBER")
				.build();		
	}
	
	
	
	@ExceptionHandler(invalidSaldoException.class)
	public ResponseEntity<?> invalidValue(invalidSaldoException exception){	
		String msg = "Voce não possui saldo suficiente para esta operacao";
		return ResponseEntity.badRequest()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "IVALID_BALANCE")
				.build();
	}
	
	
	
	@ExceptionHandler(NumeroNotFoundIdException.class)
	public ResponseEntity<?> notFoundNumber(NumeroNotFoundIdException exception){	
		String msg ="Numero digitado não existente";
		return ResponseEntity.notFound()
				.header("X-Erro-msg", msg)
				.header("X-Erro-code", "NOT_FOUND")
				.build();
	}
	
	
	
	
	
}
