package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="transferencia")
public class Transferencia extends Transacao {

	@Column(name = "conta_destino")
	private String contaDestino;
	@Column(name = "conta_origem")
	private String contaOrigem;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Malote getMalote() {
		return malote;
	}

	public void setMalote(Malote malote) {
		this.malote = malote;
	}

	public void setStatus(TransacaoStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "malote_id")
	private Malote malote;

	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino)  {
//		if (!ValidationUtils.validaConta(contaDestino))
//			throw new ContaInvalidaException("Conta " + contaDestino + " inválida");
			
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
		return new StringBuilder().append(contaDestino).append(";").append(contaOrigem).append(";R$ ").append(valor)
				.toString();
	}

}
