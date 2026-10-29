package com.wildev.security;

import com.wildev.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null
                && authorizationHeader.startsWith("Bearer ")
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            String token = authorizationHeader.substring(7).trim();

            try {
                String email = jwtService.extractUsername(token);

                System.out.println("[JWT Filter] Email extraido del token: " + email);

                if (email != null && !email.isBlank()) {
                    UserDetails user =
                            userDetailsService.loadUserByUsername(email);

                    System.out.println("[JWT Filter] Usuario cargado: " + user.getUsername());
                    System.out.println("[JWT Filter] Roles: " + user.getAuthorities());
                    System.out.println("[JWT Filter] Usuario habilitado: " + user.isEnabled());

                    boolean isValid = jwtService.isTokenValid(token, user);
                    System.out.println("[JWT Filter] Token valido: " + isValid);

                    if (isValid) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        user.getAuthorities()
                                );

                        authentication.setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(request)
                        );

                        SecurityContextHolder.getContext()
                                .setAuthentication(authentication);

                        System.out.println("[JWT Filter] Autenticacion establecida para: " + email);
                    }
                }


            } catch (JwtException
                     | IllegalArgumentException
                     | UsernameNotFoundException exception) {

                System.err.println(
                        "[JWT Filter] Error validando JWT: "
                                + exception.getClass().getSimpleName()
                                + " - "
                                + exception.getMessage()
                );

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.clearContext();
                }
            }

        }

        filterChain.doFilter(request, response);
    }
}