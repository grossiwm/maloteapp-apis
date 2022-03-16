package com.gabrielrossilopes.appmalote.model.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Transacao")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	protected BigDecimal valor;

	protected TipoTransacao tipoTransacao;

	@ManyToOne
	@JoinColumn(name = "malote_id")
	@JsonBackReference(value = "transacao-malote")
	protected Malote malote;

	public Malote getMalote() {
		return malote;
	}

	public void setMalote(Malote malote) {
		this.malote = malote;
	}

	protected Transacao() {
	}

	public Transacao(BigDecimal valor, TipoTransacao tipoTransacao, Malote malote) {
		this.malote = malote;
		this.valor = valor;
		this.tipoTransacao = tipoTransacao;
	}

	public Transacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
//	@Override
//	public String toString() {
//		return new StringBuilder().append(";").append(status.name()).toString();
//
//	}
//

	
}
