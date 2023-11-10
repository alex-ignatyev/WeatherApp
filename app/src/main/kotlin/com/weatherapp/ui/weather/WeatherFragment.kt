package com.weatherapp.ui.weather

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.weatherapp.R
import com.weatherapp.databinding.FragmentWeatherBinding
import com.weatherapp.domain.Weather
import com.weatherapp.util.hideKeyboard

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val vm: WeatherVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> searchCity()
                R.id.copy -> copyText(vm.weather.value.toString())
                R.id.save -> {
                    //todo Сохранить в файле
                }

                R.id.share -> shareEmail(text = vm.weather.value.toString(), tema = "Погода в городе ${vm.weather.value?.city}")
            }
            return@setOnMenuItemClickListener true
        }

        vm.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        vm.error.observe(viewLifecycleOwner) { error ->
            binding.error.text = error
        }

        vm.weather.observe(viewLifecycleOwner) { weather ->
            setWeather(weather)
        }

        binding.cityText.addTextChangedListener { city ->
            vm.city.value = city.toString()
        }

        return binding.root
    }

    private fun searchCity() {
        vm.getWeather(vm.city.value ?: "")
        hideKeyboard()
    }

    private fun setWeather(weather: Weather) {
        Glide.with(this)
            .load(weather.icon)
            .into(binding.icon)
        binding.nameCity.text = weather.city
        binding.description.text = weather.description
        binding.weatherText.text = weather.temp
        binding.tempMin.text = weather.tempMin
        binding.tempMax.text = weather.tempMax
        binding.pressure.setValue(weather.pressure)
        binding.humidity.setValue(weather.humidity)

        binding.cloud.setValue(weather.allClouds)

        binding.sunrise.setValue(weather.sysSunrise)
        binding.sunset.setValue(weather.sysSunset)
        binding.wind.setValue(weather.windSpeed)

        binding.cityText.text.clear()
    }

    private fun copyText(text: String) {
        val clipboardManager =
            getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
        val clipData = ClipData.newPlainText(CLIPBOARD_LABEL, text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, getString(R.string.text_copy), Toast.LENGTH_SHORT).show()
    }


    private fun shareEmail(text: String, tema: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(MAIL)
            putExtra(Intent.EXTRA_SUBJECT, tema)
            putExtra(Intent.EXTRA_TEXT, text)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private companion object {
        const val CLIPBOARD_LABEL = "Weather_Label"
        const val MAIL = "mailto:"
    }
}

