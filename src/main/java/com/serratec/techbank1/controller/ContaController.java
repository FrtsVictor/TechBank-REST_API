package com.serratec.techbank1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.serratec.techbank1.exception.ValorOperacaoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/conta")
@Api(value="REST API TechBank")
public class ContaController {

	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("TECHBANK", "REST_API");
		header.add("SERRATEC", "SOFTWARE_IMMERSION");
		return header;
	}
	
	
	@Autowired
	ContaService contaService;

	@Autowired
	AboutUsConfig aboutUsConfig;
	
	@ApiOperation(value="Retorna AboutUs, lista de informações sobre o sistema")
	@GetMapping("/about-us")
	public ResponseEntity<AboutUsConfig> aboutUs() {
		return new ResponseEntity<AboutUsConfig>(aboutUsConfig, Header(), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value="Retorna lista com todas as contas no sistema")
	@GetMapping
	public ResponseEntity<List<Conta>> listarContas() {
		return new ResponseEntity<List<Conta>>(contaService.listarContas(), Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Retorna quantidade total de contas na lista")
	@GetMapping("/size")
	public ResponseEntity<String> contarLista() {
		String tamanhoLista = "Contas no sistema: " + contaService.contarLista().toString();
		return new ResponseEntity<String>(tamanhoLista, Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Retorna conta pelo identificador 'numero' ,nao aceita numeros negativos ou 0")
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		return new ResponseEntity<Conta>(contaService.exibirPorNumero(numero), Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Adiciona uma conta no sistema, nao aceita valores nulos, negativos ou numeros de conta ja existentes")
	@PostMapping
	public ResponseEntity<Conta> adicionarConta(Conta conta)
			throws ContaRepetidaException, ContaNullException, NumeroInvalidoException, SaldoInvalidoException {
		return new ResponseEntity<Conta>(contaService.adicionarConta(conta), Header(), HttpStatus.CREATED);
	}

	@ApiOperation(value="Atualiza uma conta utilizando o identificador 'numero', caso todos os dados nao sejam inseridos, manten-se os valores anteriores")
	@PutMapping
	public ResponseEntity<Conta> atualizarConta(Conta conta)
			throws NumeroInvalidoException, NumeroNaoEncontradoException, SaldoInvalidoException {
		contaService.atualizarConta(conta);
		return new ResponseEntity<Conta>(conta, Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Faz uma operacao no banco, aceitando operacoes do tipo CREDITO, e DEBITO.")
	@PostMapping("/{numero}/{tipo}={valor}")
	public ResponseEntity<String> operacao(@PathVariable Integer numero, @PathVariable Double valor,
			@PathVariable String tipo) throws NumeroInvalidoException, NumeroNaoEncontradoException,
			SaldoInvalidoException, ValorInvalidoException, ValorOperacaoException {

		contaService.operacao(numero, valor, tipo);
		String op = String.format("Tipo de operacao:%s\nValor da operacao:R$%.2f\n%s", tipo.toUpperCase(), valor,
				contaService.exibirPorNumero(numero).toString());
		return new ResponseEntity<String>(op, Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Deleta uma conta utilizando o identificador 'numero'")
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ctDeletada = contaService.exibirPorNumero(numero);
		String cd = "Conta excluida com sucesso!\n" + ctDeletada.toString();
		contaService.deletarConta(numero);
		return new ResponseEntity<String>(cd, Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Limpa a lista de contas") 
	@DeleteMapping("/clear")
	public ResponseEntity<String> limparLista() {
		contaService.limparLista();
		String msg = "Contas excuidas com sucesso";
		return new ResponseEntity<String>(msg, Header(), HttpStatus.OK);
	}

}
