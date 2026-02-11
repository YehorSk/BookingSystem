package com.yehorsk.bookingsystem.booking.service.dtos.requests

import com.yehorsk.bookingsystem.booking.repository.models.BookingStatus
import com.yehorsk.bookingsystem.common.type.RoomId
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.service.dto.validation.DateRange
import jakarta.annotation.Nullable
import java.time.Instant

data class GetBookingsRequestDto(
    val userId: UserId? = null,
    val roomId: RoomId? = null,
    val startDate: Instant? = null,
    val endDate: Instant? = null,
    val guests: Int? = null,
    val status: BookingStatus? = null
)
