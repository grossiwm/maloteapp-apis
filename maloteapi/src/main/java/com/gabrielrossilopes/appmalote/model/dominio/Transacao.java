package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;

public abstract class Transacao {

	protected TransacaoStatus status;
	

	public Transacao() {
		this.status = TransacaoStatus.PROCESSANDO;
	}


	public TransacaoStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(";").append(status.name()).toString();
		
	}
	
	public abstract TipoTransacao getTipoTransacao();
	
}
