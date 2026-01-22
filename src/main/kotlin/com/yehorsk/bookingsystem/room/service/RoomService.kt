package com.yehorsk.bookingsystem.room.service

import com.yehorsk.bookingsystem.common.type.RoomId
import com.yehorsk.bookingsystem.exceptions.DuplicateResourceException
import com.yehorsk.bookingsystem.room.exceptions.RoomNotFoundException
import com.yehorsk.bookingsystem.room.mappers.toEntity
import com.yehorsk.bookingsystem.room.mappers.toRoomResponseDto
import com.yehorsk.bookingsystem.room.repository.RoomRepository
import com.yehorsk.bookingsystem.room.service.dto.request.CreateRoomRequestDto
import com.yehorsk.bookingsystem.room.service.dto.request.UpdateRoomRequestDto
import com.yehorsk.bookingsystem.room.service.dto.response.RoomResponseDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class RoomService(
    private val roomRepository: RoomRepository
) {

    fun get(id: RoomId): RoomResponseDto {
        return roomRepository.findById(id)
            .orElseThrow{ RoomNotFoundException(id) }.toRoomResponseDto()
    }

    @PreAuthorize("hasRole('ADMIN')")
    fun create(request: CreateRoomRequestDto): RoomResponseDto {
        if(roomRepository.existsByNumber(request.number)) throw DuplicateResourceException("Room with this number: ${request.number} already exists")
        return roomRepository.save(request.toEntity()).toRoomResponseDto()
    }

    fun update(request: UpdateRoomRequestDto, id: Long): RoomResponseDto {
        val room = roomRepository.findById(id)
            .orElseThrow{ RoomNotFoundException(id) }
        request.number?.let {
            if(roomRepository.existsByNumberAndIdNot(it, id)) throw DuplicateResourceException("Room with this number: $it already exists")
            room.number = it
        }
        request.capacity?.let { room.capacity = it }
        request.isActive?.let { room.isActive = it }
        return roomRepository.save(room).toRoomResponseDto()
    }

    fun delete(id: Long){
        roomRepository.findById(id)
            .orElseThrow{ RoomNotFoundException(id) }
        roomRepository.deleteById(id)
    }
}