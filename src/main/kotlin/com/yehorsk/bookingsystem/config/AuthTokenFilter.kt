package com.yehorsk.bookingsystem.config

import com.yehorsk.bookingsystem.auth.utils.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

//class AuthTokenFilter(
//    private val jwtUtil: JwtUtil
//) : OncePerRequestFilter() {
//
//    override fun doFilterInternal(
//        request: HttpServletRequest?,
//        response: HttpServletResponse?,
//        filterChain: FilterChain?
//    ) {
//        TODO("Not yet implemented")
//    }
//
//}