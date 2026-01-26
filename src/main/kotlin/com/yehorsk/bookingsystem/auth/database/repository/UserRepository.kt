package com.yehorsk.bookingsystem.auth.database.repository

import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun existsByEmail(email: String): Boolean

    fun findUserEntityById(id: Long): UserEntity?

    fun findByEmail(email: String): UserEntity?

}