package com.yehorsk.bookingsystem.booking.exceptions.types

class RoomNotAvailableException(
    val id: Long
): RuntimeException()