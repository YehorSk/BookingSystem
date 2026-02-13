package com.yehorsk.bookingsystem.booking.exceptions.types

import com.yehorsk.bookingsystem.common.type.BookingId

class InvalidBookingStatusTransitionException(
    val bookingId: BookingId,
): RuntimeException()