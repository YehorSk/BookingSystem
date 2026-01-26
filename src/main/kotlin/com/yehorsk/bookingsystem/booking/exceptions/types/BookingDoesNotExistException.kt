package com.yehorsk.bookingsystem.booking.exceptions.types

class BookingDoesNotExistException(val id: Long): RuntimeException()