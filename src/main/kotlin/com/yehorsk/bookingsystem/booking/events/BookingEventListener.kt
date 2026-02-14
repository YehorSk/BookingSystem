package com.yehorsk.bookingsystem.booking.events

import com.yehorsk.bookingsystem.booking.events.types.BookingCancelledEvent
import com.yehorsk.bookingsystem.booking.events.types.BookingCreatedEvent
import com.yehorsk.bookingsystem.common.service.MailService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class BookingEventListener(
    private val mailService: MailService
) {

    @Async
    @TransactionalEventListener
    fun bookingCancelledHandler(event: BookingCancelledEvent) {
        println("Booking cancelled event received")
        val booking = event.booking
        mailService.sendPlainText(event.sendTo, "Booking cancelled", "Booking with id ${booking.id} was cancelled")
    }

    @Async
    @TransactionalEventListener
    fun bookingCreatedHandler(event: BookingCreatedEvent) {
        val booking = event.booking
        mailService.sendPlainText(event.sendTo, "Booking created", "Booking with id ${booking.id} was created")
    }

}