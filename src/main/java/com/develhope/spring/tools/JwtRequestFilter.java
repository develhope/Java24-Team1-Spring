package com.develhope.spring.tools;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.UserDetailsImpl;
import com.develhope.spring.services.UserDetailsServiceImpl;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import com.sun.istack.NotNull;
import io.jsonwebtoken.ExpiredJwtException;
import io.opencensus.internal.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/*
QUESTO è IL FILTRO PER OGNI RICHIESTA HTTP. UNA RICHIESTA VIENE PRIMA
VALIDATA DA QUESTO FILTRO ESTRAENDO IL TOKEN, SE PRESENTE
 */


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null && jwtUtil.validateToken(jwt)) {
                String username = jwtUtil.getUsername(jwt);
                UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }
        catch (Exception e) {
            System.out.println("ERRORE CREAZIONE AUTENTICAZIONE");
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
         String header = request.getHeader("Authorization");
         if(header != null && header.startsWith("Bearer ")) {
             return (header.substring(7));
         }
        return null;
    }
}
