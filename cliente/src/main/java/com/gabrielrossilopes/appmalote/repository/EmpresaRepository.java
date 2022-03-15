package com.gabrielrossilopes.appmalote.repository;

import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Optional<Empresa> findById(Long id);

	List<Empresa> findAllByOrderByNome();
}
