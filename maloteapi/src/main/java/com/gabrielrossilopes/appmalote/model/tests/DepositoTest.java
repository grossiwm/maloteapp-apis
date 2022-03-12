package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.exception.CpfInvalidoException;
import com.gabrielrossilopes.appmalote.model.dominio.Deposito;

public class DepositoTest {
	
	public static void main(String[] args) {
		Deposito deposito = new Deposito();
		
		try {
			deposito.setCpfBeneficiario("501135");
		} catch (CpfInvalidoException e) {
			e.printStackTrace();
		}
		
		try {
			deposito.setCpfBeneficiario("50112122035");
		} catch (CpfInvalidoException e) {
			e.printStackTrace();
		}
		
		deposito.setNomeBeneficiario("Fulano");
		
		System.out.println(deposito);
		
		System.out.println(deposito.getTipoTransacao());
	}

}
