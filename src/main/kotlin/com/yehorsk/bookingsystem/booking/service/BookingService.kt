package com.yehorsk.bookingsystem.booking.service

import com.yehorsk.bookingsystem.auth.config.CurrentUserProvider
import com.yehorsk.bookingsystem.auth.database.entity.UserRole
import com.yehorsk.bookingsystem.auth.service.CustomUserDetails
import com.yehorsk.bookingsystem.booking.events.types.BookingCancelledEvent
import com.yehorsk.bookingsystem.booking.events.types.BookingCreatedEvent
import com.yehorsk.bookingsystem.booking.exceptions.types.BookingDoesNotExistException
import com.yehorsk.bookingsystem.booking.exceptions.types.InvalidBookingStatusTransitionException
import com.yehorsk.bookingsystem.booking.exceptions.types.RoomCapacityExceededException
import com.yehorsk.bookingsystem.booking.exceptions.types.RoomNotAvailableException
import com.yehorsk.bookingsystem.booking.mappers.toBookingEntity
import com.yehorsk.bookingsystem.booking.mappers.toBookingResponseDto
import com.yehorsk.bookingsystem.booking.repository.BookingRepository
import com.yehorsk.bookingsystem.booking.repository.BookingSpecifications
import com.yehorsk.bookingsystem.booking.repository.models.BookingStatus
import com.yehorsk.bookingsystem.booking.security.BookingSecurity
import com.yehorsk.bookingsystem.booking.service.dtos.requests.CreateBookingRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.requests.GetBookingsRequestDto
import com.yehorsk.bookingsystem.booking.service.dtos.responses.BookingResponseDto
import com.yehorsk.bookingsystem.common.service.MailService
import com.yehorsk.bookingsystem.common.type.BookingId
import com.yehorsk.bookingsystem.common.type.UserId
import com.yehorsk.bookingsystem.room.exceptions.RoomNotFoundException
import com.yehorsk.bookingsystem.room.repository.RoomRepository
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository,
    private val bookingSecurity: BookingSecurity,
    private val mailService: MailService,
    private val currentUserProvider: CurrentUserProvider,
    private val eventPublisher: ApplicationEventPublisher
) {

    @PreAuthorize("isAuthenticated()")
    fun getAllBookings(requestDto: GetBookingsRequestDto, page: Int, size: Int): Page<BookingResponseDto> {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails
        val specs = if(principal!!.user.role == UserRole.ADMIN){
            BookingSpecifications.buildDynamicSpecification(requestDto)
        }else{
            BookingSpecifications.buildDynamicSpecification(requestDto.copy(userId = principal.id))
        }
        val pageable = PageRequest.of(page, size)
        return bookingRepository.findAll(specs, pageable).map { it.toBookingResponseDto() }
    }

    @PreAuthorize("@bookingSecurity.isOwner(#id) || hasRole('ADMIN')")
    fun getBookingById(id: BookingId): BookingResponseDto{
        val booking = bookingRepository.findById(id)
            .orElseThrow{ throw BookingDoesNotExistException(id) }
        return booking.toBookingResponseDto()
    }

    @PreAuthorize("@bookingSecurity.isOwner(#id) || hasRole('ADMIN')")
    @Transactional
    fun cancelBooking(id: BookingId): BookingResponseDto {
        val booking = bookingRepository.findById(id)
            .orElseThrow{ throw BookingDoesNotExistException(id) }
        if(booking.status != BookingStatus.CREATED) {
            throw InvalidBookingStatusTransitionException(booking.id!!)
        }
        booking.apply {
            status = BookingStatus.CANCELLED
        }
        booking.let {
            val user = currentUserProvider.getCurrentUser()
            eventPublisher.publishEvent(BookingCancelledEvent(it, user.user.email))
        }
        return bookingRepository.save(booking).toBookingResponseDto()
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    fun create(request: CreateBookingRequestDto, userId: UserId): BookingResponseDto{
        val room = roomRepository.findRoomEntityById(request.roomId)
            ?: throw RoomNotFoundException(request.roomId)
        if(!room.isActive)
            throw RoomNotAvailableException(request.roomId)
        if(bookingRepository.existsBookingConflict(request.roomId, request.dateRange.startDate, request.dateRange.endDate))
            throw RoomNotAvailableException(request.roomId)
        if(request.guests > room.capacity){
            throw RoomCapacityExceededException()
        }
        val booking = bookingRepository.save(request.toBookingEntity(userId))
        booking?.let {
            val user = currentUserProvider.getCurrentUser()
            eventPublisher.publishEvent(BookingCreatedEvent(it, user.user.email))
        }
        return booking.toBookingResponseDto()
    }

}

//class SystemEvent(
//    source: Any,
//    val details: String
//): ApplicationEvent(source)
//
//@Component
//class SystemEventListener: ApplicationListener<SystemEvent> {
//
//    override fun onApplicationEvent(event: SystemEvent) {
//        println(event.details)
//    }
//
//}