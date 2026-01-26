package com.yehorsk.bookingsystem.auth.service

import com.yehorsk.bookingsystem.auth.database.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user = userRepository.findUserEntityById(username.toLong())
            ?: throw UsernameNotFoundException("User not found")
        return CustomUserDetails(
            user
        )
    }

}