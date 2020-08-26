package com.serratec.techbank1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.exception.OperacaoInvalidaException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NomeInvalidoException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
import com.serratec.techbank1.exception.SaldoInvalidoException;
import com.serratec.techbank1.exception.ValorInvalidoException;
import com.serratec.techbank1.exception.ValorOperacaoException;

@RestControllerAdvice
public class ContaExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> erroGenerico(Exception exception) {
		String msg = ("Nos Desculpe! Ocorreu um erro interno no servidor :(");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("X-Erro-msg", msg)
				.header("X-Erro-msg", "GENERIC SERVER ERROR").header("X-Erroo-code", "INTERNAL SERVER ERROR").build();
	}

	@ExceptionHandler(NumeroInvalidoException.class)
	public ResponseEntity<String> numeroInvalido(NumeroInvalidoException exception) {
		String msg = String.format("%d = Invalido.Digite um valor maior que 0", exception.getNumero());
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_NUMBER").build();
	}

	@ExceptionHandler(SaldoInvalidoException.class)
	public ResponseEntity<String> saldoInvalido(SaldoInvalidoException exception) {
		String msg = String.format("R$-%.2f saldo insuficiente.", exception.getSaldo());
		return ResponseEntity.badRequest().header("X-Erro-msg", msg).header("X-Erro-code", "IVALID_BALANCE").build();
	}

	@ExceptionHandler(NumeroNaoEncontradoException.class)
	public ResponseEntity<String> numeroNaoEncontrado(NumeroNaoEncontradoException exception) {
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
		return ResponseEntity.badRequest().header("X-Erro-msg", msg).header("X-Erro-code", "CONTAINS_NULL_VALUES").build();
	}

	@ExceptionHandler(ValorInvalidoException.class)
	public ResponseEntity<String> valorInvalido(ValorInvalidoException exception) {
		String msg = String.format("R$%.2f = Invalido.Valor minimo para operacao R$1,00", exception.getValor());
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_VALUE").build();
	}
	
	
	@ExceptionHandler(ValorOperacaoException.class)
	public ResponseEntity<String> valorOperacaoInvalido(ValorOperacaoException exception) {
		String msg = "Valor minimo para operacao de credito: R$50.00";
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_VALUE").build();
	}

	
	@ExceptionHandler(OperacaoInvalidaException.class)
	public ResponseEntity<String> operacaoInvalida(OperacaoInvalidaException exception) {
		String msg = String.format("Operacao invalida! Operacoes validas:'credito' 'debito'");
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_OPERATION").build();
	}
	
	
	@ExceptionHandler(NomeInvalidoException.class)
	public ResponseEntity<String> nomeInvalido(NomeInvalidoException exception) {
		String msg = "Nome nao inserido.";
		return ResponseEntity.badRequest().header("Error-msg", msg).header("Error-code", "INVALID_NAME").build();
	}
	
	
}
