package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> busucaTodos() {
    	return usuarioRepository.findAll();
    }

    public void aceitarUsuario(long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setAceito(true);
        usuarioRepository.save(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}
