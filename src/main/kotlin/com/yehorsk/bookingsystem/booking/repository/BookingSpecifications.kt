package com.yehorsk.bookingsystem.booking.repository

import com.yehorsk.bookingsystem.booking.repository.models.BookingEntity
import com.yehorsk.bookingsystem.booking.repository.models.BookingStatus
import com.yehorsk.bookingsystem.booking.service.dtos.requests.GetBookingsRequestDto
import com.yehorsk.bookingsystem.common.type.RoomId
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.data.jpa.domain.Specification
import java.time.Instant

object BookingSpecifications {

    fun hasRoomId(roomId: RoomId?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            roomId?.let {
                criteriaBuilder.equal(root.get<RoomId>("roomId"), it)
            }
        }
    }

    fun hasUserId(userId: UserId?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            userId?.let{
                criteriaBuilder.equal(root.get<UserId>("userId"), it)
            }
        }
    }

    fun hasStatus(status: BookingStatus?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            status?.let {
                criteriaBuilder.equal(root.get<BookingStatus>("status"), it)
            }
        }
    }

    fun hasGuests(guests: Int?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            guests?.let{
                criteriaBuilder.equal(root.get<Int>("guests"), it)
            }
        }
    }

    fun createdAfter(instant: Instant?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            instant?.let {
                criteriaBuilder.greaterThanOrEqualTo(root.get<Instant>("createdAt"), instant)
            }
        }
    }

    fun createdBefore(instant: Instant?): Specification<BookingEntity> {
        return Specification { root, _, criteriaBuilder  ->
            instant?.let {
                criteriaBuilder.lessThanOrEqualTo(root.get<Instant>("createdAt"), instant)
            }
        }
    }

    fun buildDynamicSpecification(request: GetBookingsRequestDto): Specification<BookingEntity> {
        val specs = mutableListOf<Specification<BookingEntity>>()
        request.userId?.let { specs.add(hasUserId(it)) }
        request.roomId?.let { specs.add(hasRoomId(it)) }
        request.status?.let { specs.add(hasStatus(it)) }
        request.guests?.let { specs.add(hasGuests(it)) }
        request.startDate?.let { specs.add(createdAfter(it)) }
        request.endDate?.let { specs.add(createdBefore(it)) }

        return if(specs.isEmpty()) {
            Specification { _, _, _ -> null }
        }else{
            Specification.allOf(specs)
        }
    }

}