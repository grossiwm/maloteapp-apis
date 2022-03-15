package com.gabrielrossilopes.appmalote.dto;

import com.gabrielrossilopes.appmalote.model.dominio.Empresa;

public class EmpresaDTO {

    private Long id;

    private String nome;

    private String cnpj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static EmpresaDTO getEmpresaDTOdeEmpresa(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setCnpj(empresa.getCnpj());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setId(empresa.getId());
        return empresaDTO;
    }
}
