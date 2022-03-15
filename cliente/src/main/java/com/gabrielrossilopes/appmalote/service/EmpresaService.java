package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.dto.EmpresaDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	
	public List<Empresa> buscaTodasOrdenado() {
		return empresaRepository.findAllByOrderByNome();
	}
	
	public Empresa buscaPorId(Long id) {
		return empresaRepository.getById(id);
	}

	public void criaEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa();

		empresa.setNome(empresaDTO.getNome());
		empresa.setCnpj(empresaDTO.getCnpj());

		empresaRepository.save(empresa);
	}

	public void alterarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = empresaRepository.findById(empresaDTO.getId()).get();
		empresa.setNome(empresaDTO.getNome());
		empresa.setCnpj(empresaDTO.getCnpj());

		empresaRepository.save(empresa);
	}

	public void removeEmpresa(Empresa empresa) throws DataIntegrityViolationException {
		empresaRepository.delete(empresa);
	}
}
