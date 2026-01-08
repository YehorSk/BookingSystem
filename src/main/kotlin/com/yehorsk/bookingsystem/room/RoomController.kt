package com.yehorsk.bookingsystem.room

import com.yehorsk.bookingsystem.room.service.RoomService
import com.yehorsk.bookingsystem.room.service.dto.request.CreateRoomRequestDto
import com.yehorsk.bookingsystem.room.service.dto.request.UpdateRoomRequestDto
import com.yehorsk.bookingsystem.room.service.dto.response.RoomResponseDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomController(
    private val roomService: RoomService
) {

    @GetMapping("/{id}")
    suspend fun getById(@PathVariable id: Long): RoomResponseDto {
        return roomService.get(id)
    }

    @PostMapping
    suspend fun create(request: CreateRoomRequestDto): RoomResponseDto{
        return roomService.create(request)
    }

    @PutMapping("/{id}")
    suspend fun update(@Valid @RequestBody request: UpdateRoomRequestDto, @PathVariable id: Long): RoomResponseDto{
        return roomService.update(request, id)
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Long){
        roomService.delete(id)
    }

}