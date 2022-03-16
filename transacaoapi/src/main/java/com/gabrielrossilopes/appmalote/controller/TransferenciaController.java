package com.gabrielrossilopes.appmalote.controller;

import com.gabrielrossilopes.appmalote.model.dominio.Transferencia;
import com.gabrielrossilopes.appmalote.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(transferenciaService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        Optional<Transferencia> transferenciaOptional = transferenciaService.getOptionalById(id);

        if (transferenciaOptional.isPresent()) {
            transferenciaService.remove(transferenciaOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transferenciaService.getById(id));
    }

    @PostMapping("/incluir")
    public ResponseEntity<?> inclui(@RequestBody Transferencia transferencia) {
        transferenciaService.cria(transferencia);

        return ResponseEntity.ok().build();
    }
}
