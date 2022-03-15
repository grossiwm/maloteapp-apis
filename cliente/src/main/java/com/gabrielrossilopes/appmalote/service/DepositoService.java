package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.repository.DepositoRepository;
import com.gabrielrossilopes.appmalote.repository.UsuarioRepository;
import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private UsuarioLogadoSession usuarioLogadoSession;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Deposito> getAllDepositos() {
        if (usuarioLogadoSession.isAdmin())
            return depositoRepository.findAll().stream().sorted(Comparator.comparing(Deposito::getNomeBeneficiario)).toList();

        Usuario usuario = usuarioRepository.getById(usuarioLogadoSession.getId());
        Empresa empresa = usuario.getEmpresa();
        return depositoRepository.findAll().stream().filter(d -> d.getMalote().getEmpresa().equals(empresa))
                .sorted(Comparator.comparing(Deposito::getNomeBeneficiario)).toList();
    }

    public Deposito salvaDeposito(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    public Deposito alteraDeposito(Deposito depositoReq) {
        Long id = depositoReq.getId();
        depositoReq.setId(null);
        Deposito deposito = depositoRepository.getById(id);
        deposito.setNomeBeneficiario(depositoReq.getNomeBeneficiario());
        deposito.setCpfBeneficiario(depositoReq.getCpfBeneficiario());
        deposito.setValor(depositoReq.getValor());
        depositoRepository.save(deposito);
        return deposito;
    }

    public Deposito buscaPorId(Long id) {
        return depositoRepository.getById(id);
    }


    public void removeDeposito(Deposito deposito) {
        depositoRepository.delete(deposito);
    }
}
