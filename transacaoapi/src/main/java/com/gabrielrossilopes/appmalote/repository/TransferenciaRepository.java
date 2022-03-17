package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAll(Sort by);

    @Query("from transferencia t where t.malote.empresa.id = :empresaId")
    List<Transferencia> findAll(Sort by, Long empresaId);
}
