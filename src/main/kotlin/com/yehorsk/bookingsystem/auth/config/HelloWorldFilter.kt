package com.yehorsk.bookingsystem.auth.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

// For testing purposes

@Component
class HelloWorldFilter
    : OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if(!request.headerNames.toList().contains("x-robot-secret")){
            filterChain.doFilter(request, response)
            return;
        }
        if(!request.getHeader("x-robot-secret").equals("test")){
            response.status = HttpStatus.FORBIDDEN.value()
            response.characterEncoding = "UTF-8"
            response.setHeader("Content-Type", "text/plain;charset=UTF-8")
            response.writer.write("You are not allowed")
            return;
        }
        filterChain.doFilter(request, response)
    }

}