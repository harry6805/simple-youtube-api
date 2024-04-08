package com.inrech.jobs.youtube.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter("/filter-response-header/*")
public class AddResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(
                "Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader(
                "Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader(
                "Access-Control-Allow-Methods", "*");
        chain.doFilter(request, response);
    }
}
