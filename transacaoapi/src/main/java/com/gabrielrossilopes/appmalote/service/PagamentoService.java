package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import com.gabrielrossilopes.appmalote.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"));
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

    public List<Pagamento> getAllByEmpresaId(Long id) {
        return pagamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"), id);
    }

    public List<Pagamento> getAllByMaloteId(Long id) {
        return pagamentoRepository.findAllByMalote(Sort.by(Sort.Direction.ASC, "valor"), id);
    }

    public Pagamento altera(Pagamento pagamento) {
        Optional<Pagamento> transferenciaOptional = pagamentoRepository.findById(pagamento.getId());
        return pagamentoRepository.save(atualizaPagamento(transferenciaOptional.get(), pagamento));
    }

    private Pagamento atualizaPagamento(Pagamento antigo, Pagamento novo) {
        antigo.setValor(novo.getValor());
        antigo.setCnpjRecebedor(novo.getCnpjRecebedor());
        return antigo;
    }
}
