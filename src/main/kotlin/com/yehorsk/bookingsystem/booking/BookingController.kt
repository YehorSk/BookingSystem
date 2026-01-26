package com.yehorsk.bookingsystem.booking

import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.service.BookingService
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bookings")
class BookingController(
    private val bookingService: BookingService
) {

    @PostMapping("/")
    fun createBooking(@Valid @RequestBody request: CreateBookingRequestDto, @AuthenticationPrincipal principal: CustomUserDetails): BookingResponseDto {
        return bookingService.create(request, principal.id)
    }


}