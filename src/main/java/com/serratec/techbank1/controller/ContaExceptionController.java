package com.serratec.techbank1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
import com.serratec.techbank1.exception.SaldoInvalidoException;
import com.serratec.techbank1.exception.ValorInvalidoException;

@RestControllerAdvice
public class ContaExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> erroGenerico(Exception exception) {
		String msg = ("Nos Desculpe! Ocorreu interno no servidor :(");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("X-Erro-msg", msg)
				.header("X-Erro-msg", "GENERIC SERVER ERROR").header("X-Erroo-code", "INTERNAL SERVER ERROR").build();
	}

	@ExceptionHandler(NumeroInvalidoException.class)
	public ResponseEntity<?> numeroInvalido(NumeroInvalidoException exception) {
		String msg = String.format("%d = Invalido.Digite um valor maior que 0", exception.getNumero());
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_NUMBER").build();
	}

	@ExceptionHandler(SaldoInvalidoException.class)
	public ResponseEntity<?> saldoInvalido(SaldoInvalidoException exception) {
		String msg = String.format("R$%.2f saldo insuficiente.", exception.getSaldo());
		return ResponseEntity.badRequest().header("X-Erro-msg", msg).header("X-Erro-code", "IVALID_BALANCE").build();
	}

	@ExceptionHandler(NumeroNaoEncontradoException.class)
	public ResponseEntity<?> numeroNaoEncontrado(NumeroNaoEncontradoException exception) {
		String msg = "Numero inserido não encontrado";
		return ResponseEntity.notFound().header("X-Erro-msg", msg).header("X-Erro-code", "NOT_FOUND").build();
	}

	@ExceptionHandler(ContaRepetidaException.class)
	public ResponseEntity<String> numeroRepetido(ContaRepetidaException exception) {
		String msg = String.format("Conta: %d já existe em nosso sistema", exception.getNumero());
		return ResponseEntity.badRequest().header("X-Erro-msg", msg)
				.header("X-Erro-code", "NUMBER_ALREADY_EXISTS" + exception.getNumero()).build();
	}

	@ExceptionHandler(ContaNullException.class)
	public ResponseEntity<Conta> contaInvalida(ContaNullException exception) {
		String msg = String.format("Voce nao inseriu todos os atributos requeridos para a criacao da nova conta");
		return ResponseEntity.badRequest().header("X-Erro-msg", msg).header("X-Erro-code", "NULL_VALUES").build();
	}

	@ExceptionHandler(ValorInvalidoException.class)
	public ResponseEntity<?> valorInvalido(ValorInvalidoException exception) {
		String msg = String.format("R$%.2f = Invalido.Valor minimo para operacao R$1.00", exception.getValor());
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_VALUE").build();
	}

}
