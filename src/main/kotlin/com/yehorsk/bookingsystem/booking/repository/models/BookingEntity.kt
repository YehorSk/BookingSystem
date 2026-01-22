package com.yehorsk.bookingsystem.booking.repository.models

import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.room.repository.models.RoomEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "bookings")
class BookingEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "start_date", nullable = false)
    var startDate: Instant,
    @Column(name = "end_date", nullable = false)
    var endDate: Instant,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: BookingStatus = BookingStatus.CREATED,
    @Column(nullable = false)
    var guests: Int = 1,
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    var user: UserEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "room_id",
        nullable = false
    )
    var room: RoomEntity,
    @CreationTimestamp()
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = null,
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = null,
)

enum class BookingStatus {
    CREATED, CONFIRMED, CANCELLED, EXPIRED
}