package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.model.dominio.*;

import java.util.List;

public class MaloteTest {

	public static void main(String[] args) {
		
		Empresa empresa = new Empresa();
		
		
		Malote malote = new Malote();
		malote.setEmpresa(empresa);
		
		System.out.println(malote);
		
		Transacao transacao1 = new Transferencia();
		Transacao transacao2 = new Pagamento();
		Transacao transacao3 = new Deposito();
		
		malote.addTransacoes(List.of(transacao1, transacao2, transacao3));
		
		malote.getTransacoes().stream().forEach(t->System.out.println(t.getClass().getName()));
	}

}
