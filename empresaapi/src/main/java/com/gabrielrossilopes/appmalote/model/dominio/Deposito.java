package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.exception.CpfInvalidoException;
import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;
import com.gabrielrossilopes.appmalote.utils.ValidationUtils;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "deposito")
public class Deposito extends Transacao {

	@Column(name = "cpf_beneficiario")
	private String cpfBeneficiario;

	@Column(name = "nome_beneficiario")
	private String nomeBeneficiario;

	
	public Deposito() {
		super();

	}

	public Deposito(String cpfBeneficiario, String nomeBeneficiario, Malote malote, BigDecimal valor) {
		super(valor, TipoTransacao.DEPOSITO, malote);
		this.cpfBeneficiario = cpfBeneficiario;
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public String getCpfBeneficiario() {
		return cpfBeneficiario;
	}
	public void setCpfBeneficiario(String cpfBeneficiario) throws CpfInvalidoException {
		if (!ValidationUtils.validaCPF(cpfBeneficiario))
			throw new CpfInvalidoException("Cpf " + cpfBeneficiario + " inválido");
		
		this.cpfBeneficiario = cpfBeneficiario;
	}
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	@Override
	public TipoTransacao getTipoTransacao() {
		return TipoTransacao.DEPOSITO;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(cpfBeneficiario).append(";").append(nomeBeneficiario).toString();
	}

}
