package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.dto.EmpresaDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Empresa> empresaOptional = empresaService.buscaPorId(id);
        if (empresaOptional.isPresent())
            return ResponseEntity.ok(empresaOptional.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<?> incluir(@RequestBody EmpresaDTO empresa) {
        empresaService.criaEmpresa(empresa);
        return ResponseEntity.status(201).body(null);
    }

    @PatchMapping
    ResponseEntity<?> alterar(@RequestBody EmpresaDTO empresa) {
        empresaService.alterarEmpresa(empresa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaService.buscaPorId(id);

        if (empresaOptional.isPresent()) {
            try {
                empresaService.removeEmpresa(empresaOptional.get());
                return ResponseEntity.noContent().build();
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(418).build();
            }

        }

        return ResponseEntity.notFound().build();
    }
}
