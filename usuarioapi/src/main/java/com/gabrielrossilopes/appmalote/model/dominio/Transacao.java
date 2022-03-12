package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;

import javax.persistence.*;

@Entity
@Table(name = "transacao")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
