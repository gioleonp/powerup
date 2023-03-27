package com.pragma.plazoleta.infrastructure.security.filter;

import com.pragma.plazoleta.infrastructure.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");


        if (bearerToken != null && bearerToken.startsWith("Bearer")){
            String token = bearerToken.substring(7);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    tokenUtils.getAuthenticationToken(token);

            SecurityContextHolder.getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);

            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

        }
        filterChain.doFilter(request, response);
    }
}
