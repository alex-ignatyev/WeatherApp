package com.weatherapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.weatherapp.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.nav_host_fragment_content_base))
    }
}






/*
Необходимо написать код для реализации следующих пунктов:


6 button сохранения результата в систему. Формат json

7 хранение и просмотр истории запросов

8 обработка 400 500 ошибок, сообщение пользователю об ошибке подключения
*/