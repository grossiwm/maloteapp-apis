package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.model.dominio.Malote;

import java.util.List;

public class EmpresaTest {
	
	public static void main(String[] args) {
		Empresa empresa = new Empresa();
		
		Malote malote1 = new Malote();
		Malote malote2 = new Malote();
		Malote malote3 = new Malote();
		
		empresa.addMalotes(List.of(malote1, malote2, malote3));
		
		
		System.out.println(empresa.getMalotes());
	}

	
}
