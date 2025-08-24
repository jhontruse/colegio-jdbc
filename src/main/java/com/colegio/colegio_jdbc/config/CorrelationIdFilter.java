package com.colegio.colegio_jdbc.config;

import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String HDR = "X-Correlation-Id";
    public static final String KEY = "corrId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String corr = request.getHeader(HDR);
        if (corr == null || corr.isBlank())
            corr = UUID.randomUUID().toString();
        MDC.put(KEY, corr);
        ((HttpServletResponse) request).setHeader(HDR, corr);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(KEY);
        }
    }

}
