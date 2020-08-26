package com.serratec.techbank1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.techbank1.exception.NumeroNaoEncontradoException;
import com.serratec.techbank1.exception.OperacaoInvalidaException;
import com.serratec.techbank1.exception.ContaNullException;
import com.serratec.techbank1.exception.ContaRepetidaException;
import com.serratec.techbank1.exception.NomeInvalidoException;
import com.serratec.techbank1.exception.NumeroInvalidoException;
import com.serratec.techbank1.exception.SaldoInvalidoException;
import com.serratec.techbank1.exception.ValorInvalidoException;
import com.serratec.techbank1.exception.ValorOperacaoException;
import com.serratec.techbank1.model.Conta;
import com.serratec.techbank1.model.Operacao;
import com.serratec.techbank1.model.Tipo;

@Service
public class ContaService {

	@Autowired
	Conta conta;

	private List<Conta> listaContas;

	public ContaService() {
		listaContas = new ArrayList<Conta>();
		listaContas.add(new Conta(1, "Victor", 5000.00, false));
		listaContas.add(new Conta(2, "Lais", 4000.00, true));
		listaContas.add(new Conta(3, "Priscila", 5000.00, true));
		listaContas.add(new Conta(4, "Evodio", 7000.00, false));
		listaContas.add(new Conta(5, "Carlos", 5000.00, true));

	}

	public List<Conta> listarContas() {
		return listaContas;
	}

	public void limparLista() {
		listaContas.clear();
	}

	public Integer contarLista() {
		return listaContas.size();
	}

	public List<Conta> exibirContaAtiva(boolean active) {
		List<Conta> listaCt = new ArrayList<Conta>();
		listaContas.forEach(ct -> {
			if (ct.isAtiva() == active) {
				listaCt.add(ct);
			}
		});
		return listaCt;
	}

	public Conta exibirPorNumero(Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		verificarNumeroValido(numero);
		for (Conta conta : listaContas) {
			if (conta.getNumero() == numero) {
				this.conta = conta;
				return conta;
			}
		}

		if (!listaContas.contains(this.conta.getNumero())) {
			return numeroNaoEncontrado(true);
		}
		;
		return this.conta;
	}

	public Conta adicionarConta(Conta conta) throws ContaRepetidaException, ContaNullException, NumeroInvalidoException,
			SaldoInvalidoException, NomeInvalidoException {
		if (conta.getNumero() == null || conta.getTitular() == null) {
			throw new ContaNullException(conta);
		}
		validarNome(conta.getTitular());
		verificarNumeroValido(conta.getNumero());
		verificarNumeroRepetido(conta.getNumero());

		if (conta.getSaldo() < 0) {
			throw new SaldoInvalidoException(conta.getSaldo());
		}
		if (conta.getSaldo() == null) {
			conta.setSaldo(0.0);
		}
		listaContas.add(conta);
		return conta;
	}

	public Conta atualizarConta(Conta ct)
			throws NumeroInvalidoException, NumeroNaoEncontradoException, SaldoInvalidoException {
		conta = exibirPorNumero(ct.getNumero());
		if (ct.getSaldo() < 0) {
			throw new SaldoInvalidoException(ct.getSaldo());
		}
		if (ct.getTitular() == null || ct.getTitular().isBlank()) {
			ct.setTitular(conta.getTitular());
		}
		if (ct.getSaldo() == null | ct.getSaldo().isNaN()) {
			ct.setSaldo(this.conta.getSaldo());
		}
		listaContas.set(listaContas.indexOf(conta), ct);
		return this.conta;
	}

	public Conta deletarConta(Integer numero) throws NumeroInvalidoException, NumeroNaoEncontradoException {
		Conta ct = exibirPorNumero(numero);
		listaContas.remove(ct);
		return ct;
	}


//___________________________OPERACOES________________________________________________________________

	@Autowired
	Operacao operacao;

	public Conta operacao(Integer numero, Double valor, String tipo)
			throws NumeroInvalidoException, NumeroNaoEncontradoException, SaldoInvalidoException,
			ValorInvalidoException, ValorOperacaoException, OperacaoInvalidaException {
		validarValor(valor);
		validarOperacao(tipo);
		
		operacao.setTipo(Tipo.valueOf(tipo.toUpperCase()));

		Conta ct = exibirPorNumero(numero);

		if (operacao.getTipo() == Tipo.CREDITO) {
			if (valor < 50) {
				throw new ValorOperacaoException(valor);
			}
			operacao.creditar(ct, valor);
			return ct;
		}

		if (operacao.getTipo() == Tipo.DEBITO && ct.getSaldo() > valor) {
			operacao.debitar(ct, valor);
			return ct;
		} else {
			throw new SaldoInvalidoException(ct.getSaldo());
		}

	}



//____________________________EXCEPTIONS_______________________________________________________________________	



private void validarOperacao(String tipo) throws OperacaoInvalidaException {
	if (!tipo.equalsIgnoreCase("credito") || tipo.equals("debito")) {
		throw new OperacaoInvalidaException();
	}
	;
}

private void validarValor(Double valor) throws ValorInvalidoException {
	if (valor == null || valor < 3) {
		throw new ValorInvalidoException(valor);
	}
}

private Conta numeroNaoEncontrado(boolean naoEncontrado)
		throws NumeroInvalidoException, NumeroNaoEncontradoException {
	if (naoEncontrado == true) {
		throw new NumeroNaoEncontradoException();
	}
	return null;
	
}

private void verificarNumeroValido(Integer numero) throws NumeroInvalidoException {
	if (numero <= 0 || numero == null) {
		throw new NumeroInvalidoException(numero);
	}
}

private void validarNome(String nome) throws NomeInvalidoException {
	if (nome.isBlank()) {
		throw new NomeInvalidoException();
	}
}

private void verificarNumeroRepetido(Integer numero) throws ContaRepetidaException {
	for (Conta conta : listaContas) {
		Boolean numeroInvalido = conta.getNumero().equals(numero);
		if (numeroInvalido) {
			throw new ContaRepetidaException(numero);
			
		}
	}
}}