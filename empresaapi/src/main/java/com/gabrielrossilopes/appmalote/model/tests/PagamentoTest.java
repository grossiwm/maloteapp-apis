package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.exception.CnpjInvalidoException;
import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;

public class PagamentoTest {

	public static void main(String[] args) {

		Pagamento pagamento = new Pagamento();
		
		try {
			pagamento.setCnpjRecebedor("100122");
		} catch (CnpjInvalidoException e) {
			e.printStackTrace();
		}	
		
		try {
			pagamento.setCnpjRecebedor("10051151000122");
		} catch (CnpjInvalidoException e) {
			e.printStackTrace();
		}	
		
		System.out.println(pagamento);
		
		System.out.println(pagamento.getTipoTransacao());
	}

}
