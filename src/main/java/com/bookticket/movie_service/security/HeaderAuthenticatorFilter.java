package com.bookticket.movie_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HeaderAuthenticatorFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String id = request.getHeader("X-User-Id");
        String roles = request.getHeader("X-User-Roles");
        // TO DO - Add X-User-Name , first add it in gateway

        if (id != null && roles != null && !roles.isEmpty()) {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(","))
                    .map(role -> new SimpleGrantedAuthority(role.trim()))
                    .toList();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
