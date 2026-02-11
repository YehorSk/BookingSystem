package com.yehorsk.bookingsystem.booking.repository

import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface BookingRepository: JpaRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    @Query("""
        SELECT CASE WHEN COUNT(b) > 0 THEN true else false END 
        FROM BookingEntity b 
        WHERE b.roomId = :roomId 
        AND :startDate < b.endDate
        AND :endDate > b.startDate
        and b.status in ('CREATED', 'CONFIRMED')
    """)
    fun existsBookingConflict(
        @Param("roomId") roomId: Long,
        @Param("startDate") startDate: Instant,
        @Param("endDate") endDate: Instant,
        ): Boolean

}