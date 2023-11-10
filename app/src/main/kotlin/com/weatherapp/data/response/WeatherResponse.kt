package com.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("name") val city: String
) {
    data class Weather(
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String
    )

    data class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int
    )

    data class Wind(
        @SerializedName("speed") val speed: Double
    )

    data class Clouds(
        @SerializedName("all") val all: Int
    )

    data class Sys(
        @SerializedName("sunrise") val sunrise: Long,
        @SerializedName("sunset") val sunset: Long
    )
}