package com.gabrielrossilopes.appmalote.controller.api;

import com.gabrielrossilopes.appmalote.dto.UsuarioDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);
        if (usuarioOptional.isPresent())
            return ResponseEntity.ok().body(usuarioOptional.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Usuario usuario, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(201).body(usuarioService.cadastrarUsuario(usuario));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);

        if (usuarioOptional.isPresent()) {
            try {
                usuarioService.removeUsuario(usuarioOptional.get());
                return ResponseEntity.noContent().build();
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(418).build();
            }
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

    @GetMapping("/by-email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioByEmail(email);
        if (usuarioOptional.isPresent())
            return ResponseEntity.ok(usuarioOptional.get());
        return ResponseEntity.notFound().build();
    }
}
