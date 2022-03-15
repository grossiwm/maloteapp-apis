package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.model.dominio.Malote;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.repository.MaloteRepository;
import com.gabrielrossilopes.appmalote.repository.UsuarioRepository;
import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MaloteService {

    @Autowired
    private MaloteRepository maloteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioLogadoSession usuarioLogadoSession;

    public Malote salvaMalote(Malote malote) {
        return maloteRepository.save(malote);
    }

    public Malote getById(Long id) {
        return maloteRepository.getById(id);
    }

    public List<Malote> buscaTodos() {

        if (usuarioLogadoSession.isAdmin())
            return maloteRepository.findAll().stream().sorted(Comparator.comparing(Malote::getData)).toList();

        Usuario usuario = usuarioRepository.getById(usuarioLogadoSession.getId());
        Empresa empresa = usuario.getEmpresa();
        return maloteRepository.findAll().stream().filter(d -> d.getEmpresa().equals(empresa))
                .sorted(Comparator.comparing(Malote::getData)).toList();

    }

    public void removeMalote(Malote malote) throws DataIntegrityViolationException {

        maloteRepository.delete(malote);
    }

}
