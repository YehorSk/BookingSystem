package com.yehorsk.bookingsystem.booking.service.dtos.requests

import com.yehorsk.bookingsystem.common.type.RoomId
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.service.dto.validation.DateRange
import jakarta.annotation.Nullable

data class GetBookingsRequestDto(
    val userId: UserId? = null,
    val roomId: RoomId? = null,
    val dateRange: DateRange? = null,
)
