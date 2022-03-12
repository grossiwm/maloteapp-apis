package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.exception.ContaInvalidaException;
import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;
import com.gabrielrossilopes.appmalote.utils.ValidationUtils;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="transferencia")
public class Transferencia extends Transacao {

	@Column(name = "conta_destino")
	private String contaDestino;
	@Column(name = "conta_origem")
	private String contaOrigem;

	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) throws ContaInvalidaException {
		if (!ValidationUtils.validaConta(contaDestino))
			throw new ContaInvalidaException("Conta " + contaDestino + " inválida");
			
		this.contaDestino = contaDestino;
	}
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	@Override
	public TipoTransacao getTipoTransacao() {
		return TipoTransacao.TRANSFERENCIA;
		
	}	
	
	@Override
	public String toString() {
		return new StringBuilder().append(contaDestino).append(";").append(contaOrigem).toString();
	}

	public Transferencia() {
	}

	public Transferencia(String contaDestino, String contaOrigem, Malote malote, BigDecimal valor) {
		super(valor, TipoTransacao.TRANSFERENCIA, malote);
		this.contaDestino = contaDestino;
		this.contaOrigem = contaOrigem;
	}
}
