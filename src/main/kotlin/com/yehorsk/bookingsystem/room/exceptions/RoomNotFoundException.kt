package com.yehorsk.bookingsystem.room.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
class RoomNotFoundException(
    id: Long
): RuntimeException(
    "Room not found"
)