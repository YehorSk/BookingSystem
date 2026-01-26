package com.yehorsk.bookingsystem.common.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class DuplicateResourceException(
    message: String
): RuntimeException(
    message
)