package com.yehorsk.bookingsystem.room.exceptions

class RoomNotFoundException(
    id: Long
): RuntimeException(
    "Room not found"
)