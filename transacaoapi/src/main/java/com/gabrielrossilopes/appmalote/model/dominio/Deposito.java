package com.gabrielrossilopes.appmalote.model.dominio;

import com.gabrielrossilopes.appmalote.exception.CpfInvalidoException;
import com.gabrielrossilopes.appmalote.model.enums.TipoTransacao;
import com.gabrielrossilopes.appmalote.model.enums.TransacaoStatus;
import com.gabrielrossilopes.appmalote.utils.ValidationUtils;

import javax.persistence.*;

@Entity(name = "deposito")
public class Deposito extends Transacao {

	@Column(name = "cpf_beneficiario")
	private String cpfBeneficiario;

	@Column(name = "nome_beneficiario")
	private String nomeBeneficiario;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public Malote getMalote() {
		return malote;
	}

	public void setMalote(Malote malote) {
		this.malote = malote;
	}

	@ManyToOne
	@JoinColumn(name = "malote_id")
	private Malote malote;
	
	public Deposito() {
		super();

	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setStatus(TransacaoStatus status) {
		this.status = status;
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
