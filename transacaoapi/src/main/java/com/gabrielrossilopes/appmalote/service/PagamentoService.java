package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import com.gabrielrossilopes.appmalote.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    public void remove(Pagamento pagamento) {
        pagamentoRepository.delete(pagamento);
    }

    public Optional<Pagamento> getOptionalById(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento getById(Long id) {
        return pagamentoRepository.getById(id);
    }

    public Pagamento cria(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }
}
