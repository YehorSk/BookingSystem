package com.yehorsk.bookingsystem.booking.mappers

import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.RoomSummaryDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.UserSummaryDto
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.repository.models.RoomEntity

fun UserEntity.toUserSummaryDto() = UserSummaryDto(
    id = id!!,
    email = email,
    name = name
)

fun RoomEntity.toRoomSummaryDto() = RoomSummaryDto(
    id = id!!,
    number = number,
    capacity = capacity,
    price = price
)

fun BookingEntity.toBookingResponseDto() = BookingResponseDto(
    id = id!!,
    startDate = startDate,
    endDate = endDate,
    status = status,
    guests = guests,
    user = user?.toUserSummaryDto(),
    room = room?.toRoomSummaryDto()
)

fun CreateBookingRequestDto.toBookingEntity(userId: UserId) = BookingEntity(
    startDate = dateRange.startDate,
    endDate = dateRange.endDate,
    guests = guests,
    roomId = roomId,
    userId = userId
)