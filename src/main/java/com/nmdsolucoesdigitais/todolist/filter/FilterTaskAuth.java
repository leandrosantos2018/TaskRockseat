package com.nmdsolucoesdigitais.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nmdsolucoesdigitais.todolist.user.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
           
           IUserRepository repository;     
             
            // pegar autenticaçã 

            

            var autorization = request.getHeader("Authorization");
            var authEncoded = autorization.substring("basic".length()).trim();
            byte[] authDecod = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecod);
            
            String [] credenciasi = authString.split(":");     
            System.out.println(credenciasi[0]);
            System.out.println(credenciasi[1]);
            
            //validar o usuario

            //valida senha 

            //segue viagem 
        filterChain.doFilter(request, response);

    }

   
}