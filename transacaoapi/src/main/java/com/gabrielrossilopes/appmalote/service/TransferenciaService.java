package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import com.gabrielrossilopes.appmalote.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<Transferencia> getAll() {
        return transferenciaRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"));
    }

    public List<Transferencia> getAllByEmpresaId(Long id) {
        return transferenciaRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"), id);
    }

    public List<Transferencia> getAllByMaloteId(Long id) {
        return transferenciaRepository.findAllByMalote(Sort.by(Sort.Direction.ASC, "valor"), id);
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

    public Transferencia altera(Transferencia transferencia) {
        Optional<Transferencia> transferenciaOptional = transferenciaRepository.findById(transferencia.getId());
        return transferenciaRepository.save(atualizaTransferencia(transferenciaOptional.get(), transferencia));
    }

    private Transferencia atualizaTransferencia(Transferencia antigo, Transferencia novo) {
        antigo.setValor(novo.getValor());
        antigo.setContaOrigem(novo.getContaOrigem());
        antigo.setContaDestino(novo.getContaDestino());
        return antigo;
    }
}
