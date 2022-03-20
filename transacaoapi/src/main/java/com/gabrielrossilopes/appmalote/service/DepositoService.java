package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<Deposito> getAll() {
        return depositoRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"));
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

    public List<Deposito> getAllByEmpresaId(Long id) {
        return depositoRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"), id);
    }

    public List<Deposito> getAllByMaloteId(Long id) {
        return depositoRepository.findAllByMalote(Sort.by(Sort.Direction.ASC, "valor"), id);
    }


    public Deposito altera(Deposito transferencia) {
        Optional<Deposito> transferenciaOptional = depositoRepository.findById(transferencia.getId());
        return depositoRepository.save(atualizaDeposito(transferenciaOptional.get(), transferencia));
    }

    private Deposito atualizaDeposito(Deposito antigo, Deposito novo) {
        antigo.setValor(novo.getValor());
        antigo.setCpfBeneficiario(novo.getCpfBeneficiario());
        antigo.setNomeBeneficiario(novo.getNomeBeneficiario());
        return antigo;
    }
}
