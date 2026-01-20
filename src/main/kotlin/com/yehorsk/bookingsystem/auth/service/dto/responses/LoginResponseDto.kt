package com.yehorsk.bookingsystem.auth.service.dto.responses


data class AccessTokens(
    val accessToken: String,
    val refreshToken: String
)

data class LoginResponseDto(
    val user: UserResponseDto,
    val token: AccessTokens
)
