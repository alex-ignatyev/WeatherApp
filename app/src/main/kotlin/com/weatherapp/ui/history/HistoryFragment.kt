package com.weatherapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class HistoryFragment : Fragment() {
    private val vm: HistoryVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                LazyColumn(modifier = Modifier.background(Color.Black)) {
                    items(20) {
                        HistoryElement()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryElement() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Москва", color = Color.White, modifier = Modifier.weight(1f), fontSize = 24.sp)
        Text(text = "+10°", color = Color.White, fontSize = 24.sp)
    }
}