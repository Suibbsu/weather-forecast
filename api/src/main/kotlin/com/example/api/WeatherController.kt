package com.example.api

import com.example.data.Weather
import com.example.weather.WeatherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/weather/{city}")
    fun getWeather(@PathVariable city: String): ResponseEntity<Weather> {
        val weather = weatherService.getWeather(city)
        return if (weather != null){
            ResponseEntity.ok(weather)
            }else{
                ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            }

        }
    }
}