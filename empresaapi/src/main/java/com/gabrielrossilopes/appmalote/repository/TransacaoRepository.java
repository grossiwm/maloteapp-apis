package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
