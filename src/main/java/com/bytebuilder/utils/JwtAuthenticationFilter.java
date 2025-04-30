package com.bytebuilder.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String requestURI = request.getRequestURI();
//
//
//        if (requestURI.equals("/user/signUp") || requestURI.equals("/user/viewGallery") || requestURI.equals("/user/logIn")||requestURI.equals("/user/viewBasedOnCategory")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String token = extractToken(request);
//        if (token != null) {
//            String email = jwtUtil.extractEmail(token);
//            if (email != null) {
//                UserDetails userDetails = new User(email, "", Collections.emptyList());
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String requestURI = request.getRequestURI();


    if (requestURI.equals("/user/signUp") ||
            requestURI.equals("/user/viewGallery") ||
            requestURI.equals("/user/logIn") ||
            requestURI.equals("/user/viewBasedOnCategory")) {

        filterChain.doFilter(request, response);
        return;
    }

    String token = extractToken(request);
    if (token != null) {
        try {
            String email = jwtUtil.extractEmail(token);
            if (email != null) {
                UserDetails userDetails = new User(email, "", Collections.emptyList());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {

            SecurityContextHolder.clearContext();
        }
    }

    filterChain.doFilter(request, response);
}


    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
