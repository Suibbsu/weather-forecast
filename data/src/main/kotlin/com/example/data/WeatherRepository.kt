package com.example.data

import org.springframework.data.jpa.repository.JpaRepository

interface WeatherRepository : JpaRepository<Weather, Long> {
    fun findByCity(city: String): Weather?
}