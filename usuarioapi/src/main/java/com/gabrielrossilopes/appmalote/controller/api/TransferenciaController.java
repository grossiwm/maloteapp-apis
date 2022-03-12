package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(transferenciaService.getAllTransferencia());
    }
}
