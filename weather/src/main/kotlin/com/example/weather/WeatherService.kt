package com.example.weather

import com.example.data.Weather
import com.example.data.WeatherRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@ComponentScan("com.example.data")
class WeatherService(
        private val restTemplate: RestTemplate,
        private val weatherRepository: WeatherRepository,
        @Value("\${weather.api.url") private val apiUrl: String
) {
    fun getWeather(city: String): Weather? {
        val url = "$apiUrl?city=$city"
        val weatherResponse = restTemplate.getForObject(url, WeatherResponse::class.java)
        return weatherResponse?.let {
            val weather = Weather(city = city, temperature = it.main.temp, description = it.weather[0].description)
            weatherRepository.save(weather)
        }
    }
}

data class WeatherResponse(
    val main: Main,
    val weather: List<WeatherData>
)

data class Main(
    val temp: Double
)

data class WeatherData(
    val description: String
)

