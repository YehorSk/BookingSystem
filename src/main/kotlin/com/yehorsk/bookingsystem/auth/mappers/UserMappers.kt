package com.yehorsk.bookingsystem.auth.mappers

import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.auth.service.dto.responses.UserResponseDto

fun UserEntity.toUserResponseDto() = UserResponseDto(
    id = id!!,
    email = email,
    name = name,
    role = role,
)