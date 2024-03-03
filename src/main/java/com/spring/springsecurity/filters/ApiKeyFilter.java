package com.spring.springsecurity.filters;

import com.spring.springsecurity.authenitcationManagers.CustomAuthenticationManager;
import com.spring.springsecurity.authentications.ApiKeyAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String key;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);
        String requestKey = request.getHeader("key");
        if(requestKey == null || "null".equals(requestKey)){
            filterChain.doFilter(request,response);
        }
        ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(requestKey);
        try{
            Authentication authentication = manager.authenticate(apiKeyAuthentication);
            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request,response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
