package com.serratec.techbank1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
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

<<<<<<< HEAD
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/conta")
@Api(value="REST API TechBank")
=======
@RestController
@RequestMapping("/conta")
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
public class ContaController {

	@Autowired
	ContaService contaService;

	@Autowired
	AboutUsConfig aboutUsConfig;
<<<<<<< HEAD
	
	@ApiOperation(value="Retorna AboutUs, lista de informações sobre o sistema")
=======

>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@GetMapping("/about-us")
	public ResponseEntity<AboutUsConfig> aboutUs() {
		return new ResponseEntity<AboutUsConfig>(aboutUsConfig, contaService.Header(), HttpStatus.ACCEPTED);
	}

<<<<<<< HEAD
	@ApiOperation(value="Retorna lista com todas as contas no sistema")
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@GetMapping
	public ResponseEntity<List<Conta>> listarContas() {
		return new ResponseEntity<List<Conta>>(contaService.listarContas(), contaService.Header(), HttpStatus.OK);
	}

<<<<<<< HEAD
	@ApiOperation(value="Retorna quantidade total de contas na lista")
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@GetMapping("/size")
	public ResponseEntity<String> contarLista() {
		String tamanhoLista = "Contas no sistema: " + contaService.contarLista().toString();
		return new ResponseEntity<String>(tamanhoLista, contaService.Header(), HttpStatus.OK);
	}

<<<<<<< HEAD
	@ApiOperation(value="Retorna conta pelo identificador 'numero' ,nao aceita numeros negativos ou 0")
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		return new ResponseEntity<Conta>(contaService.exibirPorNumero(numero), contaService.Header(), HttpStatus.OK);
	}

	@ApiOperation(value="Adiciona uma conta no sistema, nao aceita valores nulos, negativos ou numeros de conta ja existentes")
=======
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> exibePorNumero(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		// contaService.exibirPorNumero(numero);
		return new ResponseEntity<Conta>(contaService.exibirPorNumero(numero), contaService.Header(), HttpStatus.OK);
	}

>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@PostMapping
	public ResponseEntity<Conta> adicionarConta(Conta conta)
			throws ContaRepetidaException, ContaNullException, NumeroInvalidoException, SaldoInvalidoException {
		return new ResponseEntity<Conta>(contaService.adicionarConta(conta), contaService.Header(), HttpStatus.CREATED);
	}

<<<<<<< HEAD
	@ApiOperation(value="Atualiza uma conta utilizando o identificador 'numero', caso todos os dados nao sejam inseridos, manten-se os valores anteriores")
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@PutMapping
	public ResponseEntity<Conta> atualizarConta(Conta conta)
			throws NumeroInvalidoException, NumeroNaoEncontradoException, SaldoInvalidoException {
		contaService.atualizarConta(conta);
		return new ResponseEntity<Conta>(conta, contaService.Header(), HttpStatus.OK);
	}

<<<<<<< HEAD
	@ApiOperation(value="Faz uma transacao no banco, aceitando transacoes do tipo Credido, e debito use a rota")
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@PutMapping("/{numero}/{tipo}={valor}")
	public ResponseEntity<String> operacao(@PathVariable Integer numero, @PathVariable Double valor,
			@PathVariable String tipo) throws NumeroInvalidoException, NumeroNaoEncontradoException,
			SaldoInvalidoException, ValorInvalidoException, ValorOperacaoException {

		contaService.operacao(numero, valor, tipo);
		String op = String.format("Tipo de operacao:%s\nValor da operacao:R$%.2f\n%s", tipo.toUpperCase(), valor,
				contaService.exibirPorNumero(numero).toString());
		return new ResponseEntity<String>(op, contaService.Header(), HttpStatus.OK);
	}

<<<<<<< HEAD
	@ApiOperation(value="Deleta uma conta utilizando o identificador 'numero'")
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deletarConta(@PathVariable Integer numero)
			throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ctDeletada = contaService.exibirPorNumero(numero);
		String cd = "Conta excluida com sucesso!\n" + ctDeletada.toString();
		contaService.deletarConta(numero);
		return new ResponseEntity<String>(cd, contaService.Header(), HttpStatus.OK);
	}

<<<<<<< HEAD
	@ApiOperation(value="Limpa a lista de  contas") 
=======
>>>>>>> 94d8d9bb5bceb0e36b88211e482bbf05c66f5e18
	@DeleteMapping("/clear")
	public ResponseEntity<String> limparLista() {
		contaService.limparLista();
		String msg = "Contas excuidas com sucesso";
		return new ResponseEntity<String>(msg, contaService.Header(), HttpStatus.OK);
	}

}
