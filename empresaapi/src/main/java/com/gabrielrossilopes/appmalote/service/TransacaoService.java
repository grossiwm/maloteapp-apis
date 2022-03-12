package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Transacao;
import com.gabrielrossilopes.appmalote.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    public Long getQuantidade() {
        return transacaoRepository.count();
    }

    public Transacao getById(Long id) {
        return transacaoRepository.getById(id);
    }

    public Optional<Transacao> getOptionalById(Long id) {
        return transacaoRepository.findById(id);
    }

    public void deletar(Transacao transacao) {
        transacaoRepository.delete(transacao);
    }
}
