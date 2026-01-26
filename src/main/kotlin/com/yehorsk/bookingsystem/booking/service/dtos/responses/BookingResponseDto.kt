package com.yehorsk.bookingsystem.booking.service.dtos.responses

import com.yehorsk.bookingsystem.booking.repository.models.BookingStatus
import com.yehorsk.bookingsystem.common.type.BookingId
import java.time.Instant

data class BookingResponseDto(
    val id: BookingId,
    val startDate: Instant,
    val endDate: Instant,
    val status: BookingStatus,
    val guests: Int,
    val user: UserSummaryDto? = null,
    val room: RoomSummaryDto? = null
)
