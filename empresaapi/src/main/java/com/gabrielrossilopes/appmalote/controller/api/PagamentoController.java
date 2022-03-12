package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(pagamentoService.getAllPagamentos());
    }
}
