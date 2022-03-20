package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "pagamento")
public class Pagamento extends Transacao {

	@Column(name = "cnpj_recebedor")
	private String cnpjRecebedor;

	public String getCnpjRecebedor() {
		return cnpjRecebedor;
	}
	public void setCnpjRecebedor(String cnpjRecebedor) {
		this.cnpjRecebedor = cnpjRecebedor;
	}

	
	@Override
	public String toString() {
		return new StringBuilder().append(cnpjRecebedor).toString();
	}

	public Pagamento() {
		super(TipoTransacao.PAGAMENTO);
	}

	public Pagamento(String cnpjRecebedor, Malote malote, BigDecimal valor) {
		super(valor, TipoTransacao.PAGAMENTO, malote);
		this.cnpjRecebedor = cnpjRecebedor;
	}
}
