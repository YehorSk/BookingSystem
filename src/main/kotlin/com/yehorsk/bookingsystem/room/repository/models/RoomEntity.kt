package com.yehorsk.bookingsystem.room.repository.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "rooms")
class RoomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var number: String = "",
    @Column(nullable = false)
    var capacity: Int = 1,
    @Column(name = "is_active")
    var isActive: Boolean = true,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant = Instant.now(),
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
)