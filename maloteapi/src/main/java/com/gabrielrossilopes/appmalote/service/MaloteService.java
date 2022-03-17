package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Malote;
import com.gabrielrossilopes.appmalote.repository.MaloteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaloteService {

    @Autowired
    private MaloteRepository maloteRepository;

    public Malote salvaMalote(Malote malote) {
        return maloteRepository.save(malote);
    }

    public Malote getById(Long id) {
        return maloteRepository.getById(id);
    }

    public Optional<Malote> getOptionalById(Long id) {
        return maloteRepository.findById(id);
    }

    public List<Malote> listar() {
        return maloteRepository.findAll(Sort.by(Sort.Direction.ASC, "data"));
    }

    public List<Malote> listarPorUsuario(Long usuarioId) {
        return maloteRepository.findAll(usuarioId, Sort.by(Sort.Direction.ASC, "data"));
    }

    public List<Malote> listarPorEmpresa(Long empresaId) {
        return maloteRepository.findAllByEmpresa(empresaId, Sort.by(Sort.Direction.ASC, "data"));
    }

    public void excluir(Malote malote) {
        maloteRepository.delete(malote);
    }

}
