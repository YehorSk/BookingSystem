package com.yehorsk.bookingsystem.auth.service.dto.responses

import com.yehorsk.bookingsystem.auth.database.entity.UserRole
import com.yehorsk.bookingsystem.common.type.UserId

data class UserResponseDto(
    val id: UserId,
    val email: String,
    val name: String,
    val role: UserRole
)
