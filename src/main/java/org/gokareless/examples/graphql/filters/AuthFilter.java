package org.gokareless.examples.graphql.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

// @Order(99)
// @Component
public class AuthFilter extends RequestContextFilter {

  @Override
  public void doFilterInternal(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println("Auth filter request");
    // System.out.println("Auth filter: Security enabled: " + SecurityContextHolder.getContext() != null &&
    //     SecurityContextHolder.getContext().getAuthentication() != null);
    filterChain.doFilter(request, response);
    System.out.println("Auth filter response");
  }

}
