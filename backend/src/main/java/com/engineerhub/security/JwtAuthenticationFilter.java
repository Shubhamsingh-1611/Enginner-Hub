package com.engineerhub.security;

import com.engineerhub.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("JWT Filter -> path={}, authHeaderPresent={}, method={}", request.getRequestURI(), authHeader != null, request.getMethod());

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.debug("JWT Filter -> no Bearer token found for {}", request.getRequestURI());
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7).trim();
        logger.debug("JWT Filter -> token present, length={}", jwt.length());
        String username = jwtService.extractUsername(jwt);
        logger.debug("JWT Filter -> extracted username from token={}", username);

        // Authenticate only if user is not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    logger.debug("JWT Filter -> authentication set for user={}", username);
                } else {
                    logger.debug("JWT Filter -> token invalid for user={}", username);
                    SecurityContextHolder.clearContext();
                }
            } catch (Exception ex) {
                logger.warn("JWT Filter -> authentication failed for user={} error={}", username, ex.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

}