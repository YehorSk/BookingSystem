package com.yehorsk.bookingsystem.auth.service.dto.responses

data class LoginResponseDto(
    val user: UserResponseDto,
    val token: String
)
