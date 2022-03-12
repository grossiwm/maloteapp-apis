package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import com.gabrielrossilopes.appmalote.model.dominio.Transacao;
import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;

import java.util.ArrayList;
import java.util.List;

public class TransacaoTest {

	public static void main(String[] args) {
		
		Deposito deposito = new Deposito();
		Transferencia transferencia = new Transferencia();
		Pagamento pagamento = new Pagamento();
		
		List<Transacao> transacoes = new ArrayList<>();
		transacoes.add(pagamento);
		transacoes.add(deposito);
		transacoes.add(transferencia);
	}

}
