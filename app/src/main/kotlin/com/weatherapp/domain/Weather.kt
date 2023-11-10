package com.weatherapp.domain

import com.weatherapp.data.response.WeatherResponse
import java.text.SimpleDateFormat
import java.util.Date

data class Weather(
    val city: String,
    val icon: String,
    val description: String,

    val temp: String,
    val tempMin: String,
    val tempMax: String,
    val pressure: String,
    val humidity: String,

    val allClouds: String,

    val sysSunrise: String,
    val sysSunset: String,
    val windSpeed: String,
) {

    override fun toString(): String {
        return "Город: $city\n" +
                "Температура: $temp\n" +
                "Описание: $description\n" +
                "Скорость ветра: $windSpeed\n" +
                "Облачность: $allClouds"
    }
}

fun WeatherResponse.toDomain(): Weather {
    return Weather(
        city = this.city,
        icon = createIcon(this.weather[0].icon),
        description = this.weather[0].description,

        temp = convertTemperature(this.main.temp),
        tempMin = "мин: " + convertTemperature(this.main.tempMin),
        tempMax = "макс: " + convertTemperature(this.main.tempMax),
        pressure = this.main.pressure.toString() + "мм.рт.ст.",
        humidity = this.main.humidity.toString() + "%",

        allClouds = this.clouds.all.toString() + "%",

        sysSunrise = convertTime(this.sys.sunrise),
        sysSunset = convertTime(this.sys.sunset),
        windSpeed = prepareWind(this.wind.speed)
    )
}

private fun createIcon(iconCode: String): String {
    return "https://openweathermap.org/img/wn/${iconCode}@4x.png"
}

private fun convertTemperature(temp: Double): String {
    // -5.84
    val tempInt = temp.toInt() // -5
    val tempPositiveOrNegative =
        if (tempInt > 0) "+$tempInt" else tempInt.toString() // "+5" или "-5" или "0"
    val tempGradusString = tempPositiveOrNegative + "°"// "-5"
    return tempGradusString
}

private fun prepareWind(speed: Double): String {
    return "${speed.toInt()} м/с"
}

private fun convertTime(milliseconds: Long): String {
    return SimpleDateFormat("HH:mm").format(Date(milliseconds * 1000))
}