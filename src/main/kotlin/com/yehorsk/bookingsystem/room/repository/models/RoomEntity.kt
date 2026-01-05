package com.yehorsk.bookingsystem.room.repository.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
    var isActive: Boolean = true
)