package com.yehorsk.bookingsystem.auth.exceptions

import com.yehorsk.bookingsystem.auth.exceptions.types.EmailIsTakenException
import com.yehorsk.bookingsystem.auth.exceptions.types.InvalidCredentialsException
import com.yehorsk.bookingsystem.auth.exceptions.types.UserDoesNotExistException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(EmailIsTakenException::class)
    fun handleEmailTaken(e: EmailIsTakenException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(mapOf("email" to "Email is already taken"))
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleUserDoesNotExist(e: InvalidCredentialsException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to "Invalid credentials"))
    }

    @ExceptionHandler(UserDoesNotExistException::class)
    fun handleUserDoesNotExist(e: UserDoesNotExistException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to "User does not exist"))
    }

}