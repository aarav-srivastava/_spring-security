package com.spring.springsecurity.config.security.filters;

import com.spring.springsecurity.config.security.authentication.CustomAuthentication;
import com.spring.springsecurity.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. create an authentication which is not yet authenticated.
        // 2. delegate the authentication object to the manager.
        // 3. get back the authentication from the manager.
        // 4. if the object is authenticated then send the request to the next filter in the chain.
        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication customAuthentication = new CustomAuthentication(false,key);
        var authentication = customAuthenticationManager.authenticate(customAuthentication);
        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response); //propagate to the next filter in the filter chain , only when authentication worked.
        }
    }
}
