package com.yehorsk.bookingsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class BookingSystemApplication

fun main(args: Array<String>) {
    runApplication<BookingSystemApplication>(*args)
}
