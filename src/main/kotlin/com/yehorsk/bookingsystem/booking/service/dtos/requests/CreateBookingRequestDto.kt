package com.yehorsk.bookingsystem.booking.service.dtos.requests

import com.yehorsk.bookingsystem.common.type.RoomId
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.service.dto.validation.DateRange
import com.yehorsk.bookingsystem.room.service.dto.validation.ValidDateRange
import org.jetbrains.annotations.NotNull
import java.time.Instant

data class CreateBookingRequestDto(
    @field:NotNull("Date range is required")
    @field:ValidDateRange
    val dateRange: DateRange,
    @field:NotNull("Amount of Guests is required")
    val guests: Int,
    val userId: UserId,
    val roomId: RoomId
)
