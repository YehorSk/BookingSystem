package com.yehorsk.bookingsystem.booking.service

import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.mappers.toBookingEntity
import com.yehorsk.bookingsystem.booking.mappers.toBookingResponseDto
import com.yehorsk.bookingsystem.booking.repository.BookingRepository
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository
) {

    fun create(request: CreateBookingRequestDto, userId: UserId): BookingResponseDto{
        val booking = bookingRepository.save(request.toBookingEntity(userId))
        return booking.toBookingResponseDto()
    }

}