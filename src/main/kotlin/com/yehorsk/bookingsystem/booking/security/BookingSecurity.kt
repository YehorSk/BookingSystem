package com.yehorsk.bookingsystem.booking.security

import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.repository.BookingRepository
import com.yehorsk.bookingsystem.common.type.BookingId
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class BookingSecurity(
    private val bookingRepository: BookingRepository
) {

    fun isOwner(bookingId: BookingId): Boolean {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails
            ?: return false

        val isOwner = bookingRepository
            .findById(bookingId)
            .map { it.userId == principal.id }
            .orElse(false)
        print("IsOwner: $isOwner")
        return isOwner
    }

    fun checkUserId(userId: UserId): Boolean {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails
            ?: return false
        return principal.id == userId
    }

}