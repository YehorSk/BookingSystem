package com.yehorsk.bookingsystem.auth.service.validation

import com.yehorsk.bookingsystem.auth.service.dto.requests.RegisterUserDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordMatchesValidator: ConstraintValidator<PasswordMatches, RegisterUserDto> {

    override fun isValid(dto: RegisterUserDto?, p1: ConstraintValidatorContext?): Boolean {
        if (dto == null) return true
        return dto.password == dto.passwordConfirm
    }

}