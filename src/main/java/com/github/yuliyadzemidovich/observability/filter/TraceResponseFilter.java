package com.github.yuliyadzemidovich.observability.filter;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TraceResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Span span = Span.current();
        SpanContext ctx = span.getSpanContext();
        if (ctx.isValid()) {
            String traceId = ctx.getTraceId();
            String spanId = ctx.getSpanId();
            String flags = ctx.getTraceFlags().asHex();

            // W3C Trace Context: 00-<traceId>-<spanId>-<flags>
            String traceparent = String.format("00-%s-%s-%s", traceId, spanId, flags);
            response.setHeader("traceparent", traceparent);

            // an overhead but easier to copy between dev and qa
            response.setHeader("trace-id", traceId);
        }

        filterChain.doFilter(request, response);
    }
}
