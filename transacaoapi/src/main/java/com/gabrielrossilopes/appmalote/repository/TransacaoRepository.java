package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Transacao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAll(Sort by);

    @Query("from transacao t where t.malote.empresa.id = :empresaId")
    List<Transacao> findAll(Sort by, Long empresaId);

    @Query("from transacao t where t.malote.id = :maloteId")
    List<Transacao> findAllByMalote(Sort by, Long maloteId);
}
