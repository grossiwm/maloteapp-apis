package com.gabrielrossilopes.appmalote.controller;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(depositoService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        Optional<Deposito> depositoOptional = depositoService.getOptionalById(id);

        if (depositoOptional.isPresent()) {
            depositoService.remove(depositoOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(depositoService.getOptionalById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<?> inclui(@RequestBody Deposito deposito) {
        return ResponseEntity.ok(depositoService.cria(deposito));
    }

    @PatchMapping
    public ResponseEntity<?> altera(@RequestBody Deposito deposito) {
        return ResponseEntity.ok(depositoService.altera(deposito));
    }

    @GetMapping("/by-empresa/{empresaId}")
    public ResponseEntity<?> buscaPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(depositoService.getAllByEmpresaId(empresaId));
    }

    @GetMapping("/by-malote/{maloteId}")
    public ResponseEntity<?> buscaPorMalote(@PathVariable Long maloteId) {
        return ResponseEntity.ok(depositoService.getAllByMaloteId(maloteId));
    }
}
