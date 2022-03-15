package com.gabrielrossilopes.appmalote.filter;

import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class AdminFilter extends OncePerRequestFilter {

    @Autowired
    UsuarioLogadoSession usuarioLogado;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/admin") && (usuarioLogado.isNull() || !usuarioLogado.isAdmin())) {
            response.sendRedirect("/usuario/403");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
