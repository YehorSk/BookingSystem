package com.yehorsk.bookingsystem.auth.service.dto.requests

import com.yehorsk.bookingsystem.auth.service.validation.PasswordMatches
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@PasswordMatches
data class RegisterUserDto(
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:NotBlank(message = "Email is required")
    @field:Email
    val email: String,
    @field:NotBlank(message = "Password is required")
    val password: String,
    @field:NotBlank(message = "Password confirmation is required")
    val passwordConfirm: String,
)
