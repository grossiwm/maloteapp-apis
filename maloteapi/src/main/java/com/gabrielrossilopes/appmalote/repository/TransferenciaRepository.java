package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
