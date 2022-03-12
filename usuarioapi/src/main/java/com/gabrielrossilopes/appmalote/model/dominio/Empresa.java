package com.gabrielrossilopes.appmalote.model.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="empresa")
public class Empresa {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column
	private String cnpj;
	
    @Column
	private String nome;

	@JsonIgnore
    @Transient
	private List<Malote> malotes;

	@JsonIgnore
    @OneToMany
	@JoinColumn(name = "empresa_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Usuario> usuarios; 

	public Empresa() {
		malotes = new ArrayList<>();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Malote> getMalotes() {
		return new ArrayList<>(malotes);
	}

	public void addMalote(Malote malote) {
		malotes.add(malote);
	}
	
	public void addMalotes(List<Malote> malotes) {
		this.malotes.addAll(malotes);
	}

	public void setMalotes(List<Malote> malotes) {
		this.malotes = malotes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(id).append(";").append(nome).append(";").append(cnpj).toString();
		
	}

}
