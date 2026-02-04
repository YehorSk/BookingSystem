package com.yehorsk.bookingsystem.booking

import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.service.BookingService
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import com.yehorsk.bookingsystem.common.type.BookingId
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @PatchMapping("/cancel/{id}")
    fun cancelBooking(@PathVariable id: BookingId): BookingResponseDto {
        return bookingService.cancelBooking(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: BookingId): BookingResponseDto {
        return bookingService.getBookingById(id)
    }



}