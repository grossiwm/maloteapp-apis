package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.model.dominio.Transacao;
import com.gabrielrossilopes.appmalote.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/transacao")
public class TransacaoController {


    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<?> listarTransacoes() {
        return ResponseEntity.ok(transacaoService.listarTransacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterTransacao(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.getById(id));
    }

    @GetMapping("/quantidade")
    public ResponseEntity<?> obterQuantidade() {
        return ResponseEntity.ok(transacaoService.getQuantidade());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        Optional<Transacao> transacaoOptional = transacaoService.getOptionalById(id);
        if (transacaoOptional.isPresent()) {
            transacaoService.deletar(transacaoOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/incluir")
    public ResponseEntity<?> inclui(@RequestBody Transacao transacao) {
        transacaoService.criar(transacao);

        return ResponseEntity.ok().build();
    }
}
