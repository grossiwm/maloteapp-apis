package com.gabrielrossilopes.appmalote.model.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "malote")
public class Malote {
	
	
	
	public Malote(Empresa empresa, LocalDateTime data) {
		this.empresa = empresa;
		this.transacoes = new ArrayList<Transacao>();
		this.data = LocalDateTime.now();
	}
	
	public Malote() {
		this.transacoes = new ArrayList<Transacao>();
		this.data = LocalDateTime.now();
	}


	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	@OneToMany
	@JoinColumn(name = "malote_id")
	private List<Deposito> depositos;


	@OneToMany
	@JoinColumn(name = "malote_id")
	private List<Pagamento> pagamentos;

	public List<Deposito> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Deposito> depositos) {
		this.depositos = depositos;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	@OneToMany
	@JoinColumn(name = "malote_id")
	private List<Transferencia> transferencias;

	@Transient
	private List<Transacao> transacoes;

	@Column
	private LocalDateTime data;
	
	public LocalDateTime getData() {
		return data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<Transacao> getTransacoes() {
		return new ArrayList<>(transacoes);
	}
	public void addTransacoes(List<Transacao> transacoes) {
		this.transacoes.addAll(transacoes);
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return String.format("%s; %s; %s", id, empresa, formato.format(data));
	}

}
