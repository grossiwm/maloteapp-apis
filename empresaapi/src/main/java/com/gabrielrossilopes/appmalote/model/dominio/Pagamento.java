package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.exception.CnpjInvalidoException;
import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;
import com.gabrielrossilopes.appmalote.utils.ValidationUtils;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "pagamento")
public class Pagamento extends Transacao {

	@Column(name = "cnpj_recebedor")
	private String cnpjRecebedor;

	public String getCnpjRecebedor() {
		return cnpjRecebedor;
	}
	public void setCnpjRecebedor(String cnpjRecebedor) throws CnpjInvalidoException {
		if (!ValidationUtils.validaCNPJ(cnpjRecebedor))
			throw new CnpjInvalidoException("cnpj " + cnpjRecebedor + " inválido");
		
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
