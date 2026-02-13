package com.yehorsk.bookingsystem.auth.config

import com.yehorsk.bookingsystem.auth.exceptions.types.UserNotAuthenticatedException
import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUserProvider {

    fun getCurrentUser(): CustomUserDetails {
        val user = SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails
            ?: throw UserNotAuthenticatedException()
        return user
    }

    fun getCurrentUserId(): UserId = getCurrentUser().id

}