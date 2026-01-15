package com.yehorsk.bookingsystem.auth.service

import com.yehorsk.bookingsystem.auth.exceptions.types.EmailIsTakenException
import com.yehorsk.bookingsystem.auth.mappers.toUserResponseDto
import com.yehorsk.bookingsystem.auth.database.repository.UserRepository
import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.auth.exceptions.types.InvalidCredentialsException
import com.yehorsk.bookingsystem.auth.exceptions.types.UserDoesNotExistException
import com.yehorsk.bookingsystem.auth.service.dto.requests.LoginUserDto
import com.yehorsk.bookingsystem.auth.service.dto.requests.RegisterUserDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.LoginResponseDto
import com.yehorsk.bookingsystem.auth.service.dto.responses.UserResponseDto
import com.yehorsk.bookingsystem.auth.utils.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(request: RegisterUserDto): UserResponseDto {
        if(userRepository.existsByEmail(request.email)) throw EmailIsTakenException()
        return userRepository.save(UserEntity(
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password)!!,
        )).toUserResponseDto()
    }

    fun login(request: LoginUserDto): LoginResponseDto {
        val user = userRepository.findByEmail(request.email)
            ?: throw UserDoesNotExistException()

        if(!passwordEncoder.matches(request.password, user.password)) throw InvalidCredentialsException()
        val jwt = jwtUtil.generateToken(user.id.toString())
        return LoginResponseDto(user.toUserResponseDto(), jwt)

    }

}