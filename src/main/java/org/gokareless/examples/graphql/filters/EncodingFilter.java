package org.gokareless.examples.graphql.filters;

import com.google.common.base.Strings;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.core.annotation.Order;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

// @Order(102)
// @Component
public class EncodingFilter extends RequestContextFilter {

  public static final String ENCODING_HEADER = "X-Wellsmith-Html-Safe-Strings";

  @Override
  public void doFilterInternal(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println("Encoding filter request");
    // System.out.println("Encoding filter: Security enabled: " + SecurityContextHolder.getContext() != null &&
    //     SecurityContextHolder.getContext().getAuthentication() != null);
    HttpServletResponseWrapper responseWrapper = new StringResponseWrapper(response);
    filterChain.doFilter(request, responseWrapper);
    System.out.println("Encoding filter response");
//    if (isEncodingHeaderPresent(request) && isJsonType(response)) {
      encodeResponse(responseWrapper);
//    }
  }

  private void encodeResponse(HttpServletResponseWrapper responseWrapper) throws IOException {
    final String oldContent = responseWrapper.toString();
    final String newContent = EscapeUtil.escapeHtml(oldContent);
    ServletResponse response = responseWrapper.getResponse();
    response.setContentLength(newContent.length());
    response.getWriter().write(newContent);
  }



  private boolean isEncodingHeaderPresent(final HttpServletRequest request) {
    final String headerValue = request.getHeader(ENCODING_HEADER);
    return !Strings.isNullOrEmpty(headerValue);
  }

  private boolean isJsonType(HttpServletResponse response) {
    return Strings.nullToEmpty(response.getContentType())
        .contains("application/json");
  }

  static class StringResponseWrapper extends HttpServletResponseWrapper {
      private final StringWriter sw = new StringWriter();

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    StringResponseWrapper(HttpServletResponse response) {
      super(response);
    }

    @Override
      public PrintWriter getWriter() {
        return new PrintWriter(sw);
      }

      @Override
      public String toString() {
        return sw.toString();
      }
    }
}
