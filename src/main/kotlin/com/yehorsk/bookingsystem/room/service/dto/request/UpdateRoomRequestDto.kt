package com.yehorsk.bookingsystem.room.service.dto.request

import jakarta.validation.constraints.Min

data class UpdateRoomRequestDto(
    var number: String? = null,
    @field:Min(value = 1, "Capacity should be at least 1")
    val capacity: Int? = null,
    val isActive: Boolean? = null
)