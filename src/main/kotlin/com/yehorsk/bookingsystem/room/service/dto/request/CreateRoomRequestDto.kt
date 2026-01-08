package com.yehorsk.bookingsystem.room.service.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CreateRoomRequestDto(
    @NotNull(message = "Number cannot be empty")
    var number: String,
    @field:Min(value = 1, "Capacity should be at least 1")
    val capacity: Int,
    val isActive: Boolean = true
)
