package com.weatherapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.weatherapp.R
import com.weatherapp.databinding.FragmentWeatherBinding
import com.weatherapp.databinding.LayoutWeatherInfoBinding

class WeatherInfo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutWeatherInfoBinding

    init {
        binding = LayoutWeatherInfoBinding.inflate(LayoutInflater.from(context), this, true)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.WeatherInfo)

        try {
            val drawableFrommAttrId = attributes.getResourceId(R.styleable.WeatherInfo_image, 0)
            if (drawableFrommAttrId != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableFrommAttrId)
                binding.image.setImageDrawable(drawable)
            }

            binding.title.text = attributes.getString(R.styleable.WeatherInfo_title)
        } finally {
            attributes.recycle()
        }
    }

    fun setValue(value: String) {
        binding.value.text = value
        if (value.isNotEmpty()) {
            binding.root.isVisible = true
        }
    }
}