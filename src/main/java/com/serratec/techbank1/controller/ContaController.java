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
import com.serratec.techbank1.exception.SaldoInvalidoException;
import com.serratec.techbank1.exception.ValorInvalidoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	ContaService contaService;

	@Autowired
	AboutUsConfig aboutUsConfig;

	@GetMapping("/about-us")
	public ResponseEntity<AboutUsConfig> aboutUs() {
		return new ResponseEntity<AboutUsConfig>(aboutUsConfig, contaService.Header(), HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<List<Conta>> listarContas() {
		return new ResponseEntity<List<Conta>>(contaService.listarContas(), contaService.Header(), HttpStatus.OK);
	}

	@GetMapping("/size")
	public ResponseEntity<String> contarLista() {
		String tamanhoLista = "Contas no sistema: " + contaService.contarLista().toString();
		return new ResponseEntity<String>(tamanhoLista, contaService.Header(), HttpStatus.OK);
	}

	@GetMapping("/{numero}")
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		// contaService.exibirPorNumero(numero);
		return new ResponseEntity<Conta>(contaService.exibirPorNumero(numero), contaService.Header(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Conta> adicionarConta(Conta conta)
			throws ContaRepetidaException, ContaNullException, NumeroInvalidoException, SaldoInvalidoException {
		return new ResponseEntity<Conta>(contaService.adicionarConta(conta), contaService.Header(), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Conta> atualizarConta(Conta conta)
			throws NumeroInvalidoException, NumeroNaoEncontradoException, SaldoInvalidoException {
		contaService.atualizarConta(conta);
		return new ResponseEntity<Conta>(conta, contaService.Header(), HttpStatus.OK);
	}

	@PutMapping("/{numero}/{tipo}={valor}")
	public ResponseEntity<String> operacao(@PathVariable Integer numero, @PathVariable Double valor,
			@PathVariable String tipo) throws NumeroInvalidoException, NumeroNaoEncontradoException,
			SaldoInvalidoException, ValorInvalidoException {

		contaService.operacao(numero, valor, tipo);
		String op = String.format("Tipo de operacao:%s\nValor da operacao:R$%.2f\n%s", tipo.toUpperCase(), valor,
				contaService.exibirPorNumero(numero).toString());
		return new ResponseEntity<String>(op, contaService.Header(), HttpStatus.OK);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ctDeletada = contaService.exibirPorNumero(numero);
		String cd = "Conta excluida com sucesso!\n" + ctDeletada.toString();
		contaService.deletarConta(numero);
		return new ResponseEntity<String>(cd, contaService.Header(), HttpStatus.OK);
	}

	@DeleteMapping("/clear")
	public ResponseEntity<String> limparLista() {
		contaService.limparLista();
		String msg = "Contas excuidas com sucesso";
		return new ResponseEntity<String>(msg, contaService.Header(), HttpStatus.OK);
	}

}
