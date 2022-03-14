package com.gabrielrossilopes.appmalote.model.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "malote")
public class Malote {
	
	
	
	public Malote(Empresa empresa, Usuario usuario) {
		this.usuario = usuario;
		this.empresa = empresa;
		this.data = LocalDateTime.now();
	}
	
	public Malote() {
		this.data = LocalDateTime.now();
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@OneToMany(mappedBy = "malote", targetEntity = Transacao.class)
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
