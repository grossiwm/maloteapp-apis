package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<Deposito> getAll() {
        return depositoRepository.findAll();
    }

    public void remove(Deposito deposito) {
        depositoRepository.delete(deposito);
    }

    public Optional<Deposito> getOptionalById(Long id) {
        return depositoRepository.findById(id);
    }

    public Deposito getById(Long id) {
        return depositoRepository.getById(id);
    }

    public Deposito cria(Deposito deposito) {
        return depositoRepository.save(deposito);
    }
}
