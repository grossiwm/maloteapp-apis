package com.gabrielrossilopes.appmalote.dto;

import com.gabrielrossilopes.appmalote.model.dominio.Usuario;

public class UsuarioDTO {

	private Long id;
	
	private String email;
	
	private String senha;
	
	private Long empresa;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresaId) {
		this.empresa = empresaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static UsuarioDTO getUsuarioDTOdeUsuario(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setEmpresa(usuario.getEmpresa().getId());
		usuarioDTO.setSenha(usuario.getSenha());
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNome(usuario.getNome());

		return usuarioDTO;
	}
}
