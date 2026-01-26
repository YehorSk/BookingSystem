package com.yehorsk.bookingsystem.booking.service.dtos.responses

import com.yehorsk.bookingsystem.common.type.RoomId
import java.math.BigDecimal

data class RoomSummaryDto(
    val id: RoomId,
    val number: String,
    val capacity: Int,
    val price: BigDecimal
)
