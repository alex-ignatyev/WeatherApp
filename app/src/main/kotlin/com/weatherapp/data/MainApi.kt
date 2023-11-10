package com.weatherapp.data


import com.weatherapp.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApi {
    @GET("data/2.5/weather?lang=ru&appid=e6c18df63be4bccb60a05ce90e55a76b&units=metric")
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherResponse
}