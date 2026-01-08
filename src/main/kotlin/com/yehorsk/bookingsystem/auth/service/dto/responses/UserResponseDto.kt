package com.yehorsk.bookingsystem.auth.service.dto.responses

import com.yehorsk.bookingsystem.auth.database.entity.UserRole

data class UserResponseDto(
    val id: Long,
    val email: String,
    val name: String,
    val role: UserRole
)
