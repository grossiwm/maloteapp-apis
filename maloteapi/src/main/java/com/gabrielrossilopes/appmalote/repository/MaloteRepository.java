package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Malote;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaloteRepository extends JpaRepository<Malote, Long> {

    @Query("from malote m where m.usuario.id = :usuarioId")
    List<Malote> findAll(Long usuarioId, Sort by);

    @Query("from malote m where m.empresa.id = :empresaId")
    List<Malote> findAllByEmpresa(Long empresaId, Sort by);

    List<Malote> findAll(Sort by);
}