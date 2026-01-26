package com.yehorsk.bookingsystem.booking.service.dtos.responses

import com.yehorsk.bookingsystem.auth.database.entity.UserRole
import com.yehorsk.bookingsystem.common.type.UserId

data class UserSummaryDto(
    val id: UserId,
    val email: String,
    val name: String
)
