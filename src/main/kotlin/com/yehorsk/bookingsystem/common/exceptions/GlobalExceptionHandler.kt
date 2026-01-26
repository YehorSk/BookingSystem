package com.yehorsk.bookingsystem.common.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<Map<String,String>>{
        val errors = mutableMapOf<String,String>()
        e.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage ?: "Error"
        }
        e.bindingResult.globalErrors.forEach { error ->
            errors[error.codes?.lastOrNull() ?: "Error"] = error.defaultMessage ?: "Error"
        }
        return ResponseEntity
            .badRequest()
            .body(errors)
    }

}