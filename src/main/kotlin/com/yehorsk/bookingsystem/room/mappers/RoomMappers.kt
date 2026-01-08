package com.yehorsk.bookingsystem.room.mappers

import com.yehorsk.bookingsystem.room.repository.models.RoomEntity
import com.yehorsk.bookingsystem.room.service.dto.request.CreateRoomRequestDto
import com.yehorsk.bookingsystem.room.service.dto.response.RoomResponseDto

fun RoomEntity.toRoomResponseDto() = RoomResponseDto(
    id = id ?: 0,
    number = number,
    capacity = capacity,
    isActive = isActive
)

fun CreateRoomRequestDto.toEntity() = RoomEntity(
    number = number,
    capacity = capacity,
    isActive = isActive
)