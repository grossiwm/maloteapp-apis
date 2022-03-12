package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(depositoService.getAllDepositos());
    }
}
