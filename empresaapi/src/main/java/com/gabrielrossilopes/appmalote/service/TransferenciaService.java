package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import com.gabrielrossilopes.appmalote.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<Transferencia> getAll() {
        return transferenciaRepository.findAll();
    }

    public void remove(Transferencia transferencia) {
        transferenciaRepository.delete(transferencia);
    }

    public Optional<Transferencia> getOptionalById(Long id) {
        return transferenciaRepository.findById(id);
    }

    public Transferencia getById(Long id) {
        return transferenciaRepository.getById(id);
    }

    public Transferencia cria(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }
}
