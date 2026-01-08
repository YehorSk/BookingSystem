package com.yehorsk.bookingsystem.auth

import com.yehorsk.bookingsystem.auth.service.AuthService
import com.yehorsk.bookingsystem.auth.service.dto.requests.RegisterUserDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.UserResponseDto
import com.yehorsk.bookingsystem.auth.utils.JwtUtil
import jakarta.validation.Valid
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterUserDto): UserResponseDto {
        return authService.register(request)
    }

}