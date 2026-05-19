package com.example.arogyanidhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arogyanidhi.ui.ArogyaViewModel
import com.example.arogyanidhi.ui.navigation.AppNavigation
import com.example.arogyanidhi.ui.theme.ArogyanidhiTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArogyanidhiTheme {
                val viewModel: ArogyaViewModel = viewModel()
                AppNavigation(viewModel)
            }
        }
    }
}