package com.yehorsk.bookingsystem.auth.exceptions

import com.yehorsk.bookingsystem.auth.exceptions.types.EmailIsTakenException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(EmailIsTakenException::class)
    fun handleEmailTaken(e: EmailIsTakenException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(mapOf("email" to "Email is already taken"))
    }

}