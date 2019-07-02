package org.gokareless.examples.graphql;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppEncodingFilter extends RequestContextFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Pre-request.");
        System.out.println("SecurityContext: " + SecurityContextHolder.getContext());
        request.setAttribute("Encoding-enabled", SecurityContextHolder.getContext() != null);
        chain.doFilter(request, response);
        System.out.println("Post-request.");

    }
}
