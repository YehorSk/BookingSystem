package com.yehorsk.bookingsystem.auth

import com.yehorsk.bookingsystem.auth.mappers.toUserResponseDto
import com.yehorsk.bookingsystem.auth.service.AuthService
import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.auth.service.dto.requests.LoginUserDto
import com.yehorsk.bookingsystem.auth.service.dto.requests.RegisterUserDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.LoginResponseDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.UserResponseDto
import com.yehorsk.bookingsystem.auth.utils.JwtUtil
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    fun user(@AuthenticationPrincipal principal: CustomUserDetails): UserResponseDto {
        return principal.user.toUserResponseDto()
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterUserDto): UserResponseDto {
        return authService.register(request)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginUserDto): LoginResponseDto {
        return authService.login(request)
    }

}