package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.exception.ContaInvalidaException;
import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;

public class TransferenciaTest {

	public static void main(String[] args) {

		Transferencia transferencia = new Transferencia();
		
		try {
			transferencia.setContaDestino("7");
		} catch (ContaInvalidaException e) {
			e.printStackTrace();
		}
		
		try {
			transferencia.setContaDestino("72662-x");
		} catch (ContaInvalidaException e) {
			e.printStackTrace();
		}
		
		transferencia.setContaOrigem("123132-x");
		
		System.out.println(transferencia);
		
		System.out.println(transferencia.getTipoTransacao());
	}

}
