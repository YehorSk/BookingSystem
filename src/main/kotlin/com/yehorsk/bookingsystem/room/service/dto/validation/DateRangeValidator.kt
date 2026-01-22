package com.yehorsk.bookingsystem.room.service.dto.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.Instant

data class DateRange(
    val startDate: Instant,
    val endDate: Instant
)

class DateRangeValidator: ConstraintValidator<ValidDateRange, DateRange> {

    override fun isValid(
        value: DateRange?,
        context: ConstraintValidatorContext?
    ): Boolean {
        if(value == null) return false
        return value.startDate.isBefore(value.endDate)
    }

}