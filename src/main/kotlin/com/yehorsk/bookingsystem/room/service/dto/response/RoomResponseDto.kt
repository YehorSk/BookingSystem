package com.yehorsk.bookingsystem.room.service.dto.response

import com.yehorsk.bookingsystem.common.type.RoomId
import java.math.BigDecimal

data class RoomResponseDto(
    val id: RoomId,
    val number: String,
    val capacity: Int,
    val isActive: Boolean,
    val price: BigDecimal
)
