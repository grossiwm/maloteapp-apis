package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.repository.TransferenciaRepository;
import com.gabrielrossilopes.appmalote.repository.UsuarioRepository;
import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private UsuarioLogadoSession usuarioLogadoSession;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Transferencia> getAllTransferencia() {
        if (usuarioLogadoSession.isAdmin())
            return transferenciaRepository.findAll().stream().sorted(Comparator.comparing(Transferencia::getContaOrigem)).toList();

        Usuario usuario = usuarioRepository.getById(usuarioLogadoSession.getId());
        Empresa empresa = usuario.getEmpresa();
        return transferenciaRepository.findAll().stream().filter(d -> d.getMalote().getEmpresa().equals(empresa))
                .sorted(Comparator.comparing(Transferencia::getContaOrigem)).toList();

    }

    public Transferencia buscaPorId(Long id) {
        return transferenciaRepository.getById(id);
    }

    public Transferencia salvaTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public Transferencia alteraTransferencia(Transferencia transferenciaReq) {
        Long id = transferenciaReq.getId();
        transferenciaReq.setId(null);
        Transferencia transferencia = transferenciaRepository.getById(id);
        transferencia.setValor(transferenciaReq.getValor());
        transferencia.setContaDestino(transferenciaReq.getContaDestino());
        transferencia.setContaOrigem(transferenciaReq.getContaOrigem());
        return transferenciaRepository.save(transferencia);
    }

    public void removeTransferencia(Transferencia transferencia) {
        transferenciaRepository.delete(transferencia);
    }
}
