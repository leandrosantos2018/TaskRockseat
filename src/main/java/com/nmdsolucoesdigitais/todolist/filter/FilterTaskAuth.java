package com.nmdsolucoesdigitais.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nmdsolucoesdigitais.todolist.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/Task/")) {

            // pegar autenticação

            var autorization = request.getHeader("Authorization");
            var authEncoded = autorization.substring("basic".length()).trim();
            byte[] authDecod = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecod);

            String[] credentials = authString.split(":");
            var username = credentials[0];
            var password = credentials[1];

            // validar o usuario
            var user = this.repository.findByUsername(username);

            if (user == null) {

                response.sendError(401);

            } else {

                // valida senha

                var result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (result.verified) {
                    request.setAttribute("idUser",user.getId());

                    // segue viagem
                    filterChain.doFilter(request, response);

                } else {
                    response.sendError(401);
                }

            }

        }else {

            filterChain.doFilter(request, response);

        }

    }

}