package com.yehorsk.bookingsystem.auth.config

import com.yehorsk.bookingsystem.auth.service.UserDetailsServiceImpl
import com.yehorsk.bookingsystem.auth.utils.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthTokenFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            if(jwtUtil.validateAccessToken(authHeader)) {
                val userId = jwtUtil.extractUserId(authHeader)
                val user = userDetailsService.loadUserByUsername(userId)
                val auth = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(request, response)
    }

}