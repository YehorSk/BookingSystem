package com.yehorsk.bookingsystem.room.service.dto.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DateRangeValidator::class])
annotation class ValidDateRange (
    val message: String = "Start date should be before end date",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)