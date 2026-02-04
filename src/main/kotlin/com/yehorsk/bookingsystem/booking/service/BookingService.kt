package com.yehorsk.bookingsystem.booking.service

import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.exceptions.types.BookingDoesNotExistException
import com.yehorsk.bookingsystem.booking.exceptions.types.RoomNotAvailableException
import com.yehorsk.bookingsystem.booking.mappers.toBookingEntity
import com.yehorsk.bookingsystem.booking.mappers.toBookingResponseDto
import com.yehorsk.bookingsystem.booking.repository.BookingRepository
import com.yehorsk.bookingsystem.booking.repository.models.BookingStatus
import com.yehorsk.bookingsystem.booking.security.BookingSecurity
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.requests.GetBookingsRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import com.yehorsk.bookingsystem.common.type.BookingId
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.exceptions.RoomNotFoundException
import com.yehorsk.bookingsystem.room.repository.RoomRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.awt.print.Book

//Bookings
//
//Create a booking (room, user, date range)
//
//Prevent overlapping bookings (hard constraint)
//
//Get booking details
//
//Cancel booking
//
//List bookings:
//
//by user
//
//by room
//
//by date range

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository,
    private val bookingSecurity: BookingSecurity
) {

//    fun getBookings(request: GetBookingsRequestDto): List<BookingResponseDto> {
//
//    }

    @PreAuthorize("@bookingSecurity.isOwner(#id) || hasRole('ADMIN')")
    fun getBookingById(id: BookingId): BookingResponseDto{
        val booking = bookingRepository.findById(id)
            .orElseThrow{ throw BookingDoesNotExistException(id) }
        return booking.toBookingResponseDto()
    }

    @PreAuthorize("@bookingSecurity.isOwner(#id) || hasRole('ADMIN')")
    fun cancelBooking(id: BookingId): BookingResponseDto {
        val booking = bookingRepository.findById(id)
            .orElseThrow{ throw BookingDoesNotExistException(id) }

        booking.apply {
            status = BookingStatus.CANCELLED
        }

        return bookingRepository.save(booking).toBookingResponseDto()
    }

    @Transactional
    fun create(request: CreateBookingRequestDto, userId: UserId): BookingResponseDto{
        val room = roomRepository.findRoomEntityById(request.roomId)
            ?: throw RoomNotFoundException(request.roomId)
        if(!room.isActive)
            throw RoomNotAvailableException(request.roomId)
        if(bookingRepository.existsBookingConflict(request.roomId, request.dateRange.startDate, request.dateRange.endDate))
            throw RoomNotAvailableException(request.roomId)

        val booking = bookingRepository.save(request.toBookingEntity(userId))
        return booking.toBookingResponseDto()
    }

}