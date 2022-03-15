package com.gabrielrossilopes.appmalote.security;

import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.service.UsuarioService;
import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioLogadoSession usuarioLogado;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Optional<Usuario> usuarioOp = usuarioService.getUsuarioByEmail(email);

        if (usuarioOp.isEmpty())
            throw new UsernameNotFoundException("Login e/ou Senha inválidos.");

        Usuario usuario = usuarioOp.get();

        if (!usuario.getSenha().equals(senha))
            throw new BadCredentialsException("Login e/ou Senha inválidos.");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));

        User springSecurityUser = new User(email, "", grantedAuthorities);

        Authentication auth = new UsernamePasswordAuthenticationToken(springSecurityUser, senha, springSecurityUser.getAuthorities());

        usuarioLogado.setId(usuario.getId());
        usuarioLogado.setEmail(usuario.getEmail());
        usuarioLogado.setAdmin(usuario.isAdmin());
        usuarioLogado.setAceito(usuario.isAceito());
        usuarioLogado.setNome(usuario.getNome());
        return auth;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
