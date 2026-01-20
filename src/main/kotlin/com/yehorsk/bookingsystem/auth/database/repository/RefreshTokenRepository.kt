package com.yehorsk.bookingsystem.auth.database.repository

import com.yehorsk.bookingsystem.auth.database.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshTokenEntity, Long> {

    fun deleteByUserId(id: Long)

}