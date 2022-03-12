package com.gabrielrossilopes.appmalote.utils;

public class ValidationUtils {

	public static boolean validaCPF(String cpf) {
			return cpf.length() == 11;
	}
	
	public static boolean validaCNPJ(String cpf) {
		return cpf.length() == 14;
	}
	
	public static boolean validaConta(String conta) {
		return conta.length() > 3;
	}
}
