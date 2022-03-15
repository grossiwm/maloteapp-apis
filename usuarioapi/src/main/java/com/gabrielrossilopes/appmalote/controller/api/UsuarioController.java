package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.dto.UsuarioDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(usuarioService.busucaTodos());
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(201).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);

        if (usuarioOptional.isPresent()) {
            usuarioService.removeUsuario(usuarioOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/validar")
    public ResponseEntity<?> validar(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.validar(usuarioDTO);
        if (usuarioOptional.isPresent())
            return ResponseEntity.ok(usuarioOptional.get());
        return ResponseEntity.status(403).build();

    }
}
