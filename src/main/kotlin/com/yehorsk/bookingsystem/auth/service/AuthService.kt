package com.yehorsk.bookingsystem.auth.service

import com.yehorsk.bookingsystem.auth.exceptions.types.EmailIsTakenException
import com.yehorsk.bookingsystem.auth.mappers.toUserResponseDto
import com.yehorsk.bookingsystem.auth.database.repository.UserRepository
import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.auth.service.dto.requests.RegisterUserDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.UserResponseDto
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: Pass
) {

    fun register(request: RegisterUserDto): UserResponseDto {
        if(userRepository.existsByEmail(request.email)) throw EmailIsTakenException()
        return userRepository.save(UserEntity(
            name = request.name,
            email = request.email,
            password = request.password,
        )).toUserResponseDto()
    }

}