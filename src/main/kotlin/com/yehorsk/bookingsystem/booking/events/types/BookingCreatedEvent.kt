package com.yehorsk.bookingsystem.booking.events.types

import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import org.springframework.context.ApplicationEvent

class BookingCreatedEvent(
    val booking: BookingEntity,
    val sendTo: String
): ApplicationEvent(booking)