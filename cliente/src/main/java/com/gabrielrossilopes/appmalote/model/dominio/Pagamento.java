package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "pagamento")
public class Pagamento extends Transacao {

	@Column(name = "cnpj_recebedor")
	private String cnpjRecebedor;

	@ManyToOne
	@JoinColumn(name = "malote_id")
	private Malote malote;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Malote getMalote() {
		return malote;
	}

	public void setMalote(Malote malote) {
		this.malote = malote;
	}

	public void setStatus(TransacaoStatus status) {
		this.status = status;
	}
	
	public String getCnpjRecebedor() {
		return cnpjRecebedor;
	}
	public void setCnpjRecebedor(String cnpjRecebedor) {
//		if (!ValidationUtils.validaCNPJ(cnpjRecebedor))
//			throw new CnpjInvalidoException("cnpj " + cnpjRecebedor + " inválido");
		
		this.cnpjRecebedor = cnpjRecebedor;
	}
	@Override
	public TipoTransacao getTipoTransacao() {
		return TipoTransacao.PAGAMENTO;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(cnpjRecebedor).append(";R$ ").append(valor)
				.toString();
	}

}
