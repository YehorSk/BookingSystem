package com.yehorsk.bookingsystem.room.service.dto.response

import java.math.BigDecimal

data class RoomResponseDto(
    val id: Long,
    val number: String,
    val capacity: Int,
    val isActive: Boolean,
    val price: BigDecimal
)
