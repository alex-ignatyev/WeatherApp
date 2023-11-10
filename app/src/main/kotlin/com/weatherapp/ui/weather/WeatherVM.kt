package com.weatherapp.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.di.InternetClient
import com.weatherapp.domain.Weather
import com.weatherapp.domain.toDomain
import kotlinx.coroutines.launch

class WeatherVM : ViewModel() {
    val isLoading = MutableLiveData<Boolean>(false)
    val city = MutableLiveData<String>()
    val error = MutableLiveData<String>()
    val weather = MutableLiveData<Weather>()

    private val internetClient = InternetClient
    private val mainApi = internetClient.createMainApi()

    fun getWeather(city: String) {
        if (city.length < 3) return
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = mainApi.getWeather(city).toDomain()
                weather.value = response
                this@WeatherVM.city.value = ""
                error.value = ""
            } catch (e: Exception) {
                error.value = e.message
            }
            isLoading.value = false
        }
    }
}