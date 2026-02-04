package com.yehorsk.bookingsystem.booking.exceptions

import com.yehorsk.bookingsystem.booking.exceptions.types.BookingDoesNotExistException
import com.yehorsk.bookingsystem.booking.exceptions.types.RoomNotAvailableException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookingExceptionHandler {

    @ExceptionHandler(BookingDoesNotExistException::class)
    fun handleUserDoesNotExist(e: BookingDoesNotExistException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to "Booking does not exist"))
    }

    @ExceptionHandler(RoomNotAvailableException::class)
    fun handleRoomNotAvailable(e: RoomNotAvailableException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(mapOf("error" to "Room number ${e.id} is not available"))
    }

}