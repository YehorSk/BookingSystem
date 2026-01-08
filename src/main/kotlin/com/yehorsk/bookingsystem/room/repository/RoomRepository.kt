package com.yehorsk.bookingsystem.room.repository

import com.yehorsk.bookingsystem.room.repository.models.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository: JpaRepository<RoomEntity, Long> {

    fun existsByNumber(number: String): Boolean

    fun existsByNumberAndIdNot(number: String, id: Long): Boolean

}