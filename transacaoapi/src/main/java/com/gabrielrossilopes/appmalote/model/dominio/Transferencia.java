package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	public void setContaDestino(String contaDestino) {
			
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
