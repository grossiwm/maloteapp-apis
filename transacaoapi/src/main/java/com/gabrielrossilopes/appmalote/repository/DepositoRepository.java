package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    List<Deposito> findAll(Sort by);

    @Query("from deposito d where d.malote.empresa.id = :empresaId")
    List<Deposito> findAll(Sort by, Long empresaId);

    @Query("from deposito d where d.malote.id = :maloteId")
    List<Deposito> findAllByMalote(Sort by, Long maloteId);

}
