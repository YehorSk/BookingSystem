package com.yehorsk.bookingsystem.room.service.dto.response

data class RoomResponseDto(
    val id: Long,
    val number: String,
    val capacity: Int,
    val isActive: Boolean
)
