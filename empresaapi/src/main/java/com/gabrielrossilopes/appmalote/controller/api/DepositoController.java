package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.model.dominio.Deposito;
import com.gabrielrossilopes.appmalote.model.dominio.Pagamento;
import com.gabrielrossilopes.appmalote.service.DepositoService;
import com.gabrielrossilopes.appmalote.service.PagamentoService;
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
        return ResponseEntity.ok(depositoService.getById(id));
    }

    @PostMapping("/incluir")
    public ResponseEntity<?> inclui(@RequestBody Deposito deposito) {
        depositoService.cria(deposito);

        return ResponseEntity.ok().build();
    }
}
