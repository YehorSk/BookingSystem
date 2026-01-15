package com.yehorsk.bookingsystem.auth.service.dto.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginUserDto(
    @field:NotBlank("Email is required")
    @field:Email
    val email: String,
    @field:NotBlank("Password is required")
    val password: String
)
