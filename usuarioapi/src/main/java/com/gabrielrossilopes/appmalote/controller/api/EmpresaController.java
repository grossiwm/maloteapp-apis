package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.dto.EmpresaDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(empresaService.buscaTodasOrdenado());
    }

    @PostMapping
    ResponseEntity<?> incluir(@RequestBody Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setCnpj(empresa.getCnpj());
        empresaService.criaEmpresa(empresaDTO);
        return ResponseEntity.status(201).body(null);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaService.buscaPorId(id);

        if (empresaOptional.isPresent()) {
            empresaService.removeEmpresa(empresaOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
