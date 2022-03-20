package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findAll(Sort by);

    @Query("from pagamento p where p.malote.empresa.id = :empresaId")
    List<Pagamento> findAll(Sort by, Long empresaId);

    @Query("from pagamento p where p.malote.id = :maloteId")
    List<Pagamento> findAllByMalote(Sort by, Long maloteId);
}
