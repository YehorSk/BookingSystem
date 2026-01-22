package com.yehorsk.bookingsystem.auth.database.entity

import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Email
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    var enabled: Boolean = true,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER,
    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    var bookings: MutableSet<BookingEntity> = mutableSetOf(),
    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null,
    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null,
)

enum class UserRole {
    USER, ADMIN
}