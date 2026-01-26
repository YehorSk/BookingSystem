package com.yehorsk.bookingsystem.booking.repository

import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository: JpaRepository<BookingEntity, Long> {

}