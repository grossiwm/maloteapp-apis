package com.gabrielrossilopes.appmalote.controller;

import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import com.gabrielrossilopes.appmalote.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(pagamentoService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.getOptionalById(id);

        if (pagamentoOptional.isPresent()) {
            pagamentoService.remove(pagamentoOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.getOptionalById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<?> inclui(@RequestBody Pagamento pagamento) {
        pagamentoService.cria(pagamento);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> altera(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.altera(pagamento));
    }

    @GetMapping("/by-empresa/{empresaId}")
    public ResponseEntity<?> buscaPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(pagamentoService.getAllByEmpresaId(empresaId));
    }

    @GetMapping("/by-malote/{maloteId}")
    public ResponseEntity<?> buscaPorMalote(@PathVariable Long maloteId) {
        return ResponseEntity.ok(pagamentoService.getAllByMaloteId(maloteId));
    }
}
