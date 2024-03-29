package org.gokareless.examples.graphql.filters;

import org.gokareless.examples.graphql.dto.Device;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
public class AppDeviceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Pre-request.");
        String userAgent = ((HttpServletRequest) request).getHeader("User-Agent");
        System.out.println("User-Agent: " + userAgent);
        request.setAttribute("Device", userAgent.contains("Mobile")
                ? Device.MOBILE
                : Device.DESKTOP
        );
        chain.doFilter(request, response);
        System.out.println("Post-request.");

    }

    @Override
    public void destroy() {}
}
