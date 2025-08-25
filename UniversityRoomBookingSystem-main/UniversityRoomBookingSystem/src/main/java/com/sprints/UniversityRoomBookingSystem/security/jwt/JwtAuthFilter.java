package com.sprints.UniversityRoomBookingSystem.security.jwt;

import com.sprints.UniversityRoomBookingSystem.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        try {
            // Only check JWT if header starts with "Bearer "
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwtToken = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwtToken);
                System.out.println("JWT username extracted: " + username);
            }

            // Set authentication if username is present and not already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                boolean validToken = jwtUtil.validateToken(jwtToken, userDetails.getUsername());
                System.out.println("JWT token valid: " + validToken);
                System.out.println("Authorities: " + userDetails.getAuthorities());

                if (validToken) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (Exception e) {
            logger.warn("Cannot authenticate user: " + e.getMessage());
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Skip filtering for /api/auth endpoints
        return path.startsWith("/api/auth");
    }
}
