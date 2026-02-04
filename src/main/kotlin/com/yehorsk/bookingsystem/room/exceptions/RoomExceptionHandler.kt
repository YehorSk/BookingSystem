package com.yehorsk.bookingsystem.room.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RoomExceptionHandler {

    @ExceptionHandler(RoomNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElement(e: RoomNotFoundException): ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.message ?: "Room with number ${e.id} not found")
    }

}