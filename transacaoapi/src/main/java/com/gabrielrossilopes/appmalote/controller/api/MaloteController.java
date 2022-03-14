package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.model.dominio.Malote;
import com.gabrielrossilopes.appmalote.service.MaloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/malote")
public class MaloteController {

    @Autowired
    private MaloteService maloteService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(maloteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(maloteService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Malote> maloteOptional = maloteService.getOptionalById(id);
        if (maloteOptional.isEmpty())
            return ResponseEntity.notFound().build();

        maloteService.excluir(maloteOptional.get());
        return ResponseEntity.noContent().build();


    }

}
